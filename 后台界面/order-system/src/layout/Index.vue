<template>
    <el-container class="layout">
      <el-aside width="auto" class="asside">
        <Menu>

        </Menu>
      </el-aside>
      <el-container>
        <el-header class="header">
          <Header></Header>
        </el-header>
        <el-main class="main">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
</template>

<script setup lang="ts">
  import Menu from './Menu.vue';
  import Header from './header/Header.vue';
</script>

<style scoped lang="scss">
  .layout {
    height: 100%;
    .asside {
      //background-color: rgb(255, 255, 255);
      background-color: #51515b;
    }
    .header {
      //background-color: rgb(255, 255, 255);
      display: flex;
      align-items: center;
      border-bottom: 1px solid #d8bce5 !important;
      justify-content: space-between;
      }
    .main  {
      background-color: rgb(255, 255, 255);
    }
  }
</style>