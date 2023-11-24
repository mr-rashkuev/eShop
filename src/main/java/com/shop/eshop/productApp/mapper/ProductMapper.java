package com.shop.eshop.productApp.mapper;

import com.shop.eshop.productApp.ProductEntity;
import com.shop.eshop.productApp.dto.ProductInputRq;
import com.shop.eshop.productApp.dto.ProductOutputRq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "category", source = "product.category.name")
    ProductOutputRq toDto(ProductEntity product);

    @Mapping(target = "category", ignore = true)
   // @Mapping(target = "id", ignore = true)
    ProductEntity toEntity(ProductInputRq productInputRq);
}
