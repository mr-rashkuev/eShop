package com.shop.eshop.orderListApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemEntity.OrderItemPK> {
}
