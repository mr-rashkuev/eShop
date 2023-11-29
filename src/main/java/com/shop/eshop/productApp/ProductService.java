package com.shop.eshop.productApp;

import com.shop.eshop.categoryApp.CategoryEntity;
import com.shop.eshop.categoryApp.CategoryRepository;
import com.shop.eshop.customerApp.BusinessException;
import com.shop.eshop.productApp.dto.ProductAndQuantity;
import com.shop.eshop.productApp.dto.ProductInputRq;
import com.shop.eshop.productApp.dto.ProductRs;
import com.shop.eshop.productApp.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

    public List<ProductRs> showAllProducts() {
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

    public ProductRs getProductById(Long id) {
        return productMapper.toDto(
                productRepository.findById(id).orElseThrow());
    }

    public void addProductQuantity(List<ProductAndQuantity> productAndQuantity) {
        List<Long> list = productAndQuantity.stream().map(ProductAndQuantity::getProductId).collect(Collectors.toList());
        List<ProductEntity> productEntityList = productRepository.findByProductIds(list)
                .stream()
                .map(productEntity -> productEntity.orElseThrow(() -> new BusinessException("Товар не найден")))
                .collect(Collectors.toList());
        for (ProductAndQuantity item : productAndQuantity) {
            for (ProductEntity product : productEntityList) {
                if (Objects.equals(item.getProductId(), product.getId())) {
                    product.setQuantity(product.getQuantity() + item.getQuantity());
                    productRepository.save(product);
                }
            }
        }
    }
}
