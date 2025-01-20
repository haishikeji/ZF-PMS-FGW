<template>
 <span>
    <el-input
      :value="value"
      placeholder="请选择储备库"
      clearable
      @clear="clearable"
    >
      <el-button slot="append" icon="el-icon-search" @click="open"></el-button>
    </el-input>
    <el-dialog
      v-if="dialogVisible"
      :visible.sync="dialogVisible"
      width="65%"
      :close-on-click-modal="false"
      append-to-body
      title="储备库"
    >
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="168px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目审批代码" prop="projectCode">
        <el-input
          v-model="queryParams.projectCode"
          placeholder="请输入项目审批代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="隶属区域" prop="areaName">
        <el-select v-model="queryParams.areaName" placeholder="请选择隶属区域" clearable>
          <el-option
            v-for="dict in dict.type.xj_area_name"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="所属行业" prop="industry">
        <el-select v-model="queryParams.industry" placeholder="请选择所属行业" clearable>
          <el-option
            v-for="dict in dict.type.industry_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="申报年份" prop="applyYear">
        <el-date-picker clearable
                        v-model="queryParams.applyYear"
                        type="year"
                        format="yyyy"
                        value-format="yyyy"
                        placeholder="请选择申报年份">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" style="margin-left: 30px;">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="projectList" ref="multipleTable"
              @select="select"
              @row-click="rowClick"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" fixed/>
      <!-- <el-table-column label="序号" type="index" align="center" fixed/> -->
      <el-table-column label="项目审批代码" align="center" prop="projectCode" min-width="150" show-overflow-tooltip/>
      <el-table-column label="项目名称" align="center" prop="projectName" min-width="150" show-overflow-tooltip/>
      <el-table-column label="项目业主" align="center" prop="deptName"  min-width="100"  show-overflow-tooltip/>
      <!-- <el-table-column label="项目隶属区域" align="center" prop="areaName"  min-width="100"  show-overflow-tooltip/> -->
      <el-table-column label="项目隶属区域" align="center" prop="areaName" min-width="100"  show-overflow-tooltip>
        <template slot-scope="scope">
          <dict-tag :options="dict.type.xj_area_name" :value="scope.row.areaName"/>
        </template>
      </el-table-column>
      <el-table-column label="建设规模及内容" align="center" prop="projectInfo"  min-width="120"  show-overflow-tooltip/>
      <el-table-column label="开工年月" align="center" prop="beginTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.beginTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="竣工年月" align="center" prop="endTime" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="所属行业" align="center" prop="industry" min-width="80" show-overflow-tooltip>
        <template slot-scope="scope">
          <dict-tag :options="dict.type.industry_type" :value="scope.row.industry"/>
        </template>
      </el-table-column>
      <el-table-column label="项目总投资(万)" align="center" prop="totalInvestment"  min-width="120" show-overflow-tooltip/>
      <el-table-column label="项目联系人姓名" align="center" prop="contactsName"  min-width="120" show-overflow-tooltip/>
      <el-table-column label="项目联系人号码" align="center" prop="contactsPhone"  min-width="120" show-overflow-tooltip/>
      <el-table-column label="责任单位联系人姓名" align="center" prop="managerContactsName"  min-width="150" show-overflow-tooltip/>
      <el-table-column label="责任单位联系人手机号" align="center" prop="managerContactsPhone"  min-width="180" show-overflow-tooltip/>
      <el-table-column label="工程包类型" align="center" prop="projectType" min-width="100" show-overflow-tooltip>
        <template slot-scope="scope">
          <dict-tag :options="dict.type.package_project_type" :value="scope.row.projectType"/>
        </template>
      </el-table-column>
      <el-table-column label="是否市本级政府投资项目" align="center" prop="cityInvestmentFlag" width="200">
        <template slot-scope="scope">
          <span>{{scope.row.cityInvestmentFlag?'是':'否'}}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否招商项目" align="center" prop="investmentFlag" width="100">
          <template slot-scope="scope">
            <span>{{scope.row.investmentFlag?'是':'否'}}</span>
          </template>
      </el-table-column>
      <el-table-column label="申报年份" align="center" prop="applyYear" min-width="80" show-overflow-tooltip/>
      <el-table-column label="争资争项意向" align="center" prop="applyIntention"  min-width="120" show-overflow-tooltip/>
      <el-table-column label="计划投资" align="center" prop="planInvestment"  min-width="80" show-overflow-tooltip/>
      <el-table-column label="中央预算内资金" align="center" prop="cneterInverstment"  min-width="120" show-overflow-tooltip/>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" size="small" @click="submitForm()">确定 </el-button>
      <el-button size="small" @click="cancel">取消 </el-button>
    </div>
  </el-dialog>
  </span>
</template>
<script>
import {listProject} from "@/api/system/reserve/reserve";

export default {
  props:{
    value: {},
  },
  model: {
    prop: "value",
    event: "change",
  },
  dicts: ['xj_area_name','industry_type','package_project_type'],
  data(){
    return{
      dialogVisible:false,
      queryParams: {
        projectName: null,
        projectCode: null,
        areaName:null,
        industry:null,
        applyYear:null,
        status:false,
        commitStatus:true,
      },
      // 遮罩层
      loading: true,
      ids:[],
      total: 0,
      // 选中数组
      projectList: [],
    }
  },
  methods:{
    open() {
      this.dialogVisible = true;
      this.getList();
    },
    /** 查询储备库列表 */
    getList() {
      this.loading = true;
      listProject(this.queryParams).then(response => {
        this.projectList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    clearable() {
      this.$emit("update:reserveProjectId",'');
      this.$emit("update:projectCode",'');
      this.$emit("update:projectName",'');
      this.$emit("update:projectInfo", '');
      this.$emit("update:totalInvestment",'');
      this.$emit("update:contactsName",'');
      this.$emit("update:contactsPhone", '');
      this.$emit("update:cityInvestmentFlag", '');
    },
    // 取消按钮
    cancel() {
      this.dialogVisible = false;
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    submitForm(){
      this.$emit("update:reserveProjectId", this.ids[0].id);
      this.$emit("update:projectCode", this.ids[0].projectCode);
      this.$emit("update:projectName", this.ids[0].projectName);
      this.$emit("update:projectInfo", this.ids[0].projectInfo);
      this.$emit("update:totalInvestment", this.ids[0].totalInvestment);
      this.$emit("update:contactsName", this.ids[0].contactsName);
      this.$emit("update:contactsPhone", this.ids[0].contactsPhone);
      this.$emit("update:cityInvestmentFlag", this.ids[0].cityInvestmentFlag);
      this.dialogVisible = false;
    },
    select(selection, row) {
      // 清除 所有勾选项
      this.$refs.multipleTable.clearSelection()
      // 当表格数据都没有被勾选的时候 就返回
      // 主要用于将当前勾选的表格状态清除
      if(selection.length == 0) return
      this.$refs.multipleTable.toggleRowSelection(row, true);
    },
    // 单选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection;
      console.log('获取选择列表',this.ids)
    },
    // 表格某一行的单击事件
    rowClick(row, column) {
      const selectData = this.ids
      this.$refs.multipleTable.clearSelection()
      if( selectData.length == 1 ) {
        selectData.forEach(item => {
          // 判断 如果当前的一行被勾选, 再次点击的时候就会取消选中
          if (item == row) {
            this.$refs.multipleTable.toggleRowSelection(row, false);
          }
          // 不然就让当前的一行勾选
          else {
            this.$refs.multipleTable.toggleRowSelection(row, true);
          }
        })
      }
      else {
        this.$refs.multipleTable.toggleRowSelection(row, true);
      }
    },
  }
}
</script>

<style scoped lang="scss">

</style>
