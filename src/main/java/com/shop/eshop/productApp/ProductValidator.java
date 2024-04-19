package com.shop.eshop.productApp;

import com.shop.eshop.categoryApp.CategoryEntity;
import com.shop.eshop.categoryApp.CategoryRepository;
import com.shop.eshop.customerApp.BusinessException;
import com.shop.eshop.productApp.dto.ProductFileImport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class ProductValidator {

    private final CategoryRepository categoryRepository;


    public Map<ProductFileImport, Boolean> validate(List<ProductFileImport> productFileImportList, List<ProductEntity> products){
        categoryChecker(productFileImportList);
        return checkProductExist(productFileImportList, products);
    }
    private Map<ProductFileImport, Boolean> checkProductExist(List<ProductFileImport> productFileImportList, List<ProductEntity> products) {
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

    private void categoryChecker(List<ProductFileImport> productFileImportList){
        Set<String> setCategory = categoryRepository.findAll().stream().map(CategoryEntity::getName).collect(Collectors.toSet());
        for(ProductFileImport fileImport : productFileImportList){
            if(!setCategory.contains(fileImport.getCategory())){
                throw new BusinessException("задана неизвестная категория товаров");
            }
        }
    }

//    private boolean checkCategoryMatch(String name) {
//        return categoryRepository.findByName(name).isPresent();
//    }
}
