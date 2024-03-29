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
        Workbook workbook;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            Iterator<Cell> cellIterator = row.iterator();
            ProductInputRq productInputRq = new ProductInputRq();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (cell.getStringCellValue().equals("Наименование")) {

                }
            }
        }
        return new ArrayList<>();
    }


}
