package com.shop.eshop.productApp;

import com.shop.eshop.categoryApp.CategoryRepository;
import com.shop.eshop.productApp.dto.ProductFileImport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class ProductValidator {

    private final CategoryRepository categoryRepository;

    public Map<ProductFileImport, Boolean> checkProductExist(List<ProductFileImport> productFileImportList, List<ProductEntity> products) {
        Map<ProductFileImport, Boolean> checkMap = new HashMap<>();
        Set<String> productNamesSet = products.stream().map(ProductEntity::getName).collect(Collectors.toSet());
        for (ProductFileImport productFileImport : productFileImportList) {
            if (productNamesSet.contains(productFileImport.getName())) {
                checkMap.put(productFileImport, true);
            } else {
                checkMap.put(productFileImport, false);
            }
        }
        return checkMap;
    }

    public boolean checkCategoryMatch(String name) {
        return categoryRepository.findByName(name).isPresent();
    }
}
