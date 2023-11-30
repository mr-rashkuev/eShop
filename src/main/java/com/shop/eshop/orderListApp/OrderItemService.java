package com.shop.eshop.orderListApp;

import com.shop.eshop.orderListApp.dto.OrderItemRs;
import com.shop.eshop.orderListApp.mapper.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    public List<OrderItemRs> getAllItems(){
        return orderItemRepository.findAll().stream().map(orderItemMapper::toDto).collect(Collectors.toList());
    }

    public void saveOrderItem(OrderItemEntity orderItem){
        orderItemRepository.save(orderItem);
    }
}
