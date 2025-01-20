<template>
    <div>
      <el-dialog
        v-if="dialogVisible"
        :visible.sync="dialogVisible"
        width="80%"
        :close-on-click-modal="false"
        append-to-body
        title="实施情况"
      >
<!--       <el-button type="primary" plain icon="el-icon-plus"  v-hasPermi="['system:projectRecord:add']" size="mini" @click="handleAdd" style="margin-bottom: 10px;">生成当月任务</el-button>-->
       <el-table v-loading="loading" :data="enterList">
          <el-table-column type="index" label="序号" width="50" align="center"></el-table-column>
          <el-table-column label="调度计划名称" align="center" prop="taskName" min-width="200" show-overflow-tooltip/>
          <el-table-column label="调度任务期号" align="center" prop="taskNo" min-width="100">
            <template slot-scope="scope">
              <span class="openOrter" @click="openDispatch(scope.row.id,scope.row.projectId)">{{ scope.row.taskNo}}</span>
            </template>
          </el-table-column>
          <el-table-column label="填报情况" align="center" prop="fillFormInfo"  min-width="100"  show-overflow-tooltip/>
          <el-table-column label="填报开始时间" align="center" prop="taskBeginTime"  min-width="100"  show-overflow-tooltip/>
          <el-table-column label="填报结束时间" align="center" prop="taskEndTime"  min-width="100"  show-overflow-tooltip/>
         <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
           <template slot-scope="scope">
            <el-button type="text" v-if="scope.row.show" size="small"  @click="handleUpdate(scope.row)">{{scope.row.fillFormInfo == '已填报'?'详情':'填报'}} </el-button>
           </template>
         </el-table-column>
       </el-table>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
        <div slot="footer" style="margin-top: 10px;">
         <!-- <el-button type="primary" size="small" @click="dialogVisible = false">确定 </el-button> -->
          <el-button size="small" @click="dialogVisible = false">关闭 </el-button>
        </div>
      </el-dialog>
      <el-dialog :title="title" :visible.sync="openVisible" width="1100px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="调度任务期号" prop="taskNo">
              <el-input v-model="form.taskNo" :disabled="true" placeholder="请输入调度任务期号" style="width: 220px;height: 36px;"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="应出勤施工人数" prop="planPeopleNum">
              <el-input v-model="form.planPeopleNum" :disabled="disabled" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入应出勤施工人数" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="实际现场施工人数" prop="realPeopleNum">
              <el-input v-model="form.realPeopleNum" :disabled="disabled" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="请输入实际现场施工人数" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="开工情况" prop="projectInfo">
              <el-select v-model="form.projectInfo" :disabled="disabled" style="width: 100%" placeholder="请选择开工情况" clearable>
                <el-option
                  v-for="dict in dict.type.operation_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="建设阶段" prop="constructionPhase" required>
              <el-select v-model="form.constructionPhase" :disabled="disabled" style="width: 100%" placeholder="请选择建设阶段" clearable>
                <el-option
                  v-for="dict in dict.type.construction_phase"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="是否竣工" prop="projectEnd">
              <el-radio v-model="form.projectEnd" :label="false" :disabled="disabled" class="radio-left">否</el-radio>
              <el-radio v-model="form.projectEnd" :label="true" :disabled="disabled">是</el-radio>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="项目进展情况" prop="projectProgress" required>
              <el-input type="textarea" v-model="form.projectProgress" :disabled="disabled" placeholder="请输入项目进展情况" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="存在问题及困难" prop="problemsAndDifficulties">
              <el-input type="textarea" v-model="form.problemsAndDifficulties" :disabled="disabled" placeholder="请输入存在问题及困难" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" :disabled="disabled" type="textarea" placeholder="请输入备注" />
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="调度图片" prop="attachmentInfo">
              <img :src="form.attachmentInfo" v-if="disabled" style="width: 150px;"/>
              <image-upload v-model="form.attachmentInfo" v-else :id="form.id"/>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="截止本期累计完成投资" prop="jzbqljwctz" label-width="160px">
              <el-input v-model="form.jzbqljwctz" disabled placeholder="请输入开工情况" />
            </el-form-item>
          </el-col><el-col :span="8">
          <el-form-item label="当年累计完成投资" prop="dnljwctz">
            <el-input v-model="form.dnljwctz" disabled placeholder="请输入当年累计完成投资" />
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="当年投资完成率" prop="projectInfo">
            <el-input v-model="form.dntzwcl" disabled  placeholder="请输入当年投资完成率" />
          </el-form-item>
        </el-col>
          <el-col :span="8">
            <el-form-item label="本期完成投资" prop="completionOfInvestment">
              <template #label>
                <span style="color: red;">*</span>本期完成投资
              </template>
              <el-input v-model="form.completionOfInvestment" :disabled="disabled" placeholder="请输入本期完成投资" />
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    </div>
    </template>
    <script>
    import {
      addProjectPlan,
      delProjectPlan,
      listProjectPlan,
      getProjectPlan,
      updateProjectPlan
    } from "@/api/system/projectPlan/projectPlan";

    import {recordList} from "@/api/system/apply/apply";
    import {addTaskRecord,getProjectRecord,updateProjectRecord,projectFund} from "@/api/system/projectRecord/projectRecord";

    export default {
      dicts: ['source_of_funds','construction_phase','operation_status'],
      data(){
        return{
          rules:{},
          dialogVisible:false,
          openVisible: false,
          form: {},
          id:null,
          loading:true,
          title:null,
          total: 0,
          enterList:[],
          queryParams: {
            projectId:null,
            pageNum: 1,
            pageSize: 10,
            orderByColumn:'taskEndTime',
            isAsc:'desc',
          },
          // 非多个禁用
          multiple: true,
          shiw:false,
          disabled:false,
          jzbqljwctz:0,
          dnljwctz:0,
          dntzwcl:0,
          monthlyReportingSwitch:null,
        }
      },
      watch:{
        'form.completionOfInvestment'(newVal, oldVal) {
          if(!this.form.completionOfInvestment){
            this.form.jzbqljwctz = structuredClone(this.jzbqljwctz);
            this.form.dntzwcl = structuredClone(this.dntzwcl)
            this.form.dnljwctz = structuredClone(this.dnljwctz)
          }else if(!this.disabled && this.form.completionOfInvestment ) {
            this.form.jzbqljwctz = Number(this.jzbqljwctz) + Number(this.form.completionOfInvestment)
            this.form.dnljwctz = Number(this.dnljwctz) + Number(this.form.completionOfInvestment)
            let str2 = Number(this.form.bndjhwctzje);
            let dntzwcl =( Number(this.form.dnljwctz) / str2 ) * 100
            this.form.dntzwcl = dntzwcl.toFixed(2)
          }
        },
      },
      methods:{
        filterInput(row, value,key) {
          let filteredValue = value.replace(/[^\d.]/g, '');
          filteredValue = filteredValue.replace(/\.{2,}/g, '.');
          this.$set(row, key, filteredValue);
        },
        // 取消按钮
        cancel() {
          this.openVisible = false;
          this.dialogVisible = true;
          this.reset();
        },
        // 表单重置
        reset() {
          this.form = {
              id: null,
              projectId: null,
              taskNo: null,
              projectEnd:false,
              projectInfo: null,
              fundInPlace:null,
              completionOfInvestment:null,
              planPeopleNum:null,
              realPeopleNum:null,
              progressInfo:null,
              remark:null,
              attachmentInfo:null,
              constructionPhase:null,
              taskBeginTime: null,
              projectProgress:null,
              taskEndTime: null,
              jzbqljwctz: 0,
              dnljwctz: 0,
              dntzwcl: 0,
          };
        this.resetForm("form");
        },
        submitForm() {
          if(!this.form.constructionPhase){
            this.$modal.msgError("请选择建设阶段");
            return
          }else if(!this.form.projectProgress){
            this.$modal.msgError("请完成项目进展情况");
            return
          }
           if(this.form.completionOfInvestment){
             if (this.disabled == true) {
                 this.openVisible = false;
                 this.dialogVisible = true;
                 this.getList();
             } else {
               updateProjectRecord(this.form).then(response => {
                 this.$modal.msgSuccess("修改成功");
                 this.openVisible = false;
                 this.dialogVisible = true;
                 this.getList();
               });
             }
           }else {
             this.$modal.msgError("本期完成资金未填写，请填写完再提交");
           }


        },
        open(id,show){
          this.monthlyReportingSwitch = show
          console.log('获取录入页面',id);
          if(id){
            this.queryParams.projectId = id
            this.dialogVisible = true;
            this.getList()
          }

        },
        getList() {
          this.enterList=[]
          this.loading = true;
          recordList(this.queryParams).then(response => {
            this.total = response.total;
            if(Number(this.total) > 0){
              this.enterList = response.rows;
              this.enterList.forEach((res,index)=>{
                res.show = true
              })
            }
            this.loading = false;
          });
        },
        handleAdd() {
            addTaskRecord({"projectId":this.queryParams.projectId}).then(response => {
                this.getList()
            })
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
          if(row.fillFormInfo == '已填报' || row.monthlyReportingSwitch == false){
            this.disabled = true;
            // this.$modal.msgWarning("已超过填报时间，但可以查看详情");
          }else {
            this.disabled = false;
          }
            this.reset();
            const id = row.id || this.ids
            getProjectRecord(id).then(response => {
                this.form = response.data;
                this.form.projectEnd = response.data.projectEnd || false;
                this.form.bndjhwctzje = response.data.bndjhwctzje;
                this.form.jzbqljwctz= response.data.jzbqljwctz || 0
                this.form.dnljwctz = response.data.dnljwctz || 0
                this.form.dntzwcl =response.data.dntzwcl || 0;
                this.jzbqljwctz = structuredClone(response.data.jzbqljwctz? response.data.jzbqljwctz : 0);
                this.dnljwctz = structuredClone(response.data.dnljwctz? response.data.dnljwctz : 0);
                this.dntzwcl = structuredClone(response.data.dntzwcl? response.data.dntzwcl : 0);
                if(this.form.dntzwcl == 0 || this.form.dntzwcl == null){
                  this.shiw = true
                }else {
                  this.shiw = false
                }
              this.openVisible = true;
                this.dialogVisible = false;
                this.title = "修改项目实施";
            });
        },
      }
    }
    </script>
    <style scoped>
    /deep/.el-dialog__body{
      padding: 0 20px;
    }
    /deep/.el-col-12 .el-form-item__content{
      height: 36px;
    }
    .projecrClass .el-form-item{
      width: 30%;
      float: left;
      margin-bottom: 10px;
    }
    .projecrClass .el-input{
      width: 100%;
    }
    .projecrClass /deep/.el-form-item--medium .el-form-item__content{
      height: 36px;
    }
    /deep/.el-input{
      height: 36px;
    }
    </style>
