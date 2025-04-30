<template>
	<view>
		<view class="u-flex user-box u-p-l-30 u-p-r-20 u-p-b-30">
			<view class="userinfo u-flex-1" @click="getUserInfo">
				<view class="img-container">
					<image class="uimage" :src='avatarUrl'></image>
				</view>
				<!-- 分割线 -->
				<u-divider>{{nickName}}</u-divider>
			</view>
		</view>

		<view class="u-m-t-20">
			<u-cell-group>
				<u-cell-item @click="toOrder" icon="star" title="我的订单"></u-cell-item>
				<u-cell-item icon="photo" title="我的收藏"></u-cell-item>
				<u-cell-item icon="coupon" title="我的地址"></u-cell-item>
			</u-cell-group>
		</view>
	</view>
</template>

<script setup>
	import {
		onShow
	} from '@dcloudio/uni-app';
	import {
		ref
	} from 'vue'
	import {userLogin} from '../../api/user.js'
	//昵称
	const nickName = ref('夏天的树')
	//头像
	const avatarUrl = ref('/static/user.jpg')
	//获取头像点击事件
	const getUserInfo = () => {
		console.log('获取头像')
		if (uni.getStorageSync('avatarUrl') && uni.getStorageSync('nickName')) {
			nickName.value = uni.getStorageSync('nickName')
			avatarUrl.value = uni.getStorageSync('avatarUrl')
		} else {
			uni.getUserProfile({
				desc: '用于头像昵称展示', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
				success: (res) => {
					console.log(res)
					nickName.value = res.userInfo.nickName
					avatarUrl.value = res.userInfo.avatarUrl
					uni.setStorageSync('avatarUrl', res.userInfo.avatarUrl);
					uni.setStorageSync('nickName', res.userInfo.nickName);
				}
			})
		}
	}
	//跳转详情
	const toOrder = ()=>{
		
	    //在起始页面跳转到details.vue页面并传递参数
	    uni.navigateTo({
	        url: '../order/order'
	    });
	}
	onShow(() => {
		userLogin()
		getUserInfo()
	})
</script>

<style lang="scss">
	page {
		background-color: #ededed;
	}

	.userinfo {
		margin: 30rpx 0rpx;
	}

	.img-container {
		display: flex;
		justify-content: center;
		align-items: center;
	}

	.uimage {
		height: 50px;
		width: 50px;
		border-radius: 50%;
		margin: 5px 0px;
	}

	.camera {
		width: 54px;
		height: 44px;

		&:active {
			background-color: #ededed;
		}
	}

	.user-box {
		background-color: #fff;
	}
</style>
