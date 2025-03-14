<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="办理时间" prop="recordDate">
        <el-date-picker clearable
          v-model="queryParams.recordDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择办理时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="业务类型" prop="marketingType">
        <el-select v-model="queryParams.marketingType" placeholder="业务类型" clearable>
          <el-option
            v-for="dict in dict.type.marketing_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="区县名称" prop="countyName">
        <el-select v-model="queryParams.countyName" placeholder="请选择区县名称" clearable>
          <el-option
            v-for="dict in dict.type.county_name"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['springmarketing:businesscount:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['springmarketing:businesscount:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['springmarketing:businesscount:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['springmarketing:businesscount:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="businesscountList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键id" align="center" prop="id" /> -->
      <el-table-column label="业务类型" align="center" prop="marketingType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.marketing_type" :value="scope.row.marketingType"/>
        </template>
      </el-table-column>
      <el-table-column label="区县名称" align="center" prop="countyName">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.county_name" :value="scope.row.countyName"/>
        </template>
      </el-table-column>
      <el-table-column label="业务数量" align="center" prop="businessCount" />
      <el-table-column label="办理时间" align="center" prop="recordDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['springmarketing:businesscount:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['springmarketing:businesscount:remove']"
          >删除</el-button>
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

    <!-- 添加或修改业务办理统计表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="办理时间" prop="recordDate">
          <el-date-picker clearable
            v-model="form.recordDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择办理时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="业务类型" prop="marketingType">
          <el-select v-model="form.marketingType" placeholder="业务类型">
            <el-option
              v-for="dict in dict.type.marketing_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="区县名称" prop="countyName">
          <el-select v-model="form.countyName" placeholder="请选择区县名称">
            <el-option
              v-for="dict in dict.type.county_name"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="业务数量" prop="businessCount">
          <el-input v-model="form.businessCount" placeholder="请输入业务数量" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBusinesscount, getBusinesscount, delBusinesscount, addBusinesscount, updateBusinesscount } from "@/api/springmarketing/businesscount";

export default {
  name: "Businesscount",
  dicts: ['county_name', 'marketing_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 业务办理统计表表格数据
      businesscountList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        recordDate: null,
        marketingType: null,
        countyName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询业务办理统计表列表 */
    getList() {
      this.loading = true;
      listBusinesscount(this.queryParams).then(response => {
        this.businesscountList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        recordDate: null,
        marketingType: null,
        countyName: null,
        countyId: null,
        businessCount: null
      };
      this.resetForm("form");
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
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加业务办理统计表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBusinesscount(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改业务办理统计表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBusinesscount(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBusinesscount(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除业务办理统计表编号为"' + ids + '"的数据项？').then(function() {
        return delBusinesscount(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('springmarketing/businesscount/export', {
        ...this.queryParams
      }, `businesscount_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
