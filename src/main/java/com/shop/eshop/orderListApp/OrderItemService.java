package com.shop.eshop.orderListApp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItemEntity> getAllItems(){
        return orderItemRepository.findAll();
    }

    public void saveOrderItem(OrderItemEntity orderItem){
        orderItemRepository.save(orderItem);
    }
}
