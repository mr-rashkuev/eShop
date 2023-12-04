package com.shop.eshop.orderListApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemEntity.OrderItemPK> {

    List<OrderItemEntity> findAllByOrderId(Long id);

//    @Query("select oi.productId, sum(oi.quantity) from OrderItemEntity oi join oi.orderId  group by oi.productId order by sum(oi.quantity)")
//    List<OrderItemEntity> findMostSellableProducts();
}
