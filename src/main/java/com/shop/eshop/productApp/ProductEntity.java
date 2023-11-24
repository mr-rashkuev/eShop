package com.shop.eshop.productApp;

import com.shop.eshop.categoryApp.CategoryEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name")
    @NotNull
    @NotEmpty
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @Column(name = "price")
    @Min(value = 1, message = "Задана некорретная цена товара")
    private int price;
    @Column(name = "quantity")
    @Min(value = 0, message = "Задано некорреткное количество товара")
    private int quantity;

    public ProductEntity(String name, CategoryEntity category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
