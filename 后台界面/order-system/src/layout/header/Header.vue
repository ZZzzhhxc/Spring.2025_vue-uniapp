<template>
    <div class="left-header">
        <Collapse></Collapse>
        <Breadcrumb></Breadcrumb>
    </div>
    <LoginOut></LoginOut>
</template>

<script setup lang="ts">
import Breadcrumb from './Breadcrumb.vue';
import Collapse from './Collapse.vue';
import LoginOut from './LoginOut.vue';
</script>

<style scoped>
.left-header{
    display: flex;
    align-items: center;
}
</style>