package com.shop.eshop.orderApp;

import com.shop.eshop.customerApp.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findOrdersByCustomer(CustomerEntity customer);
}
