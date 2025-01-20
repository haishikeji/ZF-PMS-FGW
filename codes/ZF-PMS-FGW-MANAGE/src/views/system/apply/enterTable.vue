<template>
<div>
  <el-dialog v-if="dialogVisible" :visible.sync="dialogVisible"
    width="60%" :close-on-click-modal="false" append-to-body title="全链条录入">
    <div>
      <span class="totalMoney">总投资(万元)：{{money}}</span>
      <div class="totalMoney" v-for="(item,index) in projectList" :key="index" style="display: inline-block;">
        <dict-tag :options="dict.type.source_of_funds" :value="item.sourceOfFunds" style="display: inline-block;"/>
        <span>(万元):{{item.totalInvestment}}</span>
      </div>
    </div>
    <el-button type="primary" v-if="this.staty ==0 || !this.planInfoStatus" plain icon="el-icon-plus" size="mini" @click="handleAdd" style="margin-bottom: 10px;">新增</el-button>
   <el-table v-loading="loading" :data="enterList" ref="multipleTable">
      <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
      <el-table-column prop="planTime" label="时间" width="180" align="center">
        <template slot-scope="scope">
          <el-date-picker clearable  style="width: 100%;"
                          v-model="scope.row.planTime"
                          type="month"
                          :disabled="scope.row.disabled"
                          value-format="yyyy-MM-dd"
                          :picker-options="pickerOptions"
                          placeholder="请选择时间">
          </el-date-picker>
        </template>
      </el-table-column>
     <el-table-column prop="yearInvestment" label="投资金额(万元)" align="center"  min-width="100">
       <template slot-scope="scope">
         <el-input v-model="scope.row.yearInvestment" :disabled="scope.row.disabled" @input="filterInput(scope.row,scope.row.yearInvestment,'yearInvestment')"  autocomplete="off" size="small" placeholder="投资金额"></el-input>
       </template>
     </el-table-column>
     <el-table-column prop="plan_info" label="内容" align="center" min-width="150">
       <template slot-scope="scope">
         <el-input v-model="scope.row.planInfo" :disabled="scope.row.disabled" type="textarea" min-row="1" autosize resize="none" size="small" placeholder="内容"></el-input>
       </template>
     </el-table-column>
     <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
       <template slot-scope="scope">
         <el-button
           size="mini"
           type="text"
           :disabled="scope.row.disabled"
           icon="el-icon-delete"
           @click="handleDelete(scope.row,scope.$index)"
           v-hasPermi="['system:projectPlan:remove']"
         >删除</el-button>
       </template>
     </el-table-column>
   </el-table>
    <el-descriptions class="margin-top" title="审核记录" v-if="enterList.length>0 && enterList[0].status == false" :column="3" style="margin-top: 15px;">
      <el-descriptions-item label="审核">
        <el-tag size="small"  v-if="enterList[0].status">同意</el-tag>
          <el-tag type="danger" size="small" v-else>不同意</el-tag>
      </el-descriptions-item>
      <el-descriptions-item label="审核人">{{enterList[0].auditUserName}}</el-descriptions-item>
      <el-descriptions-item label="审核时间">{{enterList[0].operatingTime}}</el-descriptions-item>
      <el-descriptions-item label="审核意见">{{enterList[0].auditOpinion}}</el-descriptions-item>
    </el-descriptions>
    <div slot="footer" style="margin-top: 10px;">
      <el-button type="primary" size="small"  @click="submitForm()">确定 </el-button>
      <el-button size="small" @click="dialogVisible = false">取消 </el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>
import {
  addProjectPlan,
  delProjectPlan,
  listProjectPlan,
  projectFundList,
  updateProjectPlan,resubmit
} from "@/api/system/projectPlan/projectPlan";
import {deptList, userList} from "@/api/system/apply/apply";

export default {
  dicts:['source_of_funds'],
  data(){
    return{
      pickerOptions: {
        disabledDate(time) {
          const currentYear = new Date().getFullYear();
          // 获取选择的日期的年份
          const dateYear = time.getFullYear();
          // 如果选择的日期不是当前年份，则禁用
          return dateYear !== currentYear; // 假设你想要设置的结束日期是2025年12月31日
        }
      },
      dialogVisible:false,
      id:null,
      loading:true,
      total: 0,
      enterList:[],
      queryParams: {
        projectId:null,
        pageNum: 1,
        pageSize: 999,
      },
      // 非多个禁用
      multiple: true,
      deptList:[],
      dutyList:[],
      money:null,
      projectList:[],
      staty:false,
      planInfoStatus:null
    }
  },
  computed:{
    areAllShow(){
      return  this.enterList.every(item =>
        item.planTime !== undefined && item.planTime !== '' && item.planTime !== null &&
        item.yearInvestment !== undefined && item.yearInvestment !== '' && item.yearInvestment !== null)
    },
  },
  methods:{
    filterInput(row, value,key) {
      let filteredValue = value.replace(/[^\d.]/g, '');
      filteredValue = filteredValue.replace(/\.{2,}/g, '.');
      this.$set(row, key, filteredValue);
    },
    open(id,money,staty,planInfoStatus){
      this.money = money;
      this.staty = staty;
      this.planInfoStatus = planInfoStatus
      console.log('审核状态', staty, this.planInfoStatus)
      if(id){
        this.queryParams.projectId = id
        this.dialogVisible = true;
        this.getList();
        this.projectLists(id);
      }
    },
    projectLists(id){
      projectFundList({applyProjectId:id}).then(res =>{
        if(res.code == 200){
          this.projectList = res.rows;
        }
      })
    },
    getList() {
      this.enterList=[]
      this.loading = true;
      const currentYear = new Date().getFullYear();
      // 获取选择的日期的年份
      listProjectPlan(this.queryParams).then(response => {
        this.total = response.total;
        if(Number(this.total) > 0){
          this.disabled=true
          this.enterList = response.rows;
          this.enterList.forEach((res,index)=>{
            let time = new Date(res.planTime).getFullYear()
            if(this.planInfoStatus && time != currentYear && (this.staty==1||this.staty==2)){
                res.disabled = true
            }else{
              if(this.staty ==0 || !this.planInfoStatus){
                if(time != currentYear ){
                  res.disabled = true
                }else {
                  res.disabled = false;
                }
              }
            }
          })
        }else{
          this.disabled=false
          this.enterList.push({
            id:'',
            projectId:this.queryParams.projectId,
            planTime:'',
            sourceOfFunds:'',
            yearInvestment:'',
            planInfo:'',
            disabled:false
          })
        }
        this.loading = false;
      });
    },
    /**增加*/
    handleAdd(){
      this.enterList.push({
        id:'',
        projectId:this.queryParams.projectId,
        planTime:'',
        sourceOfFunds:'',
        yearInvestment:'',
        planInfo:'',
      })
    },
    // /** 删除按钮操作 */
    handleDelete(row,indexDelete) {
      if(row.id){
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除全链条一览编号为"' + ids + '"的数据项？').then(function() {
          return delProjectPlan(ids);
        }).then(() => {
          this.enterList.splice(indexDelete, 1)
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      }else {
       this.enterList.splice(indexDelete, 1)
      }

    },
    submitForm(){
      if(this.staty ==0 || !this.planInfoStatus){
        if(!this.areAllShow){
          this.$modal.msgError("表格中部分数据为必填项,请完成输入");
          return;
        }
        let projectPlanInfoList = this.enterList
        addProjectPlan(projectPlanInfoList).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.dialogVisible = false;
          this.$emit('refresh')
        });
      }else {
        this.dialogVisible = false;
      }
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      let projectPlanInfoList = this.enterList
      updateProjectPlan(projectPlanInfoList).then(response => {
        this.$modal.msgSuccess("修改成功");
        this.getList();
      });
    },
  }
}
</script>
<style scoped>
.totalMoney {
  margin:0 20px 10px 0;
  font-size: 16px;
  color: #1890ff;
  font-weight: bold;
}
/deep/.el-dialog__body{
  padding: 0 20px;
}
/deep/.el-date-picker__next-btn{
  display: none;
}
</style>
