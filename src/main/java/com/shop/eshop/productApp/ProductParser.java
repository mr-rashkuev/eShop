package com.shop.eshop.productApp;

import com.shop.eshop.productApp.dto.ProductFileImport;
import com.shop.eshop.productApp.dto.ProductInputRq;
import com.shop.eshop.productApp.dto.ProductRs;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ProductParser {
    public List<ProductFileImport> parse(InputStream file) {
        List<ProductFileImport> productFileImportList = new ArrayList<>();
        Workbook workbook;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            ProductFileImport productFileImport = new ProductFileImport();
            ProductFileImport.builder()
                    .name(row.getCell(0).getStringCellValue())
                    .category(row.getCell(1).getStringCellValue())
                    .price((int) row.getCell(3).getNumericCellValue())
                    .quantity((int) row.getCell(3).getNumericCellValue())
                    .build();
            productFileImportList.add(productFileImport);
        }
        return productFileImportList;
    }
}
