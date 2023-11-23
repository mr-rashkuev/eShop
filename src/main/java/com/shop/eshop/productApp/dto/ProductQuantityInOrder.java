package com.shop.eshop.productApp.dto;



public class ProductQuantityInOrder {
    private Long productId;
    private int quantityInOrder;

    public ProductQuantityInOrder(Long productId, int quantityInOrder) {
        this.productId = productId;
        this.quantityInOrder = quantityInOrder;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantityInOrder() {
        return quantityInOrder;
    }

    public void setQuantityInOrder(int quantityInOrder) {
        this.quantityInOrder = quantityInOrder;
    }
}
