package com.shop.eshop.productApp.dto;

import com.shop.eshop.categoryApp.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOutputRq {

    private Long id;
    private String name;
    private String category;
    private int price;
    private int quantity;

}
