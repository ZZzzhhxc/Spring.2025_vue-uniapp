package com.itmk.web.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class SysUser<string> {
    @TableId(type= IdType.AUTO)
    private Long userId;
    private string username;
    private string password;
    private string phone;
    private string email;
    private string sex;
    private string name;
}
