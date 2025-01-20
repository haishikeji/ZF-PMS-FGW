<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" label-width="68px">
      <el-row>
        <el-col :span="8">
          <el-form-item label="工期状态" prop="durationState">
            <el-select v-model="queryParams.durationState" placeholder="请选择">
              <el-option label="复工" :value="0"></el-option>
              <el-option label="停工" :value="1"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
<!--        <el-col :span="8">-->
<!--          <el-form-item label="审核状态" prop="status">-->
<!--            <el-select v-model="queryParams.status" placeholder="请选择">-->
<!--              <el-option label="已审核" :value="1"></el-option>-->
<!--              <el-option label="待审核" :value="0"></el-option>-->
<!--            </el-select>-->
<!--          </el-form-item>-->
<!--        </el-col>-->
        <el-col :span="8">
          <el-form-item style="float: left">
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="10" class="mb8">
        <right-toolbar @queryTable="getList"></right-toolbar>
      </el-row>
    </el-form>
    <el-table v-loading="loading" :data="projectStatusList">
      <el-table-column label="项目名称" align="center" min-width="100" prop="applyProjectName" show-overflow-tooltip/>
      <el-table-column label="原因" align="center" prop="reason" show-overflow-tooltip min-width="100"/>
      <el-table-column label="文件路径" align="center" prop="filePath" min-width="100">
        <template slot-scope="scope">
          <el-image
              v-if="scope.row.filePath"
              style="width: 50px; height:50px"
              :src="scope.row.filePath"
              :preview-src-list="[scope.row.filePath]">
          </el-image>
          <div v-else  style=" height:50px;line-height: 50px;color: #C0C4CC;">暂无图片</div>
        </template>
      </el-table-column>
      <el-table-column label="工期状态" align="center" prop="durationState" min-width="100">
        <template slot-scope="scope">
          <div>{{ scope.row.durationState == 0 ? '复工' : scope.row.durationState == 1 ? '停工' : '审核中' }}</div>
        </template>
      </el-table-column>
      <el-table-column label="审核状态" align="center" prop="status" min-width="100">
        <template slot-scope="scope">
          <div>{{ scope.row.status == 0 ? '不同意' : scope.row.status == 1 ? '同意' : '' }}</div>
        </template>
      </el-table-column>
      <el-table-column label="审核意见" align="center" prop="auditOpinion" min-width="100"/>
      <el-table-column label="操作" align="center" fixed="right" class-name="small-padding fixed-width" width="100">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['system:projectPlan:auditOpinion']"
              v-if="scope.row.status==null"
              size="mini"
              type="text"
              icon="el-icon-view"
              @click="toExamine(scope.row)"
          >审核
          </el-button>
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
    <!--    审核-->
    <el-dialog :title="examine" :visible.sync="examineShow" width="500px" append-to-body>
      <el-form ref="examineForm" :model="examineForm" :rules="examineRules" label-width="80px">
        <el-form-item label="是否同意" prop="status" required>
          <el-radio v-model="examineForm.status" :label="1">同意</el-radio>
          <el-radio v-model="examineForm.status" :label="0">不同意</el-radio>
        </el-form-item>
        <el-form-item label="原因" prop="auditOpinion" v-if="examineForm.status == 0">
          <el-input v-model="examineForm.auditOpinion" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitExamine('examineForm')">确 定</el-button>
        <el-button @click="examineShow = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listProjectStatus,
  getProjectStatus,
  delProjectStatus,
  addProjectStatus,
  updateProjectStatus,
  auditOpinion
} from "@/api/system/projectStatus/projectStatus";

export default {
  name: "ProjectStatus",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 项目工期状态表格数据
      projectStatusList: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        durationState: null,
      },
      examine: '停工审核',
      examineShow: false,
      examineForm: {
        applyProjectId: '',
        id: '',
        status: null,
        auditOpinion: ''
      },
      examineRules: {
        status: [
          {required: true, message: "审核不能为空", trigger: "change"}
        ],
      },
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    /** 查询项目工期状态列表 */
    getList() {
      this.loading = true;
      listProjectStatus(this.queryParams).then(response => {
        this.projectStatusList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
    toExamine(row) {
      this.examineForm.applyProjectId = null;
      this.examineForm.id = null;
      this.examineForm.status = null;
      this.examineForm.auditOpinion = null;
      this.examineForm.applyProjectId = row.applyProjectId
      this.examineForm.id = row.id
      this.examineShow = true;
      if (row.durationState == 1) {
        this.examine = '停工审核'
      } else {
        this.examine = '复工审核'
      }
    },
    submitExamine(formName) {
      let that = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (that.examineForm.status == 1 || that.examineForm.status == 0 && that.examineForm.auditOpinion) {
            auditOpinion(this.examineForm).then(res => {
              if (res.code == 200) {
                that.examineShow = false;
                that.$modal.msgSuccess("审核成功");
                that.resetQuery();
              } else {
                that.$modal.msgError("审核失败");
              }
            })
            that.examineShow = false;
          } else {
            that.$modal.msgError("不同意，请输入原因");
          }
        }
      });
    }
  }
};
</script>
