<template>
    <div class="weather-forecast">
      <h1>{{cityName}}未来15天的天气预报</h1>
      <div v-if="weatherData.length>0" style="height: 35vh;overflow: scroll;">
        <div v-for="(day, index) in weatherData" :key="index" class="weather-day">
          <h3>{{ day.date }}</h3>
          <p>温度:{{day.low}}~{{ day.high }} °C</p>
          <p>天气: {{ day.text_day }}</p>
        </div>
      </div>
      <div v-else>
        <p>加载中...</p>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    props:{
        location:{
            type:String,
            default:'39.93:116.40'
        }
    },
    data() {
      return {
        weatherData: [],
        cityName:''
      };
    },
    mounted() {
      this.getWeatherData();
    },
    methods: {
      async getWeatherData() {
        const apiKey = 'SoUvJtgbRhHrAm52k';
        const cityId = 'YOUR_CITY_ID'; // 替换为你想查询的城市ID
        const url = `https://api.seniverse.com/v3/weather/daily.json?key=${apiKey}&location=${this.location}&language=zh-Hans&unit=c&start=0&days=15`;
  
        try {
          const response = await axios.get(url);
          this.weatherData = response.data.results[0].daily;
          this.cityName = response.data.results[0].location.name
          console.log(this.weatherData)
        } catch (error) {
          console.error('获取天气数据失败', error);
        }
      },
      formatDate(timestamp) {
        const date = new Date(timestamp * 1000);
        return date.toLocaleDateString();
      }
    }
  };
  </script>
  
  <style>
  .weather-forecast {
    text-align: center;
  }
  .weather-day {
    border: 1px solid #ddd;
    padding: 10px;
    margin: 10px;
    border-radius: 5px;
  }
  </style>
  