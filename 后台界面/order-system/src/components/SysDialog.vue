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
      overflow-x: initial;
      overflow-y: auto;
    }
    .el-dialog {
      border-top-left-radius: 7px !important;
      border-top-right-radius: 7px !important;
      
      padding: 0;
      .el-dialog__header {
        margin-right: 0px;
        border-top-left-radius: 7px !important;
        border-top-right-radius: 7px !important;
        background-color: #494f4f !important;
        .el-dialog__title {
          color: #fff;
          font-size: 16px;
          font-weight: 600;
        }
      }
      .el-dialog__headerbtn {
        .el-dialog__close {
          color: #fff;
        }
      }
      .el-dialog__body {
        padding: 10px;
      }
      .el-dialog__footer {
        border-top: 1px solid #e8eaec !important;
        padding: 10px;
      }
    }
    </style>