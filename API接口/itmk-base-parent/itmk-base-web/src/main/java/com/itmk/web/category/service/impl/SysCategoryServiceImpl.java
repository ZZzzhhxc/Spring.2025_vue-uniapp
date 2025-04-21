package com.itmk.web.category.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itmk.web.category.entity.SysCategory;
import com.itmk.web.category.mapper.SysCategoryMapper;
import com.itmk.web.category.service.SysCategoryService;
import org.springframework.stereotype.Service;

@Service
public class SysCategoryServiceImpl extends ServiceImpl<SysCategoryMapper, SysCategory> implements SysCategoryService {
}
