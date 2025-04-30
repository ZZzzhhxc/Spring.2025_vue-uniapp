package com.itmk.web.user_order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.user_order.entity.OrderParm;
import com.itmk.web.user_order.entity.SendParm;
import com.itmk.web.user_order.entity.UserOrder;
import com.itmk.web.user_order.entity.WxOrderParm;
import com.itmk.web.user_order.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wxapi/order")
public class UserOrderController {
    @Autowired
    private UserOrderService userOrderService;

    //下单
    @PostMapping("/placeOrder")
    public ResultVo placeOrder(@RequestBody OrderParm parm){
        userOrderService.placeOrder(parm);
        return ResultUtils.success("提交成功!");
    }

    //查询订单
    @GetMapping("/getOrderList")
    public ResultVo getOrderList(WxOrderParm parm){
        IPage<UserOrder> orderList = userOrderService.getOrderList(parm);
        return ResultUtils.success("查询成功!",orderList);
    }
    //pc端查询订单
    @GetMapping("/getPcOrderList")
    public ResultVo getPcOrderList(WxOrderParm parm){
        IPage<UserOrder> orderList = userOrderService.getPcOrderList(parm);
        return ResultUtils.success("查询成功!",orderList);
    }
    //发货
    @PutMapping("/sendOrder")
    public ResultVo sendOrder(@RequestBody SendParm parm){
        //判断订单是否被取消
        QueryWrapper<UserOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserOrder::getOrderId,parm.getOrderId()).eq(UserOrder::getStatus,"3");
        UserOrder one = userOrderService.getOne(queryWrapper);
        if(one != null){
            return ResultUtils.error("订单已被取消，不能发货!");
        }
        //更新条件
        LambdaUpdateWrapper<UserOrder> query = new LambdaUpdateWrapper<>();
        query.eq(UserOrder::getOrderId,parm.getOrderId())
                .set(UserOrder::getStatus,"1");
        if(userOrderService.update(query)){
            return ResultUtils.success("更新成功!");
        }
        return ResultUtils.error("更新失败!");
    }
    //取消订单
    @PostMapping("/cancelOrder")
    public ResultVo cancelOrder(@RequestBody SendParm parm){
        //如果已发货，不能取消
        QueryWrapper<UserOrder> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserOrder::getOrderId,parm.getOrderId()).eq(UserOrder::getStatus,"1");
        UserOrder one = userOrderService.getOne(queryWrapper);
        if(one != null){
            return ResultUtils.error("订单已发货，不能取消!");
        }
        //更新条件
        LambdaUpdateWrapper<UserOrder> query = new LambdaUpdateWrapper<>();
        query.eq(UserOrder::getOrderId,parm.getOrderId())
                .set(UserOrder::getStatus,"3");
        if(userOrderService.update(query)){
            return ResultUtils.success("取消成功!");
        }
        return ResultUtils.error("取消失败!");
    }
}
