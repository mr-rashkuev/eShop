package com.shop.eshop.productApp;

import com.shop.eshop.productApp.dto.ProductAndQuantity;
import com.shop.eshop.productApp.dto.ProductInputRq;
import com.shop.eshop.productApp.dto.ProductRs;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/")
    public List<ProductRs> showAllProducts() {
        return productService.showAllProducts();
    }

    @PostMapping("/")
    public void addProduct(@RequestBody ProductInputRq productInputRq) {
        productService.addProduct(productInputRq);
    }

    @GetMapping("/{id}")
    public ProductRs getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id,
                              @RequestParam int price,
                              @RequestParam int quantity) {
        productService.updateProduct(id, price, quantity);
    }

    @PutMapping("/quantity")
    public void addBatchToStore(@RequestBody List<ProductAndQuantity> productAndQuantity){
        productService.addProductQuantity(productAndQuantity);
    }
}
