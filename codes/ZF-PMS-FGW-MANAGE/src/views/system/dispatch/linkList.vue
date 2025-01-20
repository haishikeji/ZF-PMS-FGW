<template>
<div>
  <el-dialog title="全链条计划审核" :visible.sync="show">
    <div>
      <span class="totalMoney">总投资(万元)：{{money}}</span>
      <div class="totalMoney" v-for="(item,index) in projectList" :key="index" style="display: inline-block;">
        <dict-tag :options="dict.type.source_of_funds" :value="item.sourceOfFunds" style="display: inline-block;"/>
        <span>(万元):{{item.totalInvestment}}</span>
      </div>
    </div>
    <el-table v-loading="loading" :data="enterList" ref="multipleTable">
      <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
      <el-table-column prop="planTime" label="时间" width="180" align="center"></el-table-column>
      <el-table-column prop="yearInvestment" label="投资金额(万元)" align="center"  min-width="100"></el-table-column>
      <el-table-column prop="planInfo" label="内容" align="center" min-width="150" show-overflow-tooltip></el-table-column>
    </el-table>
    <el-form ref="form" :model="form" label-width="80px" style="margin-top: 20px">
      <el-form-item label="是否同意" prop="status" required>
        <el-radio v-model="form.status" :label="1">同意</el-radio>
        <el-radio v-model="form.status" :label="0">不同意</el-radio>
      </el-form-item>
      <el-form-item label="原因" prop="auditOpinion">
        <el-input v-model="form.auditOpinion" type="textarea" placeholder="请输入原因" />
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submit">确 定</el-button>
      <el-button @click="show = false">取 消</el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>
import {auditOpinion, listProjectPlan, projectFundList} from "@/api/system/projectPlan/projectPlan";
export default {
  dicts:['source_of_funds'],
  data(){
    return{
      loading: true,
      show:false,
      enterList:[],
      total: 0,
      queryParams: {
        projectId:null,
        pageNum: 1,
        pageSize: 999,
      },
      money:null,
      projectList:[],
      form:{
        projectId:'',
        status: null,
        auditOpinion: ''
      }
    }
  },
  methods:{
    open(id,money){
      this.form.status = null;
      this.form.auditOpinion = '';
      this.money = money;
      if(id){
        this.form.projectId = id;
        this.queryParams.projectId = id
        this.show = true;
        this.getList();
        this.projectLists(id);
      }
    },
    getList() {
      this.enterList=[]
      this.loading = true;
      listProjectPlan(this.queryParams).then(response => {
        this.total = response.total;
        if(Number(this.total) > 0){
          this.enterList = response.rows;
        }
        this.loading = false;
      });
    },
    projectLists(id){
      projectFundList({applyProjectId:id}).then(res =>{
        if(res.code == 200){
          this.projectList = res.rows;
        }
      })
    },
    submit(){
      if(!(this.form.status === 0 || this.form.status === 1)){
        this.$modal.msgError("请完成审核");
        return;
      }else if(this.form.status == 0 && !this.form.auditOpinion){
        this.$modal.msgError("不同意,请输入原因");
        return;
      }
      auditOpinion(this.form).then(res => {
        if (res.code == 200) {
          this.show = false;
          this.$modal.msgSuccess("审核成功");
        } else {
          this.$modal.msgError("审核失败");
        }
        this.$emit('close')
      });

    }
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
  padding: 0 20px 30px;
}
/deep/ .el-table__body td.el-table__cell{
  background-color: #F5F7FA;
}
/deep/.el-table td.el-table__cell{
  border: none;
}
</style>
