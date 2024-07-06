<template>
    <div style="width: 100%;">
        <attendance></attendance>
        <h3>打卡记录</h3>
        <el-table v-loading="loading" :data="checkList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="用户id" align="center" prop="userId" />
      <el-table-column label="昵称" align="center" prop="nickName" />
      <el-table-column label="开始时间" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="请假理由" align="center" prop="reasonLeave" />
      <el-table-column label="是否请假1是0" align="center" prop="isAsk" />
        
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    </div>
  </template>
  
  <script>
  import Attendance from './Attendance.vue';
  import { listCheck } from "@/api/system/check";

  export default {
    components: {
      Attendance,
    },
    data() {
      return {
        form: {},
        loading: true,
  // 总条数
  total: 0,
      // 员工打卡记录表格数据
      checkList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nickName: null,
        startDate: null,
        endDate: null,
      },
      };
    },
    created(){
        this.getList()
    },
    methods: {
        getList() {
      this.loading = true;
      listCheck(this.queryParams).then(response => {
        this.checkList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    },
  };
  </script>
  
  <style scoped>

  </style>
  