

import map from "./map.vue";
import ElementUI from './element-ui';
import './element-ui/lib/theme-chalk/index.css';
const install = (Vue) => {
  Vue.use(ElementUI);
  Vue.component(map.name, map);
};
export let key = ''

// key 42acfc67036391f500b6521fcd549db3
const initMap=(apikey,security)=>{
    key  = apikey
  // 7f2d56317aed266ceef128139f08306d
  window._AMapSecurityConfig = {
    securityJsCode:security,
  }
}
export default {
  initMap,
  install,
  
};
