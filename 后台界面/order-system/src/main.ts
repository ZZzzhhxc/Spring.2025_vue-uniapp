import { createApp } from 'vue'
//import './style.css'
//echarts
import * as echarts from 'echarts'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router'
import App from './App.vue'
//import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { createPinia } from 'pinia'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import myConfirm from './utils/myConfirm'
//createApp(App).mount('#app')
const pinia = createPinia()
const app = createApp(App);
app.use(router).use(ElementPlus, {
  locale: zhCn,
}).use(pinia).mount('#app')
//全局注册图标
// for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
//     app.component(key, component)
//   }
app.config.globalProperties.$myconfirm = myConfirm
app.config.globalProperties.$echarts = echarts;