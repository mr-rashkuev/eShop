package com.shop.eshop.categoryApp.mapper;

import com.shop.eshop.categoryApp.CategoryEntity;
import com.shop.eshop.categoryApp.dto.CategoryRq;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryRq toDto(CategoryEntity category);

    CategoryEntity toEntity(CategoryRq categoryRq);
}
