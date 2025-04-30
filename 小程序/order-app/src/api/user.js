import http from '../common/http.js'
//获取code
export const getCode = () => {
	const promise = new Promise((resolve, reject) => {
		uni.login({
			provider: 'weixin', //使用微信登录
			success: function(loginRes) {
				console.log('8888888888')
				console.log(loginRes.code);
				//如果返回数据
				if (loginRes && loginRes.code) {
					//数据返回
					resolve(loginRes.code)
				} else {
					reject(loginRes)
				}
			}
		})
	}).catch(res => {
		uni.showToast({
			icon: 'none',
			title: res.errMsg || '获取code失败!'
		})
	})
	return promise;
}
//小程序登录
export const wxLoginApi = (code) => {
	return http.post('/wxapi/user/wxLogin', {
		code: code
	})
}
//封装登录
export const userLogin = async () => {
	//获取code
	let res = await getCode()
	const {
		data
	} = await wxLoginApi(res)
	console.log('登录')
	console.log(data)
	if (data) {
		//存储
		uni.setStorageSync('openid', data.openid)
		uni.setStorageSync('sessionkey', data.sessionKey)
	}
}
//新增地址
export const addAddressApi = (parm)=>{
	return http.post("/wxapi/address",parm)
}
//地址列表
export const addressListApi = (parm)=>{
	return http.get("/wxapi/address/list",parm)
}
//编辑地址
export const editAddressApi = (parm)=>{
	return http.put("/wxapi/address",parm)
}
//默认地址查询
export const getAddressApi = (parm)=>{
	return http.get("/wxapi/address/getAddress",parm)
}