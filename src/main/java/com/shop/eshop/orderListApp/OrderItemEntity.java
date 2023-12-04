package com.shop.eshop.orderListApp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.eshop.orderApp.OrderEntity;
import com.shop.eshop.productApp.ProductEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@IdClass(OrderItemEntity.OrderItemPK.class)
@Table(name = "order_item")
@ToString
public class OrderItemEntity {
    @Id
    @Column(name = "order_id")
    private Long orderId;
    @Id
    @Column(name = "product_id")
    private Long productId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", insertable=false, updatable=false)
 //   @JsonIgnore
    private OrderEntity order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable=false, updatable=false)
 //   @JsonIgnore
    private ProductEntity product;
    //@Min(value = 1, message = "Задано некорреткное количество товара")
    private int quantity;

    public OrderItemEntity(Long orderId, Long productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    @Getter
    @Setter
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderItemPK implements Serializable {
        @Column(name = "order_id")
        private Long orderId;
        @Column(name = "product_id")
        private Long productId;

    }

}
