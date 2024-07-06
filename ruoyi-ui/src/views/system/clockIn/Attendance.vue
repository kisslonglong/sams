<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>打卡页面</span>
      </div>
      <div class="card-content">
        <el-row :gutter="20">
          <el-col :span="24" class="text-center">
            <el-button
              class="clock-button"
              type="primary"
              circle
              @click="handleClock"
              :disabled="isClockedIn && isClockedOut"
            >
              {{ buttonText }}
            </el-button>
          </el-col>
        </el-row>
        <el-row :gutter="20" class="margin-top">
          <el-col :span="24">
            <el-button type="warning" @click="applyLeave"> 请假 </el-button>
          </el-col>
        </el-row>
        <el-row :gutter="20" class="margin-top">
          <el-col :span="24">
            <el-alert
              v-if="message"
              :title="message"
              type="info"
              show-icon
            ></el-alert>
          </el-col>
        </el-row>
      </div>
    </el-card>
    <el-dialog :visible.sync="open" :title="title" width="600px">
        <el-form ref="form" :model="form" label-width="80px">
            <el-input v-model="form.reason" label="请假理由" />
        </el-form>
        <div slot="footer">
            <el-button @click="subAsk">确认</el-button>
            <el-button @click="open=false">取消</el-button>
        </div>
    </el-dialog>
  </div>
</template>

<script>
import { userCheckIn,leavere } from "@/api/system/check";

export default {
  data() {
    return {
      isClockedIn: false,
      isClockedOut: false,
      message: "",
      form:{},
      open:false,
      title:"请假"
    };
  },
  computed: {
    buttonText() {
      const hours = this.getCurrentTime();
      return hours < 12 ? "上班打卡" : "下班打卡";
    },
  },
  methods: {
    getCurrentTime() {
      return new Date().getHours();
    },
    handleClock() {
      const hours = this.getCurrentTime();
      if (hours < 12) {
        if (!this.isClockedIn) {
          this.isClockedIn = true;
        //   this.message = "上班打卡成功";
          userCheckIn({
            type: 1,
          }).then((res) => {
            this.message = "上班打卡成功";
          });
        } else {
          this.message = "已经上班打卡";
        }
      } else {
        if (!this.isClockedOut) {
          this.isClockedOut = true;
          userCheckIn({
            type: 2,
          }).then((res) => {
            this.message = "下班打卡成功";
          });
        } else {
          this.message = "已经下班打卡";
        }
      }
    },
    subAsk(){
        leavere(this.form).then(res=>{
            this.open = false
      this.message = "请假申请已提交";

        })
    },
    applyLeave() {
    //   this.message = "请假申请已提交";
        this.open = true
    },
  },
};
</script>

<style scoped>
.card-content {
  padding: 20px;
}

.margin-top {
  margin-top: 20px;
}

.text-center {
  text-align: center;
}

.clock-button {
  width: 100px;
  height: 100px;
  font-size: 18px;
  border-radius: 50%;
  transition: all 0.5s ease;
  background: linear-gradient(45deg, #42b983, #6c5ce7);
  border: none;
  color: #fff;
}

.clock-button:hover {
  transform: scale(1.2) rotate(360deg);
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.4);
}

.clock-button:disabled {
  background: linear-gradient(45deg, #bdc3c7, #2c3e50);
  cursor: not-allowed;
}
</style>
