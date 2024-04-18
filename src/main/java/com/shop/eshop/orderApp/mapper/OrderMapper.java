package com.shop.eshop.orderApp.mapper;

import com.shop.eshop.orderApp.OrderEntity;
import com.shop.eshop.orderApp.dto.OrderInputRq;
import com.shop.eshop.orderApp.dto.OrderRs;
import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.orderListApp.dto.OrderItemRs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {


    @Mapping(target = "customer", source = "order.customer.firstName")
    @Mapping(target = "orderItemList", source = "productList", qualifiedByName = "toORs")
    OrderRs toDto(OrderEntity order);

    @Mapping(target = "customer", ignore = true)
    OrderEntity toEntity(OrderInputRq orderInputRq);

    @Named("toORs")
    default List<OrderItemRs> orderItemToOrderItemRs(List<OrderItemEntity> orderItem) {
        return orderItem.stream()
                .map(order -> new OrderItemRs(order.getProduct().getName(),
                        order.getQuantity())).collect(Collectors.toList());
    }

}
