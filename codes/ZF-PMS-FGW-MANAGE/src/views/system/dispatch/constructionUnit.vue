
<template>
<div>
  <el-dialog title="建设单位录入" :visible.sync="buildShow" width="70%">
    <el-form ref="form" :model="form" label-width="155px">
      <div>
        <el-button type="primary" @click="add()" style="margin-bottom:10px;">新增</el-button>
      </div>
      <div v-for="(item,index) in form.projectConstructionUnitList" :key="index">
        <el-row>
          <el-col :span="8">
            <el-form-item prop="inventoryItem"  label-width="100px">
              <template #label>
                <span style="color: red;">*</span>建设单位
              </template>
              <el-select v-model="item.constructionUnitName" @change="handleSelectDept(index,$event)" placeholder="请选择建设单位">
                <el-option
                  v-for="dict in companyList"
                  :key="dict.deptName"
                  :label="dict.deptName"
                  :value="dict.deptName"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="constructionPersonName">
              <template #label>
                <span style="color: red;">*</span>建设单位联系人
              </template>
              <el-select v-model="item.constructionPersonName" @change="handleSelectDuty(index,$event)"
                         placeholder="请选择建设单位联系人">
                <el-option
                  v-for="dict in dutyList"
                  :key="dict.nickName"
                  :label="dict.nickName"
                  :value="dict.nickName"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item prop="constructionPersonPhone">
              <template #label>
                <span style="color: red;">*</span>建设单位联系人电话
              </template>
              <el-input v-model="item.constructionPersonPhone" placeholder="请输入建设单位联系人电话"
                        onkeyup="value=value.replace(/[^\d]/g,'')" maxlength="11"/>
            </el-form-item>
          </el-col>
          <div style="width:10%;text-align: center;float: right;">
            <el-button @click="deleteList(index,item.id)">删除</el-button>
          </div>
        </el-row>
      </div>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="submitStatus">确 定</el-button>
      <el-button @click="buildShow = false">取 消</el-button>
    </div>
  </el-dialog>
</div>
</template>
<script>
import {
  deptList,
  userList,
  addConstructionUnit,
  constructionUnit,
  deletCconstructionUnit, responsibleUnit
} from "@/api/system/apply/apply";
export default {
  data(){
    return{
      buildShow:false,
      id:'',
      name:'',
      form:{
        projectConstructionUnitList:[
          {
            projectId:'',
            projectName:'',
            constructionUnit:'',
            constructionUnitName:'',
            constructionPersonName:'',
            constructionPersonId:'',
            constructionPersonPhone:'',
          }
        ],
      },

      companyList:[],
      dutyList:[],//联系人列表
    }
  },
  computed:{
    areAllPropertiesFilled(){
      console.log('获取填写的数据',this.form.list)
      return  this.form.projectConstructionUnitList.every(item =>
        item.constructionUnit !== undefined && item.constructionUnit !== '' && item.constructionUnit !== null &&
        item.constructionUnitName !== undefined && item.constructionUnitName !== '' && item.constructionUnitName !== null &&
        item.constructionPersonName !== undefined && item.constructionPersonName !== '' && item.constructionPersonName !== null &&
        item.constructionPersonPhone !== undefined && item.constructionPersonPhone !== '' && item.constructionPersonPhone !== null)
    },
  },
  methods:{
    open(id,name){
      this.id = id;
      this.name = name;
      constructionUnit({projectId:this.id}).then(res =>{
        if (res.total>0){
          this.form.projectConstructionUnitList = res.rows
        }else {
          this.form.projectConstructionUnitList=[
            {
              projectId:this.id,
              projectName:this.name,
              constructionUnit:'',
              constructionUnitName:'',
              constructionPersonName:'',
              constructionPersonId:'',
              constructionPersonPhone:'',
            }
          ]
        }
      })
      this.buildShow = true;
      deptList().then(res =>{
        this.companyList = res.data;
      })
    },
    add(){
      let str = {
        projectId:this.id,
        projectName:this.name,
        constructionUnit:'',
        constructionUnitName:'',
        constructionPersonName:'',
        constructionPersonId:'',
        constructionPersonPhone:'',
      }
      this.form.projectConstructionUnitList.push(str)
    },
    deleteList(index,id){
        if (this.form.projectConstructionUnitList.length > 1) {
          if(id) {
            this.$modal.confirm('是否确认删除当前建设单位？').then(function () {
              deletCconstructionUnit(id).then(res => {})
            }).then(() => {
              this.form.projectConstructionUnitList.splice(index, 1)
              this.$modal.msgSuccess("删除成功");
            }).catch(() => {
              this.$modal.msgError("删除失败");
            })
          }else {
            this.form.projectConstructionUnitList.splice(index, 1)
          }
        }else {
          this.$modal.msgWarning("最少保留一条数据");
        }


    },
    //项目选择责任单位，联动查询责任单位联系人列表
    handleSelectDept(index,value) {
      if (value) {
        const selected = this.companyList.find(option => option.deptName === value);
        this.form.projectConstructionUnitList[index].constructionUnit = selected.deptId;
        this.getName(selected.deptId);
        this.handleSelectDuty(index,null);
      } else {
        this.form.projectConstructionUnitList[index].constructionUnit = null;
        this.form.projectConstructionUnitList[index].constructionPersonName = null;
        this.form.projectConstructionUnitList[index].constructionPersonId = null //项目联系人电话
        this.form.projectConstructionUnitList[index].constructionPersonPhone = null;
        this.dutyList = [];
        return
      }
    },
    //责任单位联系人列表
    getName(id) {
      let parme = {
        pageNum: 1,
        pageSize: 9999,
        deptId: id
      }
      userList(parme).then(res => {
        this.dutyList = res.rows
      });
    },
    //选择单位联系人，联动回显单位联系方式
    handleSelectDuty(index,value) {
      if (value) {
        const selectedOption = this.dutyList.find(option => option.nickName === value);
        console.log('获取用户选择',selectedOption)
        this.form.projectConstructionUnitList[index].constructionPersonId = selectedOption.userId //项目联系人电话
        this.form.projectConstructionUnitList[index].constructionPersonPhone = selectedOption.phonenumber;
      } else {
        this.form.projectConstructionUnitList[index].constructionPersonId = null //项目联系人电话
        this.form.projectConstructionUnitList[index].constructionPersonPhone = null;
      }
    },
    submitStatus(){
      if(!this.areAllPropertiesFilled){
        this.$modal.msgWarning("部分列表未填写，请填写完再提交");
        return;
      }
      addConstructionUnit(this.form.projectConstructionUnitList).then(res =>{
        if (res.code == 200) {
          this.$modal.msgSuccess("录入成功");
          this.buildShow = false;
        } else {
          this.$modal.msgError(res.msg)
        }
      })
    }

  }
}

</script>

<style scoped>
/deep/.el-dialog__body{
  padding:0 20px;
}
/deep/.el-col-8 {
  width: 30%;
  margin-bottom: 10px;
}
/deep/.el-input {
  width: 100%;
}

/deep/ .el-form-item--medium .el-form-item__content {
  height: 36px;
}

.dialog-footer {
  text-align: right;
}
</style>
