package com.shop.eshop.productApp;

import com.shop.eshop.categoryApp.CategoryEntity;
import com.shop.eshop.categoryApp.CategoryRepository;
import com.shop.eshop.productApp.dto.ProductInputRq;
import com.shop.eshop.productApp.dto.ProductOutputRq;
import com.shop.eshop.productApp.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public void addProduct(ProductInputRq productInputRq) {
        ProductEntity product = productMapper.toEntity(productInputRq);
        CategoryEntity category = categoryRepository
                .findById(productInputRq.getCategory()).orElseThrow();
        product.setCategory(category);
        productRepository.save(product);
    }

    public List<ProductOutputRq> showAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
    }

    public void updateProduct(Long id, int price, int quantity) {
        ProductEntity product = productRepository.findById(id).orElseThrow();
        product.setPrice(price);
        product.setQuantity(quantity);
        productRepository.save(product);
    }

    public ProductOutputRq getProductById(Long id) {
        return productMapper.toDto(
                productRepository.findById(id).orElseThrow());
    }
}
