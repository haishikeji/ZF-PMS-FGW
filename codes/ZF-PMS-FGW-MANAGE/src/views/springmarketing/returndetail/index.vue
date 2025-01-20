<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="省份名称" prop="provinceName">
        <el-input
          v-model="queryParams.provinceName"
          placeholder="请输入省份名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
        <!-- <el-select v-model="queryParams.provinceCode" @change="getRegion(queryParams.provinceCode)" v-if="provinceL" placeholder="请选择区县名称" clearable>
          <el-option v-for="(item,index) in provinceL"
          :value = "item.code"
          :label="item.name"
          :key = "index"
           ></el-option>
        </el-select>-->
      </el-form-item>
      <el-form-item label="城市名称" prop="cityName"> 
        <el-input
          v-model="queryParams.cityName"
          placeholder="请输入城市名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
        <!-- <el-select v-model="citySelected" v-if="cityL">
          <el-option v-for="(item,index) in cityL" :value="item.name"></el-option>
        </el-select> -->
      </el-form-item>
      <el-form-item label="返乡时间" prop="returnTime">
        <el-date-picker clearable
          v-model="queryParams.returnTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择返乡时间">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="返乡区县" prop="returnCounty">
        <el-input
          v-model="queryParams.returnCounty"
          placeholder="请输入返乡区县"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="返乡区县" prop="returnCounty">
        <el-select v-model="queryParams.returnCounty" placeholder="请选择返乡区县" clearable>
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
          v-hasPermi="['springmarketing:returndetail:add']"
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
          v-hasPermi="['springmarketing:returndetail:edit']"
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
          v-hasPermi="['springmarketing:returndetail:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['springmarketing:returndetail:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="returndetailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键id" align="center" prop="id" /> -->
      <el-table-column label="省份名称" align="center" prop="provinceName" />
      <el-table-column label="城市名称" align="center" prop="cityName" />
      <el-table-column label="返乡时间" align="center" prop="returnTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.returnTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="返乡人数" align="center" prop="returnNumber" />
      <!-- <el-table-column label="返乡区县" align="center" prop="returnCounty" /> -->
      <el-table-column label="返乡区县" align="center" prop="returnCounty">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.county_name" :value="scope.row.returnCounty"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['springmarketing:returndetail:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['springmarketing:returndetail:remove']"
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

    <!-- 添加或修改返乡人员明细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="省份名称" prop="provinceName">
          <!-- <el-input v-model="form.provinceName" placeholder="请输入省份名称" /> -->
          <el-select v-model="form.provinceCode" @change="getRegion(form.provinceCode)" v-if="provinceL" placeholder="请选择省份名称">
            <el-option v-for="(item,index) in provinceL"
            :value = "item.code"
            :label="item.name"
            :key = "index"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="城市名称" prop="cityName">
          <!-- <el-input v-model="form.cityName" placeholder="请输入城市名称" /> -->
          <el-select v-model="form.cityCode" @change="getRegionTest(form.cityCode)" placeholder="请选择城市名称">
            <el-option v-for="(item,index) in cityL"
            :value = "item.code"
            :label="item.name"
            :key = "index"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="返乡时间" prop="returnTime">
          <el-date-picker clearable
            v-model="form.returnTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择返乡时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="返乡人数" prop="returnNumber">
          <el-input v-model="form.returnNumber" placeholder="请输入返乡人数" />
        </el-form-item>
        <!-- <el-form-item label="返乡区县" prop="returnCounty">
          <el-input v-model="form.returnCounty" placeholder="请输入返乡区县" />
        </el-form-item> -->
        <el-form-item label="返乡区县" prop="returnCounty">
          <el-select v-model="form.returnCounty" placeholder="请选择返乡区县">
            <el-option
              v-for="dict in dict.type.county_name"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
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
import { listReturndetail, getReturndetail, delReturndetail, addReturndetail, updateReturndetail } from "@/api/springmarketing/returndetail";
import { province, city, area } from '@/api/vue-area.js'
export default {
  name: "Returndetail",
  dicts: ['county_name'],
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
      // 返乡人员明细表格数据
      returndetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        provinceName: null,
        cityName: null,
        returnTime: null,
        returnCounty: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      selected: {
        code: 110000,
        name: '北京市',
        parentId: 0
      },
      citySelected: {},
      areaSelected: {},
      provinceL: [],
      cityL: [],
      areaL: [],
    };
  },
  watch: {
    // selected: function () {
    //   console.log("selected"+ this.selected)
    //   // 清除区县
    //   this.citySelected = {}
    //   this.cityL = city.filter((item) => item.parentId === this.selected.code)
    //   console.log("selected"+ this.cityL)
    // },
    // citySelected: function () {
    //   console.log("citySelected"+this.citySelected)
    //   this.areaL = area.filter((item) => item.parentId === this.citySelected.code)
    // }
  },
  created() {
    this.provinceL = [...province]
    this.getList();
  },
  methods: {
    getRegion(code) {
      // console.log("enter getRegion"+code)
      // this.citySelected = {}
      
      this.cityL = city.filter((item) => item.parentId === code)
      if (this.cityL.length > 0 ) {
        this.form.cityName = this.cityL[0].name
        this.form.cityCode = this.cityL[0].code
      } else {
        this.form.cityName =undefined
        this.form.cityCode =undefined
      }
      let provinceSelected = this.provinceL.find((value,index,array)=>{
          return  value.code === code;
      })
      if (provinceSelected != undefined) {
        this.form.provinceName = provinceSelected.name
      }
      console.log("enter getRegion"+JSON.stringify(this.form))
      // console.log("enter getRegion"+JSON.stringify(citySelected))
      // console.log("selected"+ this.cityL)
    },
    getRegionTest(code) {
      let citySelected = this.cityL.find((value,index,array)=>{
          return  value.code === code;
      })
      if (citySelected != undefined) {
        this.form.cityName = citySelected.name
        this.form.cityCode = citySelected.code
      }
      this.$forceUpdate()
    },
    /** 查询返乡人员明细列表 */
    getList() {
      this.loading = true;
      listReturndetail(this.queryParams).then(response => {
        this.returndetailList = response.rows;
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
        provinceName: null,
        cityName: null,
        returnTime: null,
        returnNumber: null,
        returnCounty: null
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
      this.title = "添加返乡人员明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getReturndetail(id).then(response => {
        this.form = response.data;
        let provinceSelected = this.provinceL.find((value,index,array)=>{
          return  value.name === this.form.provinceName;
        })
        this.form.provinceCode = provinceSelected.code
        this.cityL = city.filter((item) => item.parentId === provinceSelected.code)
        let citySelected = this.cityL.find((value,index,array)=>{
          return  value.name === this.form.cityName;
        })
        this.form.cityCode = citySelected.code
        this.open = true;
        this.title = "修改返乡人员明细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateReturndetail(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReturndetail(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除返乡人员明细编号为"' + ids + '"的数据项？').then(function() {
        return delReturndetail(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('springmarketing/returndetail/export', {
        ...this.queryParams
      }, `returndetail_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
