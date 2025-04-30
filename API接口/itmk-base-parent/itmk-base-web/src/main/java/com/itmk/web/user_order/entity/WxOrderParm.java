package com.itmk.web.user_order.entity;

import lombok.Data;

@Data
public class WxOrderParm {
    private String openid;
    private String type;
    private Long currentPage;
    private Long pageSize;
    private String userName;
}