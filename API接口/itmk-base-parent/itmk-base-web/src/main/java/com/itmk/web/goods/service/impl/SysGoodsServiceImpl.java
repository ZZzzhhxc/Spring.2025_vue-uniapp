package com.itmk.web.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.goods.entity.GoodsParm;
import com.itmk.web.goods.entity.SysGoods;
import com.itmk.web.goods.mapper.SysGoodsMapper;
import com.itmk.web.goods.service.SysGoodsService;
import com.itmk.web.goods_specs.entity.SysGoodsSpecs;
import com.itmk.web.goods_specs.service.SysGoodsSpecsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SysGoodsServiceImpl extends ServiceImpl<SysGoodsMapper, SysGoods> implements SysGoodsService {
    @Autowired
    private SysGoodsSpecsService sysGoodsSpecsService;

    @Override
    @Transactional
    public void saveGoods(GoodsParm parm) {
        //1、插入菜品
        SysGoods goods = new SysGoods();
        BeanUtils.copyProperties(parm,goods);
        int insert = this.baseMapper.insert(goods);
        //2、插入规格
        if(insert > 0){
            List<SysGoodsSpecs> specs = parm.getSpecs();
            for (int i = 0; i < specs.size(); i++) {
                specs.get(i).setGoodsId(goods.getGoodsId());
            }
            sysGoodsSpecsService.saveBatch(specs);
        }
    }
}
