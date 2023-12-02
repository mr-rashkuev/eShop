package com.shop.eshop.orderApp;

import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.orderListApp.OrderItemRepository;
import com.shop.eshop.orderListApp.mapper.OrderItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderStatisticsService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;



    public List<OrderItemEntity> getMostSells(){
        return orderItemRepository.findMostSellableProducts();
    }
}
