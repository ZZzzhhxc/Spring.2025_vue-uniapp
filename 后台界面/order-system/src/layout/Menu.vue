<template>
    <MenuLogo></MenuLogo>
    <el-menu
     :collapse="collapse"
     router 
     background-color="#EEEEEE"  
     :default-active="activeIndex" 
     class="el-menu-vertical-demo"
>   
    <el-menu-item index="/dashboard">
      <el-icon><Menu /></el-icon>
      <template #title>首页</template>
    </el-menu-item>
    <el-menu-item index="/user">
      <el-icon><Menu /></el-icon>
      <template #title>用户管理 </template>
    </el-menu-item>
    <el-menu-item index="/category">
      <el-icon><Menu /></el-icon>
      <template #title>菜品分类 </template>
    </el-menu-item>
    <el-menu-item index="/goods">
      <el-icon><Memo /></el-icon>
      <template #title>菜品管理</template>
    </el-menu-item>
    <el-menu-item index="/order">
      <el-icon><Monitor /></el-icon>
      <template #title>订单管理</template>
    </el-menu-item>
    <el-menu-item index="/banner">
      <el-icon><Calendar /></el-icon>
      <template #title>广告管理</template>
    </el-menu-item>
    <el-menu-item index="/comment">
      <el-icon><Edit /></el-icon>
      <template #title>评论管理</template>
    </el-menu-item>
  </el-menu>
</template>

<script setup lang="ts">
import { collapseStore } from "@/store/collapse/index"
import { useRoute } from "vue-router";
import MenuLogo from "./MenuLogo.vue";
import { Menu, Memo, Monitor, Calendar, Edit,} from "@element-plus/icons-vue"
//import Index from "./Index.vue";
import { computed } from "vue";
//获取store
const store = collapseStore()
//当前路由
const route = useRoute();
//获取激活选项
const activeIndex = computed(()=>{
  const { path } = route;
  return path ;
});
//获取菜单状态
const collapse = computed(()=>{
  return store.getCollapse
});
</script>

<style scoped lang="scss">
  :deep(.el-menu-item) {
        transition: none !important; /* 禁用所有过渡动画 */
        will-change: background-color; /* 启用硬件加速 */
    }
.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 230px;
    min-height: 400px;
    background-color: #f5f7fa; /* 新：更柔和的背景色 */
}

.el-menu {
    border-right: none;
    background-color: transparent !important; /* 透明背景 */
    
    .el-menu-item {
        color: #4a8c2e !important; /* 新：深绿色文字 */
        font-size: 15px;
        font-weight: 600; /* 稍细的字体 */
        height: 48px;
        line-height: 48px;
        margin: 4px 8px;
        border-radius: 6px;
        transition: all 0.2s ease;
        
        .el-icon {
            color: inherit;
            font-size: 18px;
            margin-right: 8px;
        }
        
        &:hover {
            background-color: #e8f5e9 !important;
            color: #388e3c !important;
        }
        
        &.is-active {
            background-color: #4caf50 !important;
            color: white !important;
            box-shadow: 0 2px 8px rgba(76, 175, 80, 0.3);
        }
    }
}
</style>