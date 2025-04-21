package com.itmk.web.goods.controller;

import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.goods.entity.GoodsParm;
import com.itmk.web.goods.service.SysGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/goods")
public class SysGoodsController {
    @Autowired
    private SysGoodsService sysGoodsService;

    //新增菜品
    @PostMapping
    public ResultVo add(@RequestBody GoodsParm parm){
        sysGoodsService.saveGoods(parm);
        return ResultUtils.success("新增成功!");
    }
}
