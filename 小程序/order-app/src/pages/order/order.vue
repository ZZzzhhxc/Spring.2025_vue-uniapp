<template>
	<view>
		<view class="wrap">
			<view class="u-tabs-box">
				<u-tabs-swiper activeColor="#2E8B57" ref="tabs" :list="list" :current="currents" @change="change"
					:is-scroll="false" swiperWidth="750"></u-tabs-swiper>
			</view>
			<swiper class="swiper-box" :current="swiperCurrent" @transition="transition"
				@animationfinish="animationfinish">
				<swiper-item v-for="(item,index) in list" :key='index' class="swiper-item">
					<scroll-view scroll-y style="height: 100%;width: 100%;" @scrolltolower="reachBottom">
						<view class="page-box">
							<view class="order" v-for="(res, index) in orderList[index]" :key="res.id">
								<view class="top">
									<view class="left">
										<u-icon name="home" :size="30" color="rgb(94,94,94)"></u-icon>
										<view class="store">{{ res.userName }}</view>
										<u-icon name="arrow-right" color="rgb(203,203,203)" :size="26"></u-icon>
									</view>
									<view style="margin-left: 6px;"  class="right">{{ res.phone }}</view>
								</view>
								<view class="">
									{{res.address}}
								</view>
								<view class="item" v-for="(item, index) in res.goodsList" :key="index">
									<view class="left">
										<image :src="item.goodsImage.split(',')[0]" mode="aspectFill"></image>
									</view>
									<view class="content">
										<view class="title u-line-2">{{ item.goodsName }}</view>
										<view class="type">{{ item.specsName }}{{item.goodsUnit}}</view>
										<!-- <view class="delivery-time">发货时间 {{ item.deliveryTime }}</view> -->
									</view>
									<view class="right">
										<view class="price">
											￥{{ item.price }}
											<!-- <text class="decimal">.{{ priceDecimal(item.price) }}</text> -->
										</view>
										<view class="number">x{{ item.num }}</view>
									</view>
								</view>
								<view class="total">
									共{{ res.goodsList.length }}件商品 合计:
									<text class="total-price">
										￥{{ res.price }}
										<!-- <text class="decimal">{{ priceDecimal(totalPrice(res.goodsList)) }}</text> -->
									</text>
								</view>
								<view class="bottom">
									<view @click="cancel(res,index)" v-if="res.status == '0'" class="exchange btn">取消订单</view>
									<view v-if="res.status == '2'" class="evaluate btn">评价</view>
								</view>
							</view>
							<u-loadmore :status="loadStatus[index]" bgColor="#f2f2f2"></u-loadmore>
						</view>
					</scroll-view>
				</swiper-item>
			</swiper>
		</view>
	</view>
</template>

<script setup>
	import {
		onReady
	} from '@dcloudio/uni-app';
	import {getOrderListApi,cancelOrderApi} from '../../api/order.js'
	import {
		ref,
		computed
	} from 'vue'
	const tabs = ref(null);
	const orderList = ref([
		[],
		[],
		[],
		[]
	])
	const list = ref([{
			name: '全部'
		},
		{
			name: '待发货'
		},
		{
			name: '已发货'
		},
		{
			name: '已收货'
		}
	])
	const currents = ref(0)
	const swiperCurrent = ref(0)
	const tabsHeight = ref(0)
	const dx = ref(0)
	const currentPage = ref(1)
	const pageSize = ref(3)
	const pages = ref(0)
	const loadStatus = ref(['loadmore', 'loadmore', 'loadmore', 'loadmore'])
	const reachBottom = () => {
		if (currents.value != 2) {
			loadStatus.value.splice(currents.value, 1, "loading")
			setTimeout(() => {
				if(currentPage.value < pages.value){
					currentPage.value = ++currentPage.value
					getOrderList(currents.value);
				}else{
					loadStatus.value.splice(currents.value, 1, "nomore")
				}
				
			}, 1000);
		}
	}
	const change = (index)=>{
		loadStatus.value.splice(index,1,'loading')
		swiperCurrent.value = index;
		currentPage.value = 1;
		pages.value = 0;
		//清空数据列表
		orderList.value[currents.value] = [];
	}
	const transition = ({ detail: { dx } })=>{
		tabs.value.setDx(dx);
	}
	const animationfinish = ({ detail: { current } })=>{
		console.log(current)
		console.log(currents.value)
		tabs.value.setFinishCurrent(current);
		swiperCurrent.value = current;
		currents.value = current;
		currentPage.value = 1;
		pages.value = 0;
		//清空数据列表
		orderList.value[currents.value] = [];
		getOrderList(current)
		
	}
	//查询订单
	const getOrderList = async(idx)=>{
		let res = await getOrderListApi({
			openid:uni.getStorageSync("openid"),
			type:idx == 0 ? '' : idx-1,
			currentPage:currentPage.value,
			pageSize:pageSize.value
		})
		if(res && res.code == 200){
			console.log(res)
			
			if(res.data.records.length == 0){//没有数据
				loadStatus.value.splice(currents.value,1,'nomore')
				return;
			}
			pages.value = res.data.pages
			res.data.records.map(item =>{
				orderList.value[idx].push(item)
			})
			loadStatus.value.splice(currents.value,1,'loadmore')
		}else{
			loadStatus.value.splice(currents.value,1,'nomore')
		}
	}
	//取消订单
	const cancel = (item,index) => {
	    uni.showModal({
	        title: '提示',
	        content: '确定取消订单吗？',
	        success:async function(res) {
	            if (res.confirm) {
	                console.log('用户点击确定');
					console.log(item)
					let res = await cancelOrderApi({
						orderId:item.orderId
					})
					if(res && res.code == 200){
						getOrderList(index+1);
						change(index+1)
					}
	            } else if (res.cancel) {
	                console.log('用户点击取消');
	            }
	        }
	    });
	}
	onReady(() => {
		getOrderList(0);
	})
</script>
<style>
/* #ifndef H5 */
page {
	height: 100%;
	background-color: #f2f2f2;
}
/* #endif */
</style>

<style lang="scss" scoped>
.order {
	width: 710rpx;
	background-color: #ffffff;
	margin: 10rpx auto;
	border-radius: 20rpx;
	box-sizing: border-box;
	padding: 20rpx;
	font-size: 28rpx;
	.top {
		display: flex;
		// justify-content: space-between;
		.left {
			display: flex;
			align-items: center;
			.store {
				margin: 0 10rpx;
				font-size: 32rpx;
				font-weight: bold;
			}
		}
		.right {
			color: $u-type-warning-dark;
		}
	}
	.item {
		display: flex;
		margin: 20rpx 0 0;
		.left {
			margin-right: 20rpx;
			image {
				width: 160rpx;
				height: 160rpx;
				border-radius: 10rpx;
			}
		}
		.content {
			.title {
				font-size: 28rpx;
				line-height: 50rpx;
			}
			.type {
				margin: 10rpx 0;
				font-size: 24rpx;
				color: $u-tips-color;
			}
			.delivery-time {
				color: #75e553;
				font-size: 24rpx;
			}
		}
		.right {
			margin-left: 10rpx;
			padding-top: 20rpx;
			text-align: right;
			.decimal {
				font-size: 24rpx;
				margin-top: 4rpx;
			}
			.number {
				color: $u-tips-color;
				font-size: 24rpx;
			}
		}
	}
	.total {
		margin-top: 20rpx;
		text-align: right;
		font-size: 24rpx;
		.total-price {
			font-size: 32rpx;
		}
	}
	.bottom {
		display: flex;
		margin-top: 40rpx;
		padding: 0 10rpx;
		justify-content: flex-end;
		align-items: center;
		.btn {
			line-height: 52rpx;
			width: 160rpx;
			border-radius: 26rpx;
			border: 2rpx solid $u-border-color;
			font-size: 26rpx;
			text-align: center;
			color: $u-type-info-dark;
		}
		.evaluate {
			color: $u-type-warning-dark;
			border-color: $u-type-warning-dark;
		}
	}
}
.centre {
	text-align: center;
	margin: 200rpx auto;
	font-size: 32rpx;
	image {
		width: 164rpx;
		height: 164rpx;
		border-radius: 50%;
		margin-bottom: 20rpx;
	}
	.tips {
		font-size: 24rpx;
		color: #999999;
		margin-top: 20rpx;
	}
	.btn {
		margin: 80rpx auto;
		width: 200rpx;
		border-radius: 32rpx;
		line-height: 64rpx;
		color: #ffffff;
		font-size: 26rpx;
		background: linear-gradient(270deg, rgba(249, 242, 186, 1.0) 0%, rgba(46, 139, 87, 1.0) 100%);
	}
}
.wrap {
	display: flex;
	flex-direction: column;
	height: calc(100vh - var(--window-top));
	width: 100%;
}
.swiper-box {
	flex: 1;
}
.swiper-item {
	height: 100%;
}
</style>