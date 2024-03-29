package com.shop.eshop.productApp;

import com.shop.eshop.categoryApp.CategoryEntity;
import com.shop.eshop.categoryApp.CategoryRepository;
import com.shop.eshop.customerApp.BusinessException;
import com.shop.eshop.orderListApp.OrderItemEntity;
import com.shop.eshop.productApp.dto.ProductDetails;
import com.shop.eshop.productApp.dto.ProductFileImport;
import com.shop.eshop.productApp.dto.ProductInputRq;
import com.shop.eshop.productApp.dto.ProductRs;
import com.shop.eshop.productApp.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final ProductValidator productValidator;
    private final ProductParser productParser;

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

    public void addProductQuantity(List<ProductDetails> productDetails) {
        List<Long> list = productDetails.stream().map(ProductDetails::getProductId).collect(Collectors.toList());
        List<ProductEntity> productEntityList = productRepository.findByProductIds(list);
//                .stream()
//                .map(productEntity -> productEntity.orElseThrow(() -> new BusinessException("Товар не найден")))
//                .collect(Collectors.toList());
        for (ProductDetails item : productDetails) {
            for (ProductEntity product : productEntityList) {
                if (Objects.equals(item.getProductId(), product.getId())) {
                    product.setQuantity(product.getQuantity() + item.getQuantity());
                    productRepository.save(product);
                }
            }
        }
    }

    public void addProductsFromFile(InputStream inputStream) {
        List<ProductFileImport> productFileImportList = productParser.parse(inputStream);
        List<String> names = productFileImportList.stream()
                .map(ProductFileImport::getName)
                .collect(Collectors.toList());
        List<ProductEntity> products = productRepository.findByProductNames(names);
        Map<ProductFileImport, Boolean> productEntityMap = productValidator.CheckProductExist(productFileImportList, products);
        for (Map.Entry<ProductFileImport, Boolean> entry : productEntityMap.entrySet()) {
            if (entry.getValue()) {
                ProductEntity product = productRepository.findByName(entry.getKey().getName());
                product.setQuantity(product.getQuantity()+entry.getKey().getQuantity());
            }else {
                CategoryEntity category = categoryRepository.findByName(entry.getKey().getCategory());
                addProduct(new ProductInputRq(entry.getKey().getName(),category.getId(),entry.getKey().getPrice(),entry.getKey().getPrice()));
            }
        }
    }
}
