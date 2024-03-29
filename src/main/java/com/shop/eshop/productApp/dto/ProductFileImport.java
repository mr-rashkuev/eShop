package com.shop.eshop.productApp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductFileImport {

    private String name;
    private String category;
    private int price;
    private int quantity;

}
