package com.shop.eshop.orderApp;

import com.shop.eshop.Status;
import com.shop.eshop.customerApp.CustomerEntity;
import com.shop.eshop.customerApp.CustomerRepository;
import com.shop.eshop.orderApp.dto.OrderInputRq;
import com.shop.eshop.orderApp.dto.OrderOutputRq;
import com.shop.eshop.orderApp.mapper.OrderMapper;
import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.orderListApp.OrderItemRepository;
import com.shop.eshop.productApp.ProductEntity;
import com.shop.eshop.productApp.ProductRepository;
import com.shop.eshop.productApp.dto.ProductQuantityInOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;

    private final OrderItemRepository orderItemRepository;

    public List<OrderEntity> findOrderByCustomer(Long customerId) {
        return orderRepository.findOrdersByCustomer(customerId);
    }

    public List<OrderOutputRq> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(order -> orderMapper.toDto(order))
                .collect(Collectors.toList());
    }

    public void registerOrder(OrderInputRq orderInputRq) {
        OrderEntity order = orderMapper.toEntity(orderInputRq);
        CustomerEntity customer = customerRepository.findById(orderInputRq.getCustomer())
                .orElseThrow(() -> new NoSuchElementException("There is no such customer"));
        order.setCustomer(customer);
        checkAndAddProductToOrder(order, orderInputRq.getProducts());
        order.setStatus(Status.OPENED);
//        costCount(order);
        orderRepository.save(order);
    }

//    public void costCount(OrderEntity order) {
//        int count = 0;
//        for (ProductEntity product : order.getProductList()) {
//            count += product.getPrice();
//        }
//        order.setCost(count);
//    }

    public void checkAndAddProductToOrder(OrderEntity order, List<ProductQuantityInOrder> OrderItemEntityList) {
        for (ProductQuantityInOrder item : OrderItemEntityList) {
            if (orderItemRepository.findById(item.getProductId()).isPresent()) {
                OrderItemEntity orderItem = orderItemRepository.findById(item.getProductId()).orElseThrow();
                order.addProductToOrder(orderItem);
            }
        }
    }
}