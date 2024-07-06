<template>
  <div class="wrap">
    <el-form>
      <el-row>
        <el-col :span="2"></el-col>
        <el-col :span="12">
          <el-form-item label="省市区" label-width="105px">
            <el-select
              v-model="provinceValue"
              placeholder="省份"
              @change="provinceE"
            >
              <el-option
                v-for="item in province"
                :key="item.value"
                :label="item.name"
                :value="item.name"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="地级市" label-width="80px">
            <el-select v-model="cityValue" placeholder="市级" @change="cityE">
              <el-option
                v-for="item in city"
                :key="item.value"
                :label="item.name"
                :value="item.name"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="区县" label-width="80px">
            <el-select v-model="areaValue" placeholder="区县" @change="areaE">
              <el-option
                v-for="item in area"
                :key="item.value"
                :label="item.name"
                :value="item.name"
              ></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4"></el-col>
      </el-row>
      <el-row>
        <el-col :span="21">
          <el-form-item label="详细地址" label-width="75px">
            <el-select
              style="width: 97%; margin: auto"
              v-model="address"
              filterable
              remote
              reserve-keyword
              clearable
              placeholder="详细地址"
              :remote-method="remoteMethod"
              :loading="loading"
              @change="tipChange"
            >
              <el-option
                v-for="item in tips"
                :key="item.id"
                :label="item.name"
                :value="item.id"
              >
                <span style="float: left">{{ item.name }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px">{{
                  item.district
                }}</span>
              </el-option>
              <i slot="suffix" class="el-input__icon el-icon-date"></i>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="22">
          <div
            ref="mapContainer"
            style="width: 95%; height: 300px; margin: auto"
          ></div>
        </el-col>
      </el-row>
      <el-row style="margin-top: 15px">
        <el-col :span="11">
          <el-form-item label="纬度" prop="latitude" label-width="120px">
            <el-input v-model="latitude" placeholder="请输入纬度" />
          </el-form-item>
        </el-col>
        <el-col :span="11">
          <el-form-item label="经度" prop="longitude" label-width="120px">
            <el-input v-model="longitude" placeholder="请输入经度" />
          </el-form-item>
        </el-col>
        <el-col :span="2"></el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
import AMapLoader from "@amap/amap-jsapi-loader";
import { key } from "./index";

export default {
  name: "mapView",
  props: {
    value: {
      type: Array,
      default: () => [117.086963, 36.201784],
    },
  },
  data() {
    return {
      address: "",
      loading: false,
      latitude: "",
      longitude: "",
      map: null,
      zoom: 13,
      center: [117.086963, 36.201784],
      geocoder: null,
      districtSearch: null,
      province: [],
      city: [],
      area: [],
      provinceValue: "",
      cityValue: "",
      areaValue: "",
      tips: [],
    };
  },
  watch: {
    provinceValue(newVal) {
      if (this.isEmptyStr(newVal) || this.province.length === 0) return;
      if (this.provinceValue !== newVal) this.provinceE(newVal);
    },
  },
  mounted() {
    this.loadMap();

    console.log(typeof this.value);
    console.log(this.value);
    if (this.value == null || this.value == undefined) {
      return;
    }
    if (Array.isArray(this.value)) {
      this.center = this.value;
      this.longitude = this.value[0];
      this.latitude = this.value[1];
    } else if (typeof this.value === "object") {
      this.center = [this.value.longitude, this.value.latitude];
      this.longitude = this.value.longitude;
      this.latitude = this.value.latitude;
      this.address = this.value.address;
    }
  },
  methods: {
    isEmptyStr(s) {
      return !s;
    },
    loadMap(lonLat) {
      this.resetForm();
      AMapLoader.load({
        key,
        version: "2.0",
        plugins: [
          "AMap.DistrictSearch",
          "AMap.Geocoder",
          "AMap.AutoComplete",
          "AMap.PlaceSearch",
          "AMap.IndoorMap",
        ],
      }).then(() => {
        this.initMap();
        this.map.on("click", this.mapClick);
        this.initGeocoder();
        this.initDistrictSearch();
        if (lonLat) this.regeoCode(lonLat);
      });
    },
    resetForm() {
      this.address = "";
      this.provinceValue = "";
      this.cityValue = "";
      this.areaValue = "";
      this.latitude = "";
      this.longitude = "";
    },
    initMap() {
      this.map = new window.AMap.Map(this.$refs.mapContainer, {
        resizeEnable: true,
        zoom: this.zoom,
        keyboardEnable: false,
        center: this.center,
      });
    },
    initGeocoder() {
      this.geocoder = new window.AMap.Geocoder({
        city: "010",
        radius: 500,
        extensions: "all",
      });
    },
    initDistrictSearch() {
      this.districtSearch = new window.AMap.DistrictSearch({
        subdistrict: 3,
        extensions: "base",
      });
      this.districtSearch.search("中国", (status, result) => {
        this.province = result.districtList[0].districtList;
      });
    },
    initAutoComplete(queryValue) {
      const autoComplete = new window.AMap.AutoComplete({
        city: "全国",
        zoom: this.zoom,
      });
      autoComplete.search(queryValue, (status, result) => {
        this.tips = (result.tips || []).filter((item) => item.id !== "");
        this.loading = false;
      });
    },
    mapClick(e) {
      if (this.map) {
        this.lonLat = `${e.lnglat.lng},${e.lnglat.lat}`;
        this.regeoCode(this.lonLat);
      }
    },
    regeoCode(lonLat) {
      if (lonLat) {
        const [lng, lat] = lonLat.split(",");
        this.latitude = lat;
        this.longitude = lng;
        this.$emit("locationChange", [lat, lng]);
        this.addMapMarker([lng, lat]);
        this.getAddress([lng, lat]);
        this.updateValue();
      }
    },
    addMapMarker(lnglat) {
      if (lnglat) {
        if (this.projectMapMarker) {
          this.map.remove(this.projectMapMarker);
          this.lonLat = "";
        }
        this.projectMapMarker = new window.AMap.Marker({
          position: new window.AMap.LngLat(lnglat[0], lnglat[1]),
        });
        this.map.add(this.projectMapMarker);
        this.map.setCenter(lnglat);
        this.map.setZoom(16);
        this.projectMapMarker.setPosition(lnglat);
      }
    },
    getAddress(lnglat) {
      this.geocoder.getAddress(lnglat, (status, result) => {
        if (status === "complete" && result.info === "OK" && result.regeocode) {
          const resp = result.regeocode.addressComponent;
          this.provinceValue = resp.province;
          this.cityValue = resp.city;
          this.areaValue = resp.district;
          this.address = result.regeocode.formattedAddress;
          this.$emit("locationAddress", result.regeocode);
        } else {
          this.$emit("locationFail", "地址查询位置失败,请检查地址是否正确");
        }
      });
    },
    provinceE(newVal) {
      this.city =
        this.province.find((item) => item.name === newVal)?.districtList || [];
      this.setDistrict(newVal, "province");
      this.cityValue = "";
      this.areaValue = "";
      this.address = "";
    },
    cityE(newVal) {
      this.area =
        this.city.find((item) => item.name === newVal)?.districtList || [];
      this.setDistrict(newVal, "city");
      this.areaValue = "";
      this.address = "";
    },
    areaE(newVal) {
      if (newVal) this.setDistrict(newVal, "district");
      this.address = "";
    },
    setDistrict(value, level) {
      let adcode;
      this[level].forEach((item) => {
        if (item.name === value) adcode = item.adcode;
      });
      this.districtSearch.setLevel(level);
      this.districtSearch.search(adcode, (status, result) => {
        if (result.districtList[0]) {
          this.map.setCenter([
            result.districtList[0].center.lng,
            result.districtList[0].center.lat,
          ]);
        }
      });
    },
    remoteMethod(query) {
      if (query) {
        const queryValue = `${this.provinceValue}${this.cityValue}${this.areaValue}${query}`;
        this.loading = true;
        AMap.plugin("AMap.AutoComplete", () =>
          this.initAutoComplete(queryValue)
        );
      } else {
        this.tips = [];
      }
    },
    tipChange(value) {
      const tip = this.tips.find((tip) => tip.id === value);
      if (tip) {
        this.lonLat = `${tip.location.lng},${tip.location.lat}`;
        this.regeoCode(this.lonLat);
      }
    },
    updateValue() {
      console.log(typeof this.value)
      if (typeof this.value == "array") {
        const newValue = [this.latitude, this.longitude, this.value.address];
        this.$emit("input", newValue);
      }
      if (typeof this.value == "object") {
        const newValue = {
          latitude: this.latitude,
          longitude: this.longitude,
          address: this.address,
        };
        this.$emit("input", newValue);
      }
    },
  },
};
</script>

<style scoped>
.el-row {
  display: flex;
  justify-content: space-evenly;
}
</style>
