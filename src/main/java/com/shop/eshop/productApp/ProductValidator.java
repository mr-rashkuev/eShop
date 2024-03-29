package com.shop.eshop.productApp;

import com.shop.eshop.categoryApp.CategoryRepository;
import com.shop.eshop.productApp.dto.ProductFileImport;
import com.shop.eshop.productApp.dto.ProductRs;
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
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public Map<ProductFileImport, Boolean> CheckProductExist(List<ProductFileImport> productFileImportList, List<ProductEntity> products) {

        Map<ProductFileImport, Boolean> checkMap = new HashMap<>();
        for (ProductFileImport productFileImport : productFileImportList) {
            for (ProductEntity product : products) {
                if(productFileImport.getName().equals(product.getName())){
                    checkMap.put(productFileImport, true);
                }
                else{checkMap.put(productFileImport, false);}

            }
        }
        return checkMap;
    }

    public boolean checkCategoryMatch(String name) {
        if (categoryRepository.findByName(name) != null) {
            return true;
        }
        return false;
    }
}
