import { defineStore } from "pinia";
//定义store
//defineStore第一个参数id是唯一的
export const testStore = defineStore('testStore ', {
    state: ()=>{
        return{
            count: 0
        }
    },
    getters:{
        getCount(state){
            return state.count
        }
    },
    actions:{
        //设置state里面的数据
        setCount(count:number){
            this.count = count;
        }
    }
})