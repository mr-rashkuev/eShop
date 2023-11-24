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
    @NotEmpty
    @NotNull
    private String name;
    @NotNull
    private Long category;
    @NotNull
    @Min(1)
    private int price;
    @NotNull
    @Min(value = 0, message = "Задано некорреткное количество товара")
    private int quantity;


}
