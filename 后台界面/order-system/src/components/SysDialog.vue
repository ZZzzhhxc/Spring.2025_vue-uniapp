<template>
    <el-dialog
    :model-value="visible"
    :title="title"
    :width="width+'px'"
    append-to-body
    :before-close="onClose"
  >
    <!--弹框内容:插槽 -->
    <div class="container" :style="{height:height+'px'}">
        <slot name="content"></slot>
    </div>
    <template #footer>
      <div class="dialog-footer">
        <el-button type="danger" @click="onClose">取消</el-button>
        <el-button type="primary" @click="onConfirm">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { sl } from 'element-plus/es/locales.mjs';

//定义参数类型
//?：可选参数
interface DialogProps{
    title?:string;//弹框标题
    visible:boolean;//控制弹框显示和隐藏
    width?:number;//宽度
    height?:number;//高度
}
//子组件，接受父组件传递的参数
//withDefaults:指定默认值
//defineProps:接受父组件传递的值
const props =withDefaults(defineProps<DialogProps>(),{
    title:'title',
    visible:false,
    width:630,
    height:280
})
//注册事件
const emits=defineEmits(['onClose','onConfirm'])
//弹框关闭
const onClose=()=>{
    emits('onClose')
}
//弹框确定
const onConfirm=()=>{
    emits('onConfirm')
}
</script>

<style lang="scss">

.container {
  overflow: hidden;
}


.el-dialog {

  width: 630px !important;
  min-width: 630px !important;
  max-width: 630px !important;
  

  border-top-left-radius: 7px !important;
  border-top-right-radius: 7px !important;
  padding: 0;
  box-shadow: 0 3px 12px rgba(0, 0, 0, 0.12) !important;


  .el-dialog__header {
    margin-right: 0;
    padding: 12px 16px;
    border-radius: 7px 7px 0 0 !important;
    background: linear-gradient(135deg, #82bc84 0%, #43A047 50%, #82bc84 0%) !important;
    
    .el-dialog__title {
      color: #fff;
      font-size: 16px;
      font-weight: 600;
      letter-spacing: 0.3px;
    }
  }


  .el-dialog__body {
    padding: 16px;
    background: #fff;
  
    overflow-y: auto;
    overflow-x: hidden;
    max-height: calc(100vh - 200px) !important; /* 动态适应 */
  
    > div {
      width: calc(100% - 1px) !important; /* 强制宽度限制 */
      min-width: 0 !important;
    }
  }

  .el-dialog__footer {
    border-top: 1px solid #e8eaec !important;
    padding: 12px 16px;
    background: #ffffff;
    
    .el-button {
      min-width: 64px;
      padding: 6px 12px;
      border-radius: 3px;
      
      + .el-button {
        margin-left: 10px;
      }
    }
  }
}


body .el-dialog__wrapper {
  overflow: hidden !important;
}
</style>