package com.itmk.web.user_order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.user_order.entity.OrderParm;
import com.itmk.web.user_order.entity.ParmDetail;
import com.itmk.web.user_order.entity.UserOrder;
import com.itmk.web.user_order.entity.WxOrderParm;
import com.itmk.web.user_order.mapper.UserOrderMapper;
import com.itmk.web.user_order.service.UserOrderService;
import com.itmk.web.user_order_detail.entity.UserOrderDetail;
import com.itmk.web.user_order_detail.service.UserOrderDetailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserOrderServiceImpl extends ServiceImpl<UserOrderMapper, UserOrder> implements UserOrderService {
    @Resource
    private UserOrderDetailService userOrderDetailService;


    @Override
    @Transactional
    public void placeOrder(OrderParm orderParm) {
        //主订单
        UserOrder order = new UserOrder();
        BeanUtils.copyProperties(orderParm,order);
        order.setStatus("0");
        order.setCreateTime(new Date());
        this.baseMapper.insert(order);
        //子订单
        List<ParmDetail> details = orderParm.getDetails();
        List<UserOrderDetail> list = new ArrayList<>();
        for(int i = 0;i<details.size();i++){
            UserOrderDetail detail = new UserOrderDetail();
            BeanUtils.copyProperties(details.get(i),detail);
            detail.setOrderId(order.getOrderId());
            list.add(detail);
        }
        userOrderDetailService.saveBatch(list);
    }

    @Override
    public IPage<UserOrder> getOrderList(WxOrderParm parm) {
        //查询订单
        QueryWrapper<UserOrder> query = new QueryWrapper<>();
        query.lambda().eq(UserOrder::getOpenid,parm.getOpenid())
                .eq(StringUtils.isNotEmpty(parm.getType()),UserOrder::getStatus,parm.getType())
                .orderByDesc(UserOrder::getCreateTime);
        IPage<UserOrder> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        IPage<UserOrder> order = this.baseMapper.selectPage(page, query);
        //有数据，查询子订单
        if(order.getRecords().size()>0){
            for (int i = 0;i<order.getRecords().size();i++){
                QueryWrapper<UserOrderDetail> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(UserOrderDetail::getOrderId,order.getRecords().get(i).getOrderId());
                List<UserOrderDetail> list = userOrderDetailService.list(queryWrapper);
                order.getRecords().get(i).setGoodsList(list);
            }

        }
        return order;
    }

    @Override
    public IPage<UserOrder> getPcOrderList(WxOrderParm parm) {
        //查询订单
        QueryWrapper<UserOrder> query = new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getUserName()),UserOrder::getUserName,parm.getUserName())
                .eq(StringUtils.isNotEmpty(parm.getType()),UserOrder::getStatus,parm.getType())
                .orderByDesc(UserOrder::getCreateTime);
        IPage<UserOrder> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        IPage<UserOrder> order = this.baseMapper.selectPage(page, query);
        //有数据，查询子订单
        if(order.getRecords().size()>0){
            for (int i = 0;i<order.getRecords().size();i++){
                QueryWrapper<UserOrderDetail> queryWrapper = new QueryWrapper<>();
                queryWrapper.lambda().eq(UserOrderDetail::getOrderId,order.getRecords().get(i).getOrderId());
                List<UserOrderDetail> list = userOrderDetailService.list(queryWrapper);
                order.getRecords().get(i).setGoodsList(list);
            }

        }
        return order;
    }
}
