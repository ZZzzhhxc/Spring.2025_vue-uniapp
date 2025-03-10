<template>
    <el-icon style="cursor: pointer;" :size="26" @click="iconClick" >
      <!--动态组件-->
      <component :is="status ? Expand : Fold"></component>
    </el-icon>
</template>

<script setup lang="ts">
import { collapseStore } from '@/store/collapse/index';
import { Expand,Fold } from '@element-plus/icons-vue';
import { computed } from 'vue';
//获取store
const store = collapseStore();
//获取状态
const status = computed(()=>{
    return store.getCollapse
})
//图标点击事件
const iconClick = () => {
    store.setCollapse(!store.collapse)
}
</script>

<style scoped>

</style>