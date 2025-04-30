import http from '../common/http.js'
export const splaceOrderApi = (parm)=>{
	return http.post("/wxapi/order/splaceOrder",parm)
}
export const getOrderListApi = (parm)=>{
	return http.get("/wxapi/order/getOrderList",parm)
}
export const cancelOrderApi = (parm)=>{
	return http.post("/wxapi/order/cancelOrder",parm)
}