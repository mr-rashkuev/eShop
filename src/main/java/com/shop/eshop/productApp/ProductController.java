package com.shop.eshop.productApp;

import com.shop.eshop.productApp.dto.ProductDetails;
import com.shop.eshop.productApp.dto.ProductInputRq;
import com.shop.eshop.productApp.dto.ProductRs;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
    public void addBatchToStore(@RequestBody List<ProductDetails> productDetails){
        productService.addProductQuantity(productDetails);
    }

    @PostMapping("/file")
    public void addProductFromFile(@RequestBody File file) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(file);
        productService.addProductsFromFile(inputStream);
    }


}
