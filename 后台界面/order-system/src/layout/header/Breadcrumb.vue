<template>
<el-breadcrumb class="bred" separator="/">
    <el-breadcrumb-item v-for="item in tabs">{{ item.meta.title }}</el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script setup lang="ts">
import { onMounted, ref,watch } from 'vue';
import type { Ref } from 'vue';
import { useRoute } from 'vue-router';
import type { RouteLocationMatched } from 'vue-router';
const route = useRoute() //获取当前路由
const tabs: Ref<RouteLocationMatched[]>= ref([]);  // 定义面包屑导航数据
watch( //监听当前路由
    ()=>route.path,
    ()=>getBredcrumb()
)
//获取面包屑导航数据
const getBredcrumb = ()=>{
    let mached = route.matched.filter((item)=>item.meta && item.meta.title); //找出有title的数据
    console.log(mached)
    const first = mached[0]
    console.log(first)
    if(first.path !=='/dashboard'){
        mached = [{path:'dashboard',meta:{title:'首页'}} as any].concat(mached)
    }
    tabs.value = mached
};
onMounted(()=>{
    getBredcrumb()
})
</script>

<style scoped lang="scss">
/*:deep(el-breadcrumb_inner) {
    color: #fff !important;
}*/
.bred {
    margin-left: 20px;
}
</style>