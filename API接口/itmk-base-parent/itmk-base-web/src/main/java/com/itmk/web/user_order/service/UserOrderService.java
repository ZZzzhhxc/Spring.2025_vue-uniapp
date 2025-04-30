package com.itmk.web.user_order.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itmk.web.user_order.entity.OrderParm;
import com.itmk.web.user_order.entity.UserOrder;
import com.itmk.web.user_order.entity.WxOrderParm;


public interface UserOrderService extends IService<UserOrder> {
    void placeOrder(OrderParm orderParm);
    IPage<UserOrder> getOrderList(WxOrderParm parm);
    IPage<UserOrder> getPcOrderList(WxOrderParm parm);
}
