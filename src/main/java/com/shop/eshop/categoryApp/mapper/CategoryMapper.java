package com.shop.eshop.categoryApp.mapper;

import com.shop.eshop.categoryApp.CategoryEntity;
import com.shop.eshop.categoryApp.dto.CategoryRq;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryRq toDto(CategoryEntity category);
    CategoryEntity toEntity(CategoryRq categoryRq);
}
