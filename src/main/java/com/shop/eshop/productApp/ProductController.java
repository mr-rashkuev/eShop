package com.shop.eshop.productApp;

import com.shop.eshop.productApp.dto.ProductDetails;
import com.shop.eshop.productApp.dto.ProductInputRq;
import com.shop.eshop.productApp.dto.ProductRs;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    public void addBatchToStore(@RequestBody List<ProductDetails> productDetails) {
        productService.addProductQuantity(productDetails);
    }

    @PostMapping("/file")
    public void addProductFromFile(@RequestParam MultipartFile file) throws IOException {
        try (InputStream ins = file.getInputStream()) {
            productService.addProductsFromFile(ins);

        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @GetMapping("/export")
    public ResponseEntity<Resource> exportToExcel() throws IOException {
        InputStream excel = productService.exportProductListToFile();
        Resource resource = new InputStreamResource(excel);

        MediaType mediaType = MediaType.parseMediaType("application/xlsx");
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename("список.xlsx", StandardCharsets.UTF_8)
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(contentDisposition);
        httpHeaders.setContentType(mediaType);
        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }
}
