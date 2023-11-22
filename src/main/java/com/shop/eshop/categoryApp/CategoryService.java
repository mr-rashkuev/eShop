package com.shop.eshop.categoryApp;

import com.shop.eshop.categoryApp.mapper.CategoryMapper;
import com.shop.eshop.categoryApp.dto.CategoryRq;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryRq> showAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .collect(Collectors.toList());
    }

    public void addCategory(CategoryRq categoryRq) {
        categoryRepository.save(categoryMapper.toEntity(categoryRq));
    }

}

