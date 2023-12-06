package com.shop.eshop.orderListApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrderItemRs {

    private String product;
    private int quantity;

}
