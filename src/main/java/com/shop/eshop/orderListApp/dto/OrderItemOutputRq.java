package com.shop.eshop.orderListApp.dto;

import com.shop.eshop.orderApp.OrderEntity;
import com.shop.eshop.productApp.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
@AllArgsConstructor
@Getter
@Setter
public class OrderItemOutputRq {

    private String product;
    private int quantity;

}
