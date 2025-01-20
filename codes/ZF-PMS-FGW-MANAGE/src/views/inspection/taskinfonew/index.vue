<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="联防区id" prop="unionId">
        <el-input
          v-model="queryParams.unionId"
          placeholder="请输入联防区id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="联防区名称" prop="unionName">
        <el-input
          v-model="queryParams.unionName"
          placeholder="请输入联防区名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="部门id" prop="deptId">
        <el-input
          v-model="queryParams.deptId"
          placeholder="请输入部门id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="企业名称" prop="deptName">
        <el-input
          v-model="queryParams.deptName"
          placeholder="请输入部门名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="任务类型" prop="taskType">
        <el-select v-model="queryParams.taskType" placeholder="请选择任务类型" clearable>
          <el-option
            v-for="dict in dict.type.work_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item label="走访人员id" prop="taskUserId">
        <el-input
          v-model="queryParams.taskUserId"
          placeholder="请输入走访人员id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="上报人员名称" prop="taskUserName">
        <el-input
          v-model="queryParams.taskUserName"
          placeholder="请输入走访人员名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="记录时间" prop="recordTime">
        <el-date-picker clearable
          v-model="queryParams.recordTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择记录时间">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="走访地址" prop="taskAddress">
        <el-input
          v-model="queryParams.taskAddress"
          placeholder="请输入走访地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="治安问题" prop="problem1Flag">
        <el-select v-model="queryParams.problem1Flag" placeholder="请选择是否存在治安问题" clearable>
          <el-option
            v-for="dict in dict.type.tenant_flag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="上报人" prop="reportUser">
        <el-input
          v-model="queryParams.reportUser"
          placeholder="请输入上报人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="上报人id" prop="reportUserId">
        <el-input
          v-model="queryParams.reportUserId"
          placeholder="请输入上报人id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="上报人部门id" prop="reportDeptId">
        <el-input
          v-model="queryParams.reportDeptId"
          placeholder="请输入上报人部门id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item label="生产问题" prop="problem2Flag">
        <el-select v-model="queryParams.problem2Flag" placeholder="请选择是否存在生产问题" clearable>
          <el-option
            v-for="dict in dict.type.tenant_flag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="消防问题" prop="problem3Flag">
        <el-select v-model="queryParams.problem3Flag" placeholder="请选择是否存在消防问题" clearable>
          <el-option
            v-for="dict in dict.type.tenant_flag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="纠纷排除" prop="problem4Flag">
        <el-select v-model="queryParams.problem4Flag" placeholder="请选择是否存在纠纷排除" clearable>
          <el-option
            v-for="dict in dict.type.tenant_flag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="违规经营" prop="problem5Flag">
        <el-select v-model="queryParams.problem5Flag" placeholder="请选择是否存在违规经营" clearable>
          <el-option
            v-for="dict in dict.type.tenant_flag"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="车辆违规停放" prop="problem6Flag">
        <el-select v-model="queryParams.problem6Flag" placeholder="请选择是否存在车辆违规停放" clearable>
          <el-option
            v-for="dict in dict.type.tenant_flag"
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
    <!--  <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['inspection:taskinfonew:add']"
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
          v-hasPermi="['inspection:taskinfonew:edit']"
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
          v-hasPermi="['inspection:taskinfonew:remove']"
        >删除</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['inspection:taskinfonew:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskinfonewList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键id" align="center" prop="id" />
      <el-table-column label="联防区id" align="center" prop="unionId" /> -->
      <el-table-column label="联防区名称" align="center" prop="unionName" />
      <!-- <el-table-column label="部门id" align="center" prop="deptId" /> -->
      <el-table-column label="企业名称" align="center" prop="deptName" />
      <!-- <el-table-column label="任务类型" align="center" prop="taskType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.work_type" :value="scope.row.taskType"/>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="走访人员id" align="center" prop="taskUserId" /> -->
      <el-table-column label="走访人员名称" align="center" prop="taskUserName" />
      <el-table-column label="走访地址" align="center" prop="taskAddress" />
      <el-table-column label="附件地址1" align="center" prop="attachmentOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="附件地址2" align="center" prop="attachmentTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.attachmentTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="走访情况" align="center" prop="recordInfo" />
      <el-table-column label="隐患信息" align="center" prop="troubleInfo" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="记录时间" align="center" prop="recordTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.recordTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="上报人" align="center" prop="reportUser" />
      <el-table-column label="上报人id" align="center" prop="reportUserId" />
      <el-table-column label="上报人部门id" align="center" prop="reportDeptId" /> -->
      <el-table-column label="是否存在治安问题" align="center" prop="problem1Flag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.tenant_flag" :value="scope.row.problem1Flag"/>
        </template>
      </el-table-column>
      <el-table-column label="治安问题详细地址" align="center" prop="problem1Add" />
      <el-table-column label="治安问题情况" align="center" prop="problem1Info" />
      <el-table-column label="治安问题附件1" align="center" prop="problem1UrlOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem1UrlOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="治安问题附件2" align="center" prop="problem1UrlTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem1UrlTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="是否存在生产问题" align="center" prop="problem2Flag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.tenant_flag" :value="scope.row.problem2Flag"/>
        </template>
      </el-table-column>
      <el-table-column label="安全生产详细地址" align="center" prop="problem2Add" />
      <el-table-column label="安全生产问题情况" align="center" prop="problem2Info" />
      <el-table-column label="安全生产附件1" align="center" prop="problem2UrlOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem2UrlOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="安全生产附件2" align="center" prop="problem2UrlTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem2UrlTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="是否存在消防问题" align="center" prop="problem3Flag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.tenant_flag" :value="scope.row.problem3Flag"/>
        </template>
      </el-table-column>
      <el-table-column label="消防安全详细地址" align="center" prop="problem3Add" />
      <el-table-column label="消防安全问题情况" align="center" prop="problem3Info" />
      <el-table-column label="消防安全附件1" align="center" prop="problem3UrlOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem3UrlOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="消防安全附件2" align="center" prop="problem3UrlTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem3UrlTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="是否存在矛盾纠纷" align="center" prop="problem4Flag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.tenant_flag" :value="scope.row.problem4Flag"/>
        </template>
      </el-table-column>
      <el-table-column label="矛盾纠纷详情地址" align="center" prop="problem4Add" />
      <el-table-column label="矛盾纠纷问题情况" align="center" prop="problem4Info" />
      <el-table-column label="矛盾纠纷附件1" align="center" prop="problem4UrlOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem4UrlOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="矛盾纠纷附件2" align="center" prop="problem4UrlTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem4UrlTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="是否存在违规经营" align="center" prop="problem5Flag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.tenant_flag" :value="scope.row.problem5Flag"/>
        </template>
      </el-table-column>
      <el-table-column label="违规经营详情地址" align="center" prop="problem5Add" />
      <el-table-column label="违规经营问题情况" align="center" prop="problem5Info" />
      <el-table-column label="违规经营附件1" align="center" prop="problem5UrlOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem5UrlOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="违规经营附件2" align="center" prop="problem5UrlTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem5UrlTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="是否存在车辆违规停放" align="center" prop="problem6Flag">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.tenant_flag" :value="scope.row.problem6Flag"/>
        </template>
      </el-table-column>
      <el-table-column label="车辆违规停详情地址" align="center" prop="problem6Add" />
      <el-table-column label="车辆违规停问题情况" align="center" prop="problem6Info" />
      <el-table-column label="车辆违规停附件1" align="center" prop="problem6UrlOne" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem6UrlOne" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="车辆违规停附件2" align="center" prop="problem6UrlTwo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.problem6UrlTwo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['inspection:taskinfonew:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['inspection:taskinfonew:remove']"
          >删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改巡检记录新对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="联防区id" prop="unionId">
          <el-input v-model="form.unionId" placeholder="请输入联防区id" />
        </el-form-item>
        <el-form-item label="联防区名称" prop="unionName">
          <el-input v-model="form.unionName" placeholder="请输入联防区名称" />
        </el-form-item>
        <el-form-item label="部门id" prop="deptId">
          <el-input v-model="form.deptId" placeholder="请输入部门id" />
        </el-form-item>
        <el-form-item label="部门名称" prop="deptName">
          <el-input v-model="form.deptName" placeholder="请输入部门名称" />
        </el-form-item>
        <el-form-item label="任务类型" prop="taskType">
          <el-select v-model="form.taskType" placeholder="请选择任务类型">
            <el-option
              v-for="dict in dict.type.work_type"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="走访人员id" prop="taskUserId">
          <el-input v-model="form.taskUserId" placeholder="请输入走访人员id" />
        </el-form-item>
        <el-form-item label="走访人员名称" prop="taskUserName">
          <el-input v-model="form.taskUserName" placeholder="请输入走访人员名称" />
        </el-form-item>
        <el-form-item label="走访地址" prop="taskAddress">
          <el-input v-model="form.taskAddress" placeholder="请输入走访地址" />
        </el-form-item>
        <el-form-item label="附件地址1" prop="attachmentOne">
          <image-upload v-model="form.attachmentOne"/>
        </el-form-item>
        <el-form-item label="附件地址2" prop="attachmentTwo">
          <image-upload v-model="form.attachmentTwo"/>
        </el-form-item>
        <el-form-item label="是否存在治安问题" prop="problem1Flag">
          <el-select v-model="form.problem1Flag" placeholder="请选择是否存在治安问题">
            <el-option
              v-for="dict in dict.type.tenant_flag"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="走访情况" prop="recordInfo">
          <el-input v-model="form.recordInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="隐患信息" prop="troubleInfo">
          <el-input v-model="form.troubleInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="记录时间" prop="recordTime">
          <el-date-picker clearable
            v-model="form.recordTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择记录时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="上报人" prop="reportUser">
          <el-input v-model="form.reportUser" placeholder="请输入上报人" />
        </el-form-item>
        <el-form-item label="上报人id" prop="reportUserId">
          <el-input v-model="form.reportUserId" placeholder="请输入上报人id" />
        </el-form-item>
        <el-form-item label="上报人部门id" prop="reportDeptId">
          <el-input v-model="form.reportDeptId" placeholder="请输入上报人部门id" />
        </el-form-item>
        <el-form-item label="是否存在生产问题" prop="problem2Flag">
          <el-select v-model="form.problem2Flag" placeholder="请选择是否存在生产问题">
            <el-option
              v-for="dict in dict.type.tenant_flag"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否存在消防问题" prop="problem3Flag">
          <el-select v-model="form.problem3Flag" placeholder="请选择是否存在消防问题">
            <el-option
              v-for="dict in dict.type.tenant_flag"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否存在环境问题" prop="problem4Flag">
          <el-select v-model="form.problem4Flag" placeholder="请选择是否存在环境问题">
            <el-option
              v-for="dict in dict.type.tenant_flag"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="是否存在矛盾问题" prop="problem5Flag">
          <el-select v-model="form.problem5Flag" placeholder="请选择是否存在矛盾问题">
            <el-option
              v-for="dict in dict.type.tenant_flag"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="治安问题详细地址" prop="problem1Add">
          <el-input v-model="form.problem1Add" placeholder="请输入治安问题详细地址" />
        </el-form-item>
        <el-form-item label="治安问题情况" prop="problem1Info">
          <el-input v-model="form.problem1Info" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="治安问题附件1" prop="problem1UrlOne">
          <image-upload v-model="form.problem1UrlOne"/>
        </el-form-item>
        <el-form-item label="治安问题附件2" prop="problem1UrlTwo">
          <image-upload v-model="form.problem1UrlTwo"/>
        </el-form-item>
        <el-form-item label="安全生产详细地址" prop="problem2Add">
          <el-input v-model="form.problem2Add" placeholder="请输入安全生产详细地址" />
        </el-form-item>
        <el-form-item label="安全生产问题情况" prop="problem2Info">
          <el-input v-model="form.problem2Info" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="安全生产附件1" prop="problem2UrlOne">
          <image-upload v-model="form.problem2UrlOne"/>
        </el-form-item>
        <el-form-item label="安全生产附件2" prop="problem2UrlTwo">
          <image-upload v-model="form.problem2UrlTwo"/>
        </el-form-item>
        <el-form-item label="消防安全详细地址" prop="problem3Add">
          <el-input v-model="form.problem3Add" placeholder="请输入消防安全详细地址" />
        </el-form-item>
        <el-form-item label="消防安全问题情况" prop="problem3Info">
          <el-input v-model="form.problem3Info" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="消防安全附件1" prop="problem3UrlOne">
          <image-upload v-model="form.problem3UrlOne"/>
        </el-form-item>
        <el-form-item label="消防安全附件2" prop="problem3UrlTwo">
          <image-upload v-model="form.problem3UrlTwo"/>
        </el-form-item>
        <el-form-item label="环境卫生详情地址" prop="problem4Add">
          <el-input v-model="form.problem4Add" placeholder="请输入环境卫生详情地址" />
        </el-form-item>
        <el-form-item label="环境卫生问题情况" prop="problem4Info">
          <el-input v-model="form.problem4Info" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="环境卫生附件1" prop="problem4UrlOne">
          <image-upload v-model="form.problem4UrlOne"/>
        </el-form-item>
        <el-form-item label="环境卫生附件2" prop="problem4UrlTwo">
          <image-upload v-model="form.problem4UrlTwo"/>
        </el-form-item>
        <el-form-item label="矛盾纠纷详情地址" prop="problem5Add">
          <el-input v-model="form.problem5Add" placeholder="请输入矛盾纠纷详情地址" />
        </el-form-item>
        <el-form-item label="矛盾纠纷问题情况" prop="problem5Info">
          <el-input v-model="form.problem5Info" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="矛盾纠纷附件1" prop="problem5UrlOne">
          <image-upload v-model="form.problem5UrlOne"/>
        </el-form-item>
        <el-form-item label="矛盾纠纷附件2" prop="problem5UrlTwo">
          <image-upload v-model="form.problem5UrlTwo"/>
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
import { listTaskinfonew, getTaskinfonew, delTaskinfonew, addTaskinfonew, updateTaskinfonew } from "@/api/inspection/taskinfonew";

export default {
  name: "Taskinfonew",
  dicts: ['tenant_flag', 'work_type'],
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
      // 巡检记录新表格数据
      taskinfonewList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        unionId: null,
        unionName: null,
        deptId: null,
        deptName: null,
        taskType: null,
        taskUserId: null,
        taskUserName: null,
        taskAddress: null,
        attachmentOne: null,
        attachmentTwo: null,
        problem1Flag: null,
        recordInfo: null,
        troubleInfo: null,
        recordTime: null,
        reportUser: null,
        reportUserId: null,
        reportDeptId: null,
        problem2Flag: null,
        problem3Flag: null,
        problem4Flag: null,
        problem5Flag: null,
        problem1Add: null,
        problem1Info: null,
        problem1UrlOne: null,
        problem1UrlTwo: null,
        problem2Add: null,
        problem2Info: null,
        problem2UrlOne: null,
        problem2UrlTwo: null,
        problem3Add: null,
        problem3Info: null,
        problem3UrlOne: null,
        problem3UrlTwo: null,
        problem4Add: null,
        problem4Info: null,
        problem4UrlOne: null,
        problem4UrlTwo: null,
        problem5Add: null,
        problem5Info: null,
        problem5UrlOne: null,
        problem5UrlTwo: null
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
    /** 查询巡检记录新列表 */
    getList() {
      this.loading = true;
      listTaskinfonew(this.queryParams).then(response => {
        this.taskinfonewList = response.rows;
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
        unionId: null,
        unionName: null,
        deptId: null,
        deptName: null,
        taskType: null,
        taskUserId: null,
        taskUserName: null,
        taskAddress: null,
        attachmentOne: null,
        attachmentTwo: null,
        problem1Flag: null,
        recordInfo: null,
        troubleInfo: null,
        remark: null,
        recordTime: null,
        reportUser: null,
        reportUserId: null,
        reportDeptId: null,
        problem2Flag: null,
        problem3Flag: null,
        problem4Flag: null,
        problem5Flag: null,
        problem1Add: null,
        problem1Info: null,
        problem1UrlOne: null,
        problem1UrlTwo: null,
        problem2Add: null,
        problem2Info: null,
        problem2UrlOne: null,
        problem2UrlTwo: null,
        problem3Add: null,
        problem3Info: null,
        problem3UrlOne: null,
        problem3UrlTwo: null,
        problem4Add: null,
        problem4Info: null,
        problem4UrlOne: null,
        problem4UrlTwo: null,
        problem5Add: null,
        problem5Info: null,
        problem5UrlOne: null,
        problem5UrlTwo: null
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
      this.title = "添加巡检记录新";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTaskinfonew(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改巡检记录新";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTaskinfonew(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTaskinfonew(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除巡检记录新编号为"' + ids + '"的数据项？').then(function() {
        return delTaskinfonew(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('inspection/taskinfonew/export', {
        ...this.queryParams
      }, `taskinfonew_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
