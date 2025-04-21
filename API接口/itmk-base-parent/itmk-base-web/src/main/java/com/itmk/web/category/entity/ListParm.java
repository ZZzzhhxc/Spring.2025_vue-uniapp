package com.itmk.web.category.entity;

import lombok.Data;

@Data
public class ListParm {
    private Integer currentPage;//当前页数
    private Integer pageSize;//每页查询条数
    private String categoryName;
}
