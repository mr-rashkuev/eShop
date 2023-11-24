package com.shop.eshop.orderListApp.mapper;

import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.orderListApp.dto.OrderItemOutputRq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface OrderItemMapper {

    @Mapping(target = "product", source = "orderItem.product.name")
    OrderItemOutputRq toDto(OrderItemEntity orderItem);
}
