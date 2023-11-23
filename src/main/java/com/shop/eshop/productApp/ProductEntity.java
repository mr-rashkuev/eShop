package com.shop.eshop.productApp;

import com.shop.eshop.categoryApp.CategoryEntity;
import lombok.*;

import javax.persistence.*;

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
    private String name;
    @ManyToOne
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private CategoryEntity category;
    @Column(name = "price")
    private int price;
    @Column(name = "quantity")
    private int quantity;

    public ProductEntity(String name, CategoryEntity category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
