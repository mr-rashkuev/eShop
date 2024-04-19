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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
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
        Map<ProductFileImport, Boolean> productEntityMap = checkProductExistenceInDataBase(productFileImportList);
        for (Map.Entry<ProductFileImport, Boolean> entry : productEntityMap.entrySet()) {
            if (entry.getValue()) {
                ProductEntity product = productRepository.findByName(entry.getKey().getName());
                product.setQuantity(product.getQuantity() + entry.getKey().getQuantity());
            } else {
                CategoryEntity category = categoryRepository.findByName(entry.getKey().getCategory()).orElseThrow();
                addProduct(new ProductInputRq(entry.getKey().getName(), category.getId(), entry.getKey().getPrice(), entry.getKey().getPrice()));
            }
        }
    }

    private Map<ProductFileImport, Boolean> checkProductExistenceInDataBase(List<ProductFileImport> productFileImportList) {
        List<String> names = productFileImportList.stream()
                .map(ProductFileImport::getName)
                .collect(Collectors.toList());
        List<ProductEntity> products = productRepository.findByProductNames(names);
        return productValidator.validate(productFileImportList, products);
    }

    public InputStream exportProductListToFile() throws IOException {
        List<ProductRs> productRsList = showAllProducts();
        InputStream inputStream;
        Workbook workbook = new SXSSFWorkbook();
        writeHeaders(workbook);
        writeValuesToFile(workbook, productRsList);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            workbook.write(out);
            byte[] bytes = out.toByteArray();
            inputStream = new ByteArrayInputStream(bytes);
            workbook.close();
        } catch (IOException e) {
            throw new IOException(e.getCause());

        }
        return inputStream;
    }

    private void writeHeaders(Workbook workbook){
        String[] headers = new String[]{"Идентификационный номер", "Наименование", "Категория", "Цена", "Количество"};
        Sheet sheet = workbook.createSheet("products");
        int cellNum = 0;
        Row row = sheet.createRow(0);
        for (String header : headers) {
            row.createCell(cellNum++).setCellValue(header);
        }
    }

    private void writeValuesToFile(Workbook workbook, List<ProductRs> productRsList){
        int rowCount = 1;
        for (ProductRs productRs : productRsList) {
            Row row = workbook.getSheet("products").createRow(rowCount++);
            row.createCell(0).setCellValue(productRs.getId());
            row.createCell(1).setCellValue(productRs.getName());
            row.createCell(2).setCellValue(productRs.getCategory());
            row.createCell(3).setCellValue(productRs.getPrice());
            row.createCell(4).setCellValue(productRs.getQuantity());
        }
    }


}



