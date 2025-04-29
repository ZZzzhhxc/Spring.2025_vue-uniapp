// stores/counter.js
import { defineStore } from 'pinia';

export const carStore = defineStore('carStore', {
	state: () => {
		return { 
			count: 0 ,
			carList:[]
		};
	},
	// 也可以这样定义
	// state: () => ({ count: 0 })
	actions: {
		increment() {
			this.count++;
		},
		addCar(goods){
			//查找是否存在，返回位置
			const index = this.carList.findIndex(item => goods.goodsId == item.goodsId);
			//存在，删除
			if(index > -1){
				this.carList.splice(index,1);
			}
			this.carList.push(goods);
		}
	},
});