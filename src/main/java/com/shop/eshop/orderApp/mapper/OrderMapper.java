package com.shop.eshop.orderApp.mapper;

import com.shop.eshop.customerApp.CustomerEntity;
import com.shop.eshop.customerApp.dto.CustomerRq;
import com.shop.eshop.orderApp.OrderEntity;
import com.shop.eshop.orderApp.dto.OrderInputRq;
import com.shop.eshop.orderApp.dto.OrderOutputRq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "customer", source = "order.customer.firstName")
    OrderOutputRq toDto(OrderEntity order);

    @Mapping(target = "customer", ignore = true)
    OrderEntity toEntity(OrderInputRq orderInputRq);
}
