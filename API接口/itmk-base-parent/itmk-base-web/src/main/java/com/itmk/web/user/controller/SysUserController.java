package com.itmk.web.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itmk.utils.ResultUtils;
import com.itmk.utils.ResultVo;
import com.itmk.web.user.entity.SysUser;
import com.itmk.web.user.entity.UserPageParm;
import com.itmk.web.user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @PostMapping
    public ResultVo add(@RequestBody SysUser user){
        if(sysUserService.save(user)){
            return ResultUtils.success("新增成功！");
        }
        return ResultUtils.error("新增失败！");
    }

    @PutMapping
    public ResultVo edit(@RequestBody SysUser user){
        if(sysUserService.save(user)){
            return ResultUtils.success("编辑成功！");
        }
        return ResultUtils.error("编辑失败！");
    }
    @DeleteMapping("/{userId}")
    public ResultVo delete(@PathVariable("userId") Long userId){
        if(sysUserService.removeById(userId)){
                return ResultUtils.success("删除成功！");
            }
            return ResultUtils.error("删除失败！");
        }
    @GetMapping("/list")
    public ResultVo list(UserPageParm parm){
        IPage<SysUser> page = new Page<>(parm.getCurrentPage(),parm.getPageSize());
        QueryWrapper<SysUser> query=new QueryWrapper<>();
        query.lambda().like(StringUtils.isNotEmpty(parm.getName()),SysUser::getName,parm.getName())
                      .like(StringUtils.isNotEmpty(parm.getPhone()),SysUser::getPhone,parm.getPhone())
                      .orderByAsc(SysUser::getName);
        IPage<SysUser> list = sysUserService.page(page,query);
        return ResultUtils.success("查询成功",list);
    }

}