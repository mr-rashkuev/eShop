package com.shop.eshop.categoryApp;

import com.shop.eshop.categoryApp.dto.CategoryRq;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/")
    public List<CategoryRq> showAllCategories() {
        return categoryService.showAllCategories();
    }
}
