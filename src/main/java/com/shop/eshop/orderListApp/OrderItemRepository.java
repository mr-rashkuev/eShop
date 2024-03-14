package com.shop.eshop.orderListApp;

import com.shop.eshop.orderListApp.dto.ItemView;
import com.shop.eshop.orderListApp.dto.MostSells;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;


public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemEntity.OrderItemPK> {

    List<OrderItemEntity> findAllByOrderId(Long id);

    @Query("SELECT new com.shop.eshop.orderListApp.dto.MostSells(oi.product.name, sum(oi.quantity)) " +
            "FROM OrderItemEntity oi GROUP BY oi.productId,oi.product.name ORDER BY sum(oi.quantity) DESC")
    List<MostSells> findMostSellProducts();

    @Query("select oi.productId as id, oi.product.name as name, sum(oi.quantity) as quantity FROM OrderItemEntity oi join " +
            "OrderEntity o on oi.orderId=o.orderId where o.date between ?1 and ?2 group by oi.productId,oi.product.name order by quantity DESC")
    List<ItemView> getItemsByPeriod(LocalDateTime low, LocalDateTime high);
}
