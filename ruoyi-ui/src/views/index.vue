<template>
  <div class="dashboard-editor-container">
    <!-- <panel-group @handleSetLineChartData="handleSetLineChartData" /> -->

    <el-row style="background: #fff; padding: 16px 16px 0; margin-bottom: 32px">
      <line-chart
        v-if="lineStatus"
        :chatDate="chatDate"
        :legend="legend"
        :chart-data="lineChartData"
      />
    </el-row>

    <!-- <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart />
        </div>
      </el-col>
    </el-row> -->
  </div>
</template>

<script>
import PanelGroup from "./dashboard/PanelGroup";
import LineChart from "./dashboard/LineChart";
import RaddarChart from "./dashboard/RaddarChart";
import PieChart from "./dashboard/PieChart";
import BarChart from "./dashboard/BarChart";
import { getLineData } from "@/api/system/home";
const lineChartData = {
  newVisitis: {
    // expectedData: [100, 120, 161, 134, 105, 160, 165],
    // actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130],
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130],
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130],
  },
};

export default {
  name: "Index",
  components: {
    PanelGroup,
    LineChart,
    RaddarChart,
    PieChart,
    BarChart,
  },
  data() {
    return {
      chatDate: [
        "2018-01-01",
        "2018-01-02",
        "2018-01-03",
        "2018-01-04",
        "2018-01-05",
        "2018-01-06",
        "2018-01-07",
        "2018-01-08",
      ],
      lineStatus: false,
      lineChartData: lineChartData.newVisitis,
      legend: [],
    };
  },
  mounted() {
    this.getLine();
  },
  methods: {
    async getLine() {
      const resp = await getLineData();
      // console.log(resp)
      this.chatDate = resp.data.year;
      // this.lineChartData = lineChartData
      for (let key in resp.data) {
        console.log(key);
        if (key == "year") {
          continue;
        }
        this.lineChartData[key] = resp.data[key];
        this.legend.push(key);
      }
      this.lineStatus = true;
      console.log(this.lineChartData);
    },
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type];
    },
  },
};
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
