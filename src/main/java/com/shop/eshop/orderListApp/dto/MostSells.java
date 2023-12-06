package com.shop.eshop.orderListApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class MostSells {

    private String name;
    private Long quantity;

}
