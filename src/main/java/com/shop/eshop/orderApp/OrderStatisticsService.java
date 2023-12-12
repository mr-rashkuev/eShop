package com.shop.eshop.orderApp;

import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.orderListApp.OrderItemRepository;
import com.shop.eshop.orderListApp.dto.ItemView;
import com.shop.eshop.orderListApp.dto.MostSells;
import com.shop.eshop.orderListApp.dto.OrderItemRs;
import com.shop.eshop.orderListApp.mapper.OrderItemMapper;
import com.shop.eshop.productApp.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;


@Service
@RequiredArgsConstructor
public class OrderStatisticsService {

    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;


    public List<MostSells> getMostSells() {
        return orderItemRepository.findMostSellProducts();
    }

    public List<MostSells> findMostSells() {
        Map<ProductEntity, Integer> map = orderItemRepository.findAll().stream()
                .collect(groupingBy(OrderItemEntity::getProduct, summingInt(OrderItemEntity::getQuantity)));
        List<MostSells> mostSells = new ArrayList<>();
        for (Map.Entry<ProductEntity, Integer> entry : map.entrySet()) {
            mostSells.add(new MostSells(entry.getKey().getName(), Long.valueOf(entry.getValue())));
        }
        return mostSells.stream().sorted(Comparator.comparing(MostSells::getQuantity, Comparator.reverseOrder())).limit(5).collect(Collectors.toList());
    }

    public List<ItemView> getByPeriod(LocalDateTime low, LocalDateTime high) {
        return orderItemRepository.getItemsByPeriod(low, high);
//        .stream()
//                .map(orderItemMapper::toDto)
//                .collect(Collectors.toList());
//}
    }

}
