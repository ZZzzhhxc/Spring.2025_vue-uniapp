package com.itmk.web.address.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.address.entity.UserAddress;
import com.itmk.web.address.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class UserAddressController {
    @Autowired
    private UserAddressService userAddressService;

    //新增地址
    @PostMapping
    public ResultVo add(@RequestBody UserAddress userAddress){
        if(userAddressService.save(userAddress)){
            return ResultUtils.success("新增地址成功!");
        }
        return ResultUtils.error("新增地址失败!");
    }

    //列表
    @GetMapping("/list")
    public ResultVo list(){
        //排序：按status排序，默认的排在第一条
        QueryWrapper<UserAddress> query = new QueryWrapper<>();
        query.lambda().orderByDesc(UserAddress::getStatus);
        List<UserAddress> list = userAddressService.list(query);
        return ResultUtils.success("查询成功",list);
    }
}