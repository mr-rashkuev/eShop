package com.shop.eshop.productApp.dto;

import com.shop.eshop.orderListApp.OrderItemPK;

public class ProductQuantityInOrder {
    private OrderItemPK productId;
    private int quantityInOrder;

    public ProductQuantityInOrder(OrderItemPK productId, int quantityInOrder) {
        this.productId = productId;
        this.quantityInOrder = quantityInOrder;
    }

    public OrderItemPK getProductId() {
        return productId;
    }

    public void setProductId(OrderItemPK productId) {
        this.productId = productId;
    }

    public int getQuantityInOrder() {
        return quantityInOrder;
    }

    public void setQuantityInOrder(int quantityInOrder) {
        this.quantityInOrder = quantityInOrder;
    }
}
