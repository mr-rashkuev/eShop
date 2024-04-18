package com.shop.eshop.productApp.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ProductFileImport {

    private String name;
    private String category;
    private int price;
    private int quantity;

}
