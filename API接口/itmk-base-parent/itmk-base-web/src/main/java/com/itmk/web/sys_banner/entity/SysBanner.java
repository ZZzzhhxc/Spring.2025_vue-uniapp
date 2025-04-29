package com.itmk.web.sys_banner.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_banner")
public class SysBanner {
    @TableId(type = IdType.AUTO)
    private Long banId;
    private Long goodsId;
    private String title;
    private String images;
    private String status;
    private Integer orderNum;
}
