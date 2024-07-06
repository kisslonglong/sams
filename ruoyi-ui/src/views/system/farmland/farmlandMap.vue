<template>
  <div>
    <div
      style="width: 100%; height: 92vh; margin-top: 10px"
      id="container"
    ></div>
  </div>
</template>

<script>
import settings from "../../../settings";
import AMapLoader from "@amap/amap-jsapi-loader";
import { mapInfo } from "@/api/system/farmland";

export default {
  data() {
    return {
      mapList: [],
      map: null,
    };
  },
  methods: {
    async getMarkList() {
      try {
        const res = await mapInfo();
        this.mapList = res.data;
        return res.data;
      } catch (error) {
        console.error("Failed to fetch mark list:", error);
      }
    },
    async initMap() {
      try {
        const AMap = await AMapLoader.load({
          key: settings.mapKey, // 申请好的 Web 端开发 Key，首次调用 load 时必填
          version: "2.0", // 指定要加载的 JS API 的版本，缺省时默认为 1.4.15
          plugins: ["AMap.Scale","MarkerCluster"], // 需要使用的插件列表，如比例尺'AMap.Scale'，支持添加多个如：['AMap.Scale','...','...']
          AMapUI: {
            // 是否加载 AMapUI，缺省不加载
            version: "1.1", // AMapUI 版本
            plugins: ["overlay/SimpleMarker"], // 需要加载的 AMapUI ui 插件
          },
          Loca: {
            // 是否加载 Loca， 缺省不加载
            version: "2.0", // Loca 版本
          },
        });
        this.map = new AMap.Map("container",{
          layers: [new AMap.TileLayer.Satellite()],
        });

        map.plugin(["AMap.MarkerCluster"], function () {
          cluster = new AMap.MarkerCluster(
            map, //地图实例
            points, //海量点数据，数据中需包含经纬度信息字段 lnglat
            {
              gridSize: 60, //数据聚合计算时网格的像素大小
              renderClusterMarker: _renderClusterMarker, //上述步骤的自定义聚合点样式
              renderMarker: _renderMarker, //上述步骤的自定义非聚合点样式
            }
          );
        });
        this.map.addControl(new AMap.Scale()); // 添加比例尺组件到地图实例上
      } catch (e) {
        console.error("Failed to load AMap:", e); // 加载错误提示
      }
    },
    addMarkers(markList) {
      markList.forEach((mark) => {
        console.log(mark.name);
        const icon = new AMap.Icon({
          size: new AMap.Size(34, 34), //图标尺寸
          image: process.env.VUE_APP_BASE_API + mark.imgUrl,
          imageOffset: new AMap.Pixel(-9, -3), //图像相对展示区域的偏移量，适于雪碧图等
          imageSize: new AMap.Size(135, 40), //根据所设置的大小拉伸或压缩图片
        });
        const marker = new AMap.Marker({
          position: new AMap.LngLat(mark.longitude, mark.latitude),
          offset: new AMap.Pixel(-10, -10),
          title:'测速',
          icon: icon,
        });
        this.map.add(marker);
      });
    },
  },
  async mounted() {
    await this.initMap();
    const markList = await this.getMarkList();
    console.log(markList);
    if (markList) {
      this.addMarkers(markList);
    }
  },
};
</script>
