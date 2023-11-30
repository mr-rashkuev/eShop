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

//    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    @Mapping(target = "customer", source = "order.customer.firstName")
//    @Mapping(target = "orderItemList", source = "productList", qualifiedByName = "toORs")
    OrderRs toDto(OrderEntity order);

    @Mapping(target = "customer", ignore = true)
    OrderEntity toEntity(OrderInputRq orderInputRq);

//    @Named("toORs")
//    static OrderItemRs orderItemToOrderItemRs(OrderItemEntity orderItem) {
//        return new OrderItemRs(orderItem.getProduct().getName(), orderItem.getQuantity());
//    }
}
