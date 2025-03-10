<script setup lang="ts">
import { testStore } from '@/store/test/index';
//获取store
const store = testStore()
//改变store里面的count值
const addBtn = ()=>{
  store.count++
}
</script>

<template>
  <div class="mb-4">
    <el-button @click="addBtn" type="primary">Primary</el-button>
   
  </div>
  <div>{{ store.count }}</div>
  <el-icon>
      <Edit />
    </el-icon>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
</style>
