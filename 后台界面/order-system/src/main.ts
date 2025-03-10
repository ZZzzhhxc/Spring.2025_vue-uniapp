import { createApp } from 'vue'
//import './style.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import router from './router'
import App from './App.vue'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import { createPinia } from 'pinia'
//createApp(App).mount('#app')
const pinia = createPinia()
const app = createApp(App);
app.use(router).use(ElementPlus).use(pinia).mount('#app')//全局注册图标，在页面中可以直接使用
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }