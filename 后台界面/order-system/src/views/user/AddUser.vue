<template>
    <SysDialog
    :title="dialog.title"
    :width="dialog.width"
    :height="dialog.height"
    :visible="dialog.visible"
    @on-close="onClose"
    @on-confirm="commit"
    >
        <template v-slot:content>
            <el-form :model="addModel" ref="addFormRef" :rules="rules" label-width="80px" :inline="false" size="default">
                <el-row :gutter="20">
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="name" label="姓名">
                    <el-input v-model="addModel.name"></el-input>
                </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="sex" label="性别">
                    <el-radio-group v-model="addModel.sex">
                        <el-radio :value="'0'">男</el-radio>
                        <el-radio :value="'1'">女</el-radio>
                        </el-radio-group>
                </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="phone" label="电话">
                    <el-input v-model="addModel.phone"></el-input>
                </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="email" label="邮箱">
                    <el-input v-model="addModel.email"></el-input>
                </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="20">
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="username" label="账户">
                    <el-input v-model="addModel.username"></el-input>
                </el-form-item>
                    </el-col>
                    <el-col :span="12" :offset="0">
                        <el-form-item prop="password" label="密码">
                    <el-input v-model="addModel.password"></el-input>
                </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            
        </template>
    </SysDialog>
</template>

<script setup lang="ts">
import type { UserModel } from '@/api/user/UserModel';
import SysDialog from '@/components/SysDialog.vue';
import useDialog from '@/hooks/useDialog';
import { reactive, ref } from 'vue';
import type { FormInstance } from 'element-plus'
import type { Phone } from '@element-plus/icons-vue';
import { addUserApi } from '@/api/user/index';
//表单的ref属性
const addFormRef = ref<FormInstance>()
//弹框属性
const {dialog,onClose,onConfirm,onShow} =useDialog()
//表单绑定的对象
const addModel = reactive<UserModel>({
    userId:'',
    username:'',
    password:'',
    phone:'',
    email:'',
    sex:'',
    name:'',
});
//显示弹框
const show =()=>{
    dialog.height = 180;
    dialog.visible = true;
    //清空表单
    addFormRef.value?.resetFields()
};
//给父组件调用
defineExpose({
    show
});
//表单验证的规则
const rules = reactive({
    name:[
    {
        required: true,
        message: '请输入姓名',
        trigger: 'blur',
    },
  ],
    sex:[
    {
        required: true,
        message: '请选择性别',
        trigger: 'blur',
    },
  ],
    phone:[
    {
        required: true,
        message: '请输入电话',
        trigger: 'blur',
    },
  ],
    username:[
    {
        required: true,
        message: '请输入账户',
        trigger: 'blur',
    },
  ],
    password:[
    {
        required: true,
        message: '请输入密码',
        trigger: 'blur',
    },
  ]
});
//表单提交
const commit =()=>{
    addFormRef.value?.validate(async(valid)=>{
        if(valid){
            let res = await addUserApi(addModel);
            console.log(res)
            if(res && res.code ==200){
                onClose();
            }
        }
    });
};
</script>

<style scoped>

</style>