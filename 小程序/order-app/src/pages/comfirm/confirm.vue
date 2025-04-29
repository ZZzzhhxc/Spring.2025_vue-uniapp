<template>
	<view class="address" @click="toAddress">
		<view>
			<view class="info">
				<view class="name">
					张明
				</view>
				<view class="phone">
					18687116xxx
				</view>
			</view>
			<view class="">
				北京工业大学
			</view>
		</view>
		<u-icon color="#c8c9cc" name="arrow-right"></u-icon>
	</view>
	<view style="padding-bottom: 60px;margin-top: 10px;" v-if="goods.length > 0">
		<view class="goods-detail" v-for="(item,index) in goods" :key="index">
			<view class="detail-left">
				<view class="goods-left">
					<image style="width: 150rpx; height: 140rpx; margin-left:10px;" :src="item.goodsImage"></image>
				</view>
				<view class="size">
					<text style="font-size: 25rpx;">名称：{{item.goodsName}}</text>
					<text style="font-size: 25rpx;">规格：{{item.specsName}}</text>
					<text class="goods-price">￥{{item.price}}{{item.goodsUnit}}</text>
				</view>
			</view>
		</view>
	</view>
	<view class="end">
		<view class="end-left">
			<view>
				总计：<text style="color: #2E8B57;font-weight: bold;">￥{{totalPrice}}</text>
			</view>
		</view>
		<view @click="toConfirm" class="end-right">
			提交订单({{totalNum}})
		</view>
	</view>
</template>

<script setup>
	import {
		ref,
		reactive,
		computed
	} from 'vue'
	const goods = reactive([{
			flag: true,
			goodsName: "女款-M",
			goodsUnit: '/份',
			goodsId: 1,
			num: 1,
			specsName: '中',
			price: 149,
			goodsImage: "http://192.168.31.70:8089/images/a35e4257-9e9a-43f8-b077-a7664064ce12.png",
		},
		{
			flag: true,
			goodsName: "女款-M",
			goodsUnit: '/份',
			goodsId: 1,
			specsName: '中',
			num: 1,
			price: 149,
			goodsImage: "http://192.168.31.70:8089/images/a35e4257-9e9a-43f8-b077-a7664064ce12.png",
		},
		{
			flag: true,
			goodsName: "女款-M",
			goodsUnit: '/份',
			goodsId: 1,
			num: 1,
			specsName: '中',
			price: 149,
			goodsImage: "http://192.168.31.70:8089/images/a35e4257-9e9a-43f8-b077-a7664064ce12.png",
		},
		{
			flag: true,
			goodsName: "女款-M",
			goodsId: 1,
			num: 1,
			goodsUnit: '/份',
			specsName: '中',
			price: 149,
			goodsImage: "http://192.168.31.70:8089/images/a35e4257-9e9a-43f8-b077-a7664064ce12.png",
		}
	])
	//总数
	const totalNum = computed(()=>{
		let totalNum = 0;
			
		if(goods.length >0){
			goods.map(item =>{
				item.flag ? totalNum += item.num : totalNum += 0;
			})
		}
		
		return totalNum
	})
	//总共价格
	const totalPrice = computed(()=>{
		let totalPrice = 0;
		if(goods.length > 0){
			goods.map(item =>{
				item.flag ? totalPrice += item.num*item.price : totalPrice += 0;
			})
		}
		return totalPrice
	})
	//跳转新增地址
	const toAddress = (item) => {
		uni.navigateTo({
			url: '../address/address'
		});
	}
</script>

<style lang="scss">
	page {
		background-color: #f4f4f4;
	}

	.address {
		display: flex;
		justify-content: space-between;
		background-color: #FFF;
		height: '25px';
		padding: 10px 20px;

		.info {
			display: flex;

			.name {
				font-size: 14px;
				font-weight: 600;
			}

			.phone {
				margin-left: 10px;
			}
		}
	}

	.goods-detail {
		display: flex;
		padding: 30rpx 15rpx 30rpx 30rpx;
		background-color: #FFF;
		justify-content: space-between;
		border-bottom: 5rpx solid #f1f1f1;
		align-items: center;

		.detail-left {
			display: flex;

			.goods-left {
				display: flex;
				align-items: center;
			}

			.size {
				display: flex;
				flex-direction: column;
				justify-content: space-around;
				margin-left: 30rpx;

				.goods-price {
					font-size: 25rpx;
					color: #F44545;
				}
			}
		}

		.detail-right {
			text {
				width: 50rpx;
				line-height: 50rpx;
				text-align: center;
				display: inline-block;
				background-color: #F7F7F7;
				margin-right: 10rpx;
			}

			.add {
				color: #FA4305;
				border-radius: 10rpx 30rpx 30rpx 10rpx;
				margin-right: 20rpx;
			}

			.subtract {
				border-radius: 30rpx 10rpx 10rpx 30rpx;
			}
		}
	}

	.end {
		width: 100%;
		height: 90rpx;
		background-color: #fff;
		position: fixed;
		bottom: 0;
		left: 0;
		display: flex;
		align-items: center;

		.end-left {
			width: 70%;
			display: flex;
			justify-content: space-between;
			padding: 0 30rpx;
		}

		.end-right {
			width: 30%;
			line-height: 90rpx;
			background-color: #2E8B57;
			text-align: center;
			color: #fff;
		}
	}
</style>
