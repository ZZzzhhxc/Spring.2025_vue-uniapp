//增删改的业务操作
import {ref} from "vue"
export default function useUser(){
    const addRef = ref<{show:()=>void}>();
    //新增
    const addBtn=()=>{
        console.log(addRef)
        addRef.value?.show()
    }
    //编辑
    const editBtn=()=>{

    }
    //删除
    const deleteBtn=()=>{

    }
    return{
        addBtn,
        editBtn,
        deleteBtn,
        addRef
    }
}