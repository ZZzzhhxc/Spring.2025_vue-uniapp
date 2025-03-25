import type { ListUserParm } from "@/api/user/UserModel";
import { reactive } from "vue";

//表格业务抽离
export default function useUserTable(){
    //列表查询的参数
    const listParm = reactive<ListUserParm>({
        currentPage:1,
        pageSize:10,
        name:'',
        phone:''
    })
    //列表
    const getList=()=>{

    }
    //搜索
    const searchBtn=()=>{

    }
    //重置
    const resetBtn =()=>{

    }
    return{
        listParm,
        getList,
        searchBtn,
        resetBtn
    }
}