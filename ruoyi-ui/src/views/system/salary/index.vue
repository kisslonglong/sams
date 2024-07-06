<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="昵称" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="基础工资" prop="baseWage">
        <el-input
          v-model="queryParams.baseWage"
          placeholder="请输入基础工资"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="绩效工资" prop="performanceSalary">
        <el-input
          v-model="queryParams.performanceSalary"
          placeholder="请输入绩效工资"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="五险一金" prop="fiveInsurance">
        <el-input
          v-model="queryParams.fiveInsurance"
          placeholder="请输入五险一金"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合计" prop="sum">
        <el-input
          v-model="queryParams.sum"
          placeholder="请输入合计"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
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
          v-hasPermi="['system:salary:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:salary:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:salary:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:salary:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="salaryList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="姓名"  align="center" prop="name"></el-table-column>
      <!-- <el-table-column label="用户id" align="center" prop="userId" /> -->
      <el-table-column label="昵称" align="center" prop="nickName" />
      <el-table-column label="基础工资" align="center" prop="baseWage" />
      <el-table-column
        label="绩效工资"
        align="center"
        prop="performanceSalary"
      />
      <el-table-column label="五险一金" align="center" prop="fiveInsurance" />
      <el-table-column label="合计" align="center" prop="sum" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:salary:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:salary:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改员工薪资管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="用户id" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户id" />
        </el-form-item> -->
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="用户">
          <el-select v-model="form.userId" placeholder="请选择用户">
            <el-option
              v-for="item in userList"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId"
            />
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="昵称" prop="nickName">
          <el-input v-model="form.nickName" placeholder="请输入昵称" />
        </el-form-item> -->
        <el-form-item label="基础工资" prop="baseWage">
          <el-input v-model="form.baseWage" placeholder="请输入基础工资" />
        </el-form-item>
        <el-form-item label="绩效工资" prop="performanceSalary">
          <el-input
            v-model="form.performanceSalary"
            placeholder="请输入绩效工资"
          />
        </el-form-item>
        <el-form-item label="五险一金" prop="fiveInsurance">
          <el-input v-model="form.fiveInsurance" placeholder="请输入五险一金" />
        </el-form-item>
        <el-form-item label="合计" prop="sum">
          <el-input v-model="form.sum" @focus="sum" placeholder="请输入合计" />
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
import {
  listSalary,
  getSalary,
  delSalary,
  addSalary,
  updateSalary,
} from "@/api/system/salary";
import { staffList } from "@/api/system/zg_information";

export default {
  name: "Salary",
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
      // 员工薪资管理表格数据
      salaryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        nickName: null,
        baseWage: null,
        performanceSalary: null,
        fiveInsurance: null,
        sum: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      userList: [],
    };
  },
  created() {
    this.getList();
    this.getstaffList();
  },
  methods: {
    sum() {
      // Ensure all values are converted to numbers before calculation
      const baseWage = parseFloat(this.form.baseWage);
      const performanceSalary = parseFloat(this.form.performanceSalary);
      const fiveInsurance = parseFloat(this.form.fiveInsurance);

      // Check if all values are valid numbers
      if (
        !isNaN(baseWage) &&
        !isNaN(performanceSalary) &&
        !isNaN(fiveInsurance)
      ) {
        // Perform the calculation
        this.form.sum = baseWage + performanceSalary - fiveInsurance;
      } else {
        // Handle case where one or more inputs are not valid numbers
        this.form.sum = null; // Or any appropriate handling for invalid input
      }
    },

    getstaffList() {
      staffList().then((response) => {
        this.userList = response.data;
      });
    },
    /** 查询员工薪资管理列表 */
    getList() {
      this.loading = true;
      listSalary(this.queryParams).then((response) => {
        this.salaryList = response.rows;
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
        userId: null,
        nickName: null,
        baseWage: null,
        performanceSalary: null,
        fiveInsurance: null,
        sum: null,
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加员工薪资管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getSalary(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改员工薪资管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateSalary(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSalary(this.form).then((response) => {
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
      this.$modal
        .confirm('是否确认删除员工薪资管理编号为"' + ids + '"的数据项？')
        .then(function () {
          return delSalary(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/salary/export",
        {
          ...this.queryParams,
        },
        `salary_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
