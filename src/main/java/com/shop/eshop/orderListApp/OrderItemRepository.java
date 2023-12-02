package com.shop.eshop.orderListApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemEntity.OrderItemPK> {

    List<OrderItemEntity> findAllByOrderId(Long id);

    @Query("select productId, sum(quantity) from OrderItemEntity group by productId order by sum(quantity)")
    List<OrderItemEntity> findMostSellableProducts();
}
