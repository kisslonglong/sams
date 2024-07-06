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
      <el-form-item label="昵称" prop="nickName">
        <el-input
          v-model="queryParams.nickName"
          placeholder="请输入昵称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="sex">
        <el-select v-model="queryParams.sex" placeholder="请选择性别" clearable>
          <el-option
            v-for="dict in dict.type.sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="联系方式" prop="contactInformation">
        <el-input
          v-model="queryParams.contactInformation"
          placeholder="请输入联系方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户id" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户id"
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
          v-hasPermi="['system:zg_information:add']"
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
          v-hasPermi="['system:zg_information:edit']"
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
          v-hasPermi="['system:zg_information:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
              type="info"
              plain
              icon="el-icon-upload2"
              size="mini"
              @click="handleImport"
            >导入</el-button>
      </el-col>
      <el-col :span="1.5">
    
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:zg_information:export']"
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
      :data="zg_informationList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="姓名" align="center" prop="name" />
      <!-- <el-table-column label="昵称" align="center" prop="nickName" /> -->
      <el-table-column label="简历">
        <template slot-scope="scope">
          <!-- <el-link type="primary">主要链接</el-link> -->
          <el-button type="text" @click="goFile(scope.row)">查看</el-button>
          <!-- <el-link @click="goFile(scope.row)" type="primary">查看</el-link> -->
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center" prop="sex">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sex" :value="scope.row.sex" />
        </template>
      </el-table-column>
      <el-table-column
        label="联系方式"
        align="center"
        prop="contactInformation"
      />
      <!-- <el-table-column label="用户id" align="center" prop="userId" /> -->
      <el-table-column label="工作经历" width="700px">
        <template slot-scope="scope">
          <el-descriptions
            boder
            v-for="(item, index) in scope.row.workExperienceList"
            border
            size="mini"
            :key="index"
            :column="4"
          >
            <el-descriptions-item label="公司名称">{{
              item.companyName
            }}</el-descriptions-item>
            <el-descriptions-item label="职位">{{
              item.post
            }}</el-descriptions-item>
            <el-descriptions-item label="入职时间">{{
              item.startDate
            }}</el-descriptions-item>
            <el-descriptions-item label="离职时间">{{
              item.endDate
            }}</el-descriptions-item>
          </el-descriptions>
        </template>
      </el-table-column>
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
            v-hasPermi="['system:zg_information:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:zg_information:remove']"
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

    <!-- 添加或修改职工信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="昵称" prop="nickName">
          <el-input v-model="form.nickName" placeholder="请输入昵称" />
        </el-form-item> -->
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option
              v-for="dict in dict.type.sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="简历">
          <FileUpload v-model="form.resumeAttachments"></FileUpload>
        </el-form-item>
        <el-form-item label="联系方式" prop="contactInformation">
          <el-input
            v-model="form.contactInformation"
            placeholder="请输入联系方式"
          />
        </el-form-item>
        <el-form-item label="用户" prop="userId">
          <!-- <el-input v-model="form.userId" placeholder="请输入用户id" /> -->
          <el-select v-model="form.userId" placeholder="请选择用户" filterable>
            <el-option
              v-for="item in userList"
              :key="item.userId"
              :label="item.nickName"
              :value="item.userId"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-divider content-position="center">工作信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              icon="el-icon-plus"
              size="mini"
              @click="handleAddWorkExperience"
              >添加</el-button
            >
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              icon="el-icon-delete"
              size="mini"
              @click="handleDeleteWorkExperience"
              >删除</el-button
            >
          </el-col>
        </el-row>
        <el-table
          :data="workExperienceList"
          :row-class-name="rowWorkExperienceIndex"
          @selection-change="handleWorkExperienceSelectionChange"
          ref="workExperience"
        >
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column
            label="序号"
            align="center"
            prop="index"
            width="50"
          />
          <el-table-column label="公司名称" prop="companyName" width="150">
            <template slot-scope="scope">
              <el-input
                v-model="scope.row.companyName"
                placeholder="请输入公司名称"
              />
            </template>
          </el-table-column>
          <el-table-column label="岗位" prop="post" width="150">
            <template slot-scope="scope">
              <el-input v-model="scope.row.post" placeholder="请输入岗位" />
            </template>
          </el-table-column>
          <el-table-column label="开始时间" prop="startDate" width="240">
            <template slot-scope="scope">
              <el-date-picker
                clearable
                v-model="scope.row.startDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择开始时间"
              />
            </template>
          </el-table-column>
          <el-table-column label="结束时间" prop="endDate" width="240">
            <template slot-scope="scope">
              <el-date-picker
                clearable
                v-model="scope.row.endDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择结束时间"
              />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 用户导入对话框 -->
    <el-dialog
      :title="upload.title"
      :visible.sync="upload.open"
      width="400px"
      append-to-body
    >
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" />
            是否更新已经存在的用户数据
          </div>
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link
            type="primary"
            :underline="false"
            style="font-size: 12px; vertical-align: baseline"
            @click="importTemplate"
            >下载模板</el-link
          >
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listZg_information,
  getZg_information,
  delZg_information,
  addZg_information,
  updateZg_information,
  staffList,
  importDate,
} from "@/api/system/zg_information";
import { getToken } from "@/utils/auth";

export default {
  name: "Zg_information",
  dicts: ["sex"],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedWorkExperience: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 职工信息表格数据
      zg_informationList: [],
      // ${subTable.functionName}表格数据
      workExperienceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nickName: null,
        sex: null,
        contactInformation: null,
        userId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      userList: [],
      // 用户导入参数
      upload: {
        // 是否显示弹出层（用户导入）
        open: false,
        // 弹出层标题（用户导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的用户数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/zg_information/import",
      },
    };
  },
  created() {
    this.getList();
    this.getstaffList();
  },
  methods: {
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    submitFileForm() {
      this.$refs.upload.submit();
    },
        // 文件上传中处理
        handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download(
        "system/zg_information/importTemplate",
        {},
        `zg_template_${new Date().getTime()}.xlsx`
      );
    },
    handleImport() {
      this.upload.title = "用户导入";
      this.upload.open = true;
    },
    goFile(row) {
      console.log(row);
      location.href = process.env.VUE_APP_BASE_API + row.resumeAttachments;
    },
    getstaffList() {
      staffList().then((response) => {
        this.userList = response.data;
      });
    },
    /** 查询职工信息列表 */
    getList() {
      this.loading = true;
      listZg_information(this.queryParams).then((response) => {
        console.log(response);
        this.zg_informationList = response.rows;
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
        nickName: null,
        sex: null,
        contactInformation: null,
        userId: null,
      };
      this.workExperienceList = [];
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
      this.title = "添加职工信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      getZg_information(id).then((response) => {
        this.form = response.data;
        this.workExperienceList = response.data.workExperienceList;
        this.open = true;
        this.title = "修改职工信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.form.workExperienceList = this.workExperienceList;
          if (this.form.id != null) {
            updateZg_information(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addZg_information(this.form).then((response) => {
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
        .confirm('是否确认删除职工信息编号为"' + ids + '"的数据项？')
        .then(function () {
          return delZg_information(ids);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** ${subTable.functionName}序号 */
    rowWorkExperienceIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** ${subTable.functionName}添加按钮操作 */
    handleAddWorkExperience() {
      let obj = {};
      obj.companyName = "";
      obj.post = "";
      obj.startDate = "";
      obj.endDate = "";
      this.workExperienceList.push(obj);
    },
    /** ${subTable.functionName}删除按钮操作 */
    handleDeleteWorkExperience() {
      if (this.checkedWorkExperience.length == 0) {
        this.$modal.msgError("请先选择要删除的${subTable.functionName}数据");
      } else {
        const workExperienceList = this.workExperienceList;
        const checkedWorkExperience = this.checkedWorkExperience;
        this.workExperienceList = workExperienceList.filter(function (item) {
          return checkedWorkExperience.indexOf(item.index) == -1;
        });
      }
    },
    /** 复选框选中数据 */
    handleWorkExperienceSelectionChange(selection) {
      this.checkedWorkExperience = selection.map((item) => item.index);
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/zg_information/export",
        {
          ...this.queryParams,
        },
        `zg_information_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>
