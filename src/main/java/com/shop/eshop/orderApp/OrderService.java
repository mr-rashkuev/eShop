package com.shop.eshop.orderApp;

import com.shop.eshop.Status;
import com.shop.eshop.customerApp.CustomerEntity;
import com.shop.eshop.customerApp.CustomerRepository;
import com.shop.eshop.orderApp.dto.OrderInputRq;
import com.shop.eshop.orderApp.dto.OrderOutputRq;
import com.shop.eshop.orderApp.mapper.OrderMapper;
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

    public List<OrderEntity> findOrderByCustomer(Long customerId){
        return orderRepository.findOrdersByCustomer(customerId);
    }

    public List<OrderOutputRq> getAllOrders(){
        return orderRepository.findAll()
                .stream()
                .map(order-> orderMapper.toDto(order))
                .collect(Collectors.toList());
    }

    public void registerOrder(OrderInputRq orderInputRq){
        OrderEntity order = orderMapper.toEntity(orderInputRq);
        CustomerEntity customer = customerRepository.findById(orderInputRq.getCustomer())
                .orElseThrow(()->new NoSuchElementException("There is no such customer"));

        order.setCustomer(customer);
        order.setDate(LocalDateTime.now());
        order.setCost(10);
        order.setStatus(Status.OPENED);
        orderRepository.save(order);
    }
}
