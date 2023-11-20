package com.shop.eshop.customerApp.mapper;

import com.shop.eshop.customerApp.CustomerEntity;
import com.shop.eshop.customerApp.dto.CustomerRq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "id", source = "customerId")
    CustomerRq toDto(CustomerEntity customer);
    @Mapping(target = "customerId", source = "id")
    CustomerEntity toEntity(CustomerRq customerRq);
}
