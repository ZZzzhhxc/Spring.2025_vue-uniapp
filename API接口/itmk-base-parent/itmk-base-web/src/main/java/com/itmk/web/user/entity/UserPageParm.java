package com.itmk.web.user.entity;

import lombok.Data;

@Data
public class UserPageParm {
    private Integer currentPage;//当前页数
    private Integer pageSize;//每页查询条数
    private String name;
    private String phone;

}
