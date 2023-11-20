package com.shop.eshop.productApp.dto;

import com.shop.eshop.categoryApp.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductInputRq {

    private String name;
    private Long category;
    private int price;
    private int quantity;


}
