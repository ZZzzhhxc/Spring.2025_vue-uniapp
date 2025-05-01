package com.itmk.web.user_order.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SunList {
    private String days;
    private BigDecimal price;
}
