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
app.use(router).use(ElementPlus).use(pinia).mount('#app')//ȫ��ע��ͼ�꣬��ҳ���п���ֱ��ʹ��
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }