package com.shop.eshop.orderListApp.mapper;

import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.orderListApp.dto.OrderItemRs;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "product", source = "orderItem.product.name")
    OrderItemRs toDto(OrderItemEntity orderItem);

    @Mapping(target = "product", source = "orderItem.product.name")
    List<OrderItemRs> toDto(List<OrderItemEntity> orderItemEntityList);
}
