package com.shop.eshop.orderApp;

import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.orderListApp.OrderItemRepository;
import com.shop.eshop.orderListApp.dto.MostSells;
import com.shop.eshop.orderListApp.dto.OrderItemRs;
import com.shop.eshop.productApp.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;


@Service
@RequiredArgsConstructor
public class OrderStatisticsService {

    private final OrderItemRepository orderItemRepository;


//    public List<OrderItemEntity> getMostSells(){
//        return orderItemRepository.findMostSellableProducts();
//    }

    public List<MostSells> someMethod(){
        Map<ProductEntity, Integer> map = orderItemRepository.findAll().stream().collect(groupingBy(OrderItemEntity::getProduct, Collectors.summingInt(OrderItemEntity::getQuantity)));
        List<MostSells> mostSells = new ArrayList<>();
        for(Map.Entry <ProductEntity, Integer> entry: map.entrySet()){
            mostSells.add(new MostSells(entry.getKey().getName(), entry.getValue()));
        }
        return mostSells.stream().sorted(Comparator.comparing(MostSells::getQuantity, Comparator.reverseOrder())).limit(5).collect(Collectors.toList());
    }
}
