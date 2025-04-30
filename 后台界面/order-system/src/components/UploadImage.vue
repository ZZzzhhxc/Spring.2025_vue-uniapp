<template>
  <el-upload
    ref="uploadRef"
    action="#"
    :on-change="uploadFile"
    list-type="picture-card"
    :auto-upload="false"
    :file-list="fileList"
    :show-file-list="true"
    :limit="1"
    :on-remove="handleRemove"
    :on-exceed="moreLimit"
  >
    <el-icon><Plus /></el-icon>
  </el-upload>
</template>

<script setup lang="ts">
import { Plus } from "@element-plus/icons-vue";
import type {
  UploadFile,
  UploadUserFile,
  UploadFiles
} from "element-plus";
import { ref } from "vue";
import { ElMessage } from "element-plus";
import { uploadImageApi } from "@/api/img/index";
import type{ NewType } from "@/type/BaseType";
//图片上传组件的ref属性
const uploadRef = ref();
//注册事件
const emits = defineEmits(["getImg"]);
const newImgRes = ref<NewType>({
  newImgUrl: [],
  deleteUrl: [],
});

//图片上传的数据
type PropType = {
  fileList: UploadUserFile[];
  oldUrl: Array<{ url: string }>;
};
const props = withDefaults(defineProps<PropType>(), {
  fileList: () => [],
  oldUrl: () => [],
});
//删除图片
const handleRemove = (file: UploadFile) => {
  if(props.oldUrl.some(item => item.url === file.name)){
    newImgRes.value.deleteUrl.push({url:file.name})
    emits("getImg", newImgRes.value);
  }else{
    let images = newImgRes.value.newImgUrl.filter((item) => item.url != file.name);
    newImgRes.value.newImgUrl = images;
    emits("getImg", newImgRes.value);
  }
};
//文件个数超出
const moreLimit = (files: File[], uploadFiles: UploadFiles) => {
  ElMessage.warning("最多只能上传" + uploadFiles.length + "张图片!");
};
//图片上传
const uploadFile = async (file: any) => {
  //判断选择的图片是不是图片类型
  const typeArr = ["image/png", "image/gif", "image/jpeg", "image/jpg"];
  const isImg = typeArr.indexOf(file.raw.type) !== -1;
  const isMore3M = file.size / 1024 / 1024 < 3;
  if (!isImg) {
    ElMessage.warning("只能上传图片类型!");
    uploadRef.value?.clearFiles();
    return;
  }
  if (!isMore3M) {
    ElMessage.warning("图片大小不能超过3M!");
    uploadRef.value?.clearFiles();
    return;
  }
  //数据提交
  const formData = new FormData();
  formData.append("file", file.raw);
  let res = await uploadImageApi(formData);
  if (res && res.code == 200 && res.data) {
    ElMessage.success("图片上传成功!");
    file.name = process.env.BASE_API + res.data;
    newImgRes.value.newImgUrl.push({ url: process.env.BASE_API + res.data });
    emits("getImg", newImgRes.value);
  }
};
</script>
<style ></style>