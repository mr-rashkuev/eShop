package com.shop.eshop.orderListApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemEntity.OrderItemPK> {

    List<OrderItemEntity> findAllByOrderId(Long id);

    @Query("select productId, count(productId) as quantity from OrderItemEntity group by productId order by quantity")
    List<OrderItemEntity> findMostSellableProducts();
}
