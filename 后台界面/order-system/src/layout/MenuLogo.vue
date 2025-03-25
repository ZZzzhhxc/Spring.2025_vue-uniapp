<template>
    <div class="logo-container" :class="{ 'collapsed': !show }">
      <img 
        :src="MenuLogo" 
        alt="校园点餐系统Logo"
        class="logo-img"
      />
      <transition name="fade">
        <span v-if="show" class="logo-title">{{ title }}</span>
      </transition>
    </div>
  </template>
  
  <script setup lang="ts">
  import { collapseStore } from '@/store/collapse/index';
  import { ref, watch } from "vue";
  
  const store = collapseStore();
  const MenuLogo = ref("https://wpimg.wallstcn.com/69a1c46c-eb1c-4b46-8bd4-e9e686ef5251.png");
  const title = ref("校园点餐系统");
  const show = ref(true);
  
  watch(
    () => store.getCollapse,
    (collapsed: boolean) => {
      if (!collapsed) {
        setTimeout(() => {
          show.value = !collapsed;
        }, 300);
      } else {
        show.value = !collapsed;
      }
    }
  );
  </script>
  
  <style scoped lang="scss">
  .logo-container {
    display: flex;
    align-items: center;
    height: 60px;
    padding: 0 20px;
    background: linear-gradient(to right, #f5f7fa, #e8f5e9);
    border-bottom: 1px solid #e0e0e0;
    transition: all 0.3s ease;
    overflow: hidden;
    
    &.collapsed {
      justify-content: center;
      padding: 0 10px;
    }
  
    .logo-img {
      width: 36px;
      height: 36px;
      object-fit: contain;
      flex-shrink: 0;
    }
  
    .logo-title {
      margin-left: 12px;
      color: #2e7d32;
      font-size: 20px;
      font-weight: 600;
      font-family: "Microsoft YaHei", sans-serif;
      white-space: nowrap;
      
      /* 文字渐变效果 */
      background: linear-gradient(to right, #2e7d32, #4caf50);
      -webkit-background-clip: text;
      background-clip: text;
      color: transparent;
    }
  
    /* 标题动画 */
    .fade-enter-active,
    .fade-leave-active {
      transition: opacity 0.3s ease, transform 0.3s ease;
    }
    .fade-enter-from,
    .fade-leave-to {
      opacity: 0;
      transform: translateX(-10px);
    }
  }
  </style>