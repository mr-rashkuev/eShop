package com.shop.eshop.categoryApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRq {
    private Long id;
    private String name;

    public CategoryRq(String name) {
        this.name = name;
    }
}
