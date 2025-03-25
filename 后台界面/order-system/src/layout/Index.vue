<template>
  <el-container class="layout-container">
    <el-aside width="auto" class="aside">
      <Menu />
    </el-aside>
    <el-container class="content-container">
      <el-header class="header">
        <Header />
      </el-header>
      <el-main class="main-content">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup lang="ts">
import Menu from './Menu.vue';
import Header from './header/Header.vue';
</script>

<style scoped lang="scss">
.layout-container {
  height: 100vh;
  display: flex;
  
  .aside {
    background-color: #f5f5f5;
    box-shadow: 2px 0 4px rgba(0, 0, 0, 0.05);
    transition: width 0.3s ease;
  }
  
  .content-container {
    display: flex;
    flex-direction: column;
    flex: 1;
    min-width: 0; /* 防止flex布局溢出 */
    
    .header {
      background-color: #ffffff;
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0 24px;
      height: 60px;
      border-bottom: 1px solid #e6e6e6;
      box-shadow: 0 1px 4px rgba(0, 0, 0, 0.05);
      z-index: 10;
      
      /* 确保Header内部布局正确 */
      :deep(.header-content) {
        width: 100%;
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
    }
    
    .main-content {
      padding: 20px;
      background-color: #f8f9fa;
      flex: 1;
      overflow: auto; /* 内容超出时滚动 */
    }
  }
}
</style>