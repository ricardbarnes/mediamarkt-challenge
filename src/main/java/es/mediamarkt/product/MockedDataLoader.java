package es.mediamarkt.product;

import es.mediamarkt.product.domain.products.model.*;
import es.mediamarkt.product.domain.products.service.ProductIdGenerator;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MockedDataLoader {

    private final JdbcTemplate jdbcTemplate;
    private final ProductIdGenerator productIdGenerator;

    @Transactional
    public void loadMockedData() {
        loadCategories();
        loadProducts();
    }

    private void loadCategories() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("categories/xlsx/tv_and_audio_categories.xlsx")) {
            if (is == null) {
                throw new IllegalStateException("Resource tv_and_audio_categories.xlsx not found in resources folder");
            }

            try (Workbook workbook = new XSSFWorkbook(is)) {
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();

                // 1. Skip the CSV/Excel Header Row (<ID>, <Name>, <Parent ID>)
                if (rowIterator.hasNext()) {
                    rowIterator.next();
                }
                // 2. Skip the First Data Row (202, TV & Audio, MediaMarkt_DE)
                if (rowIterator.hasNext()) {
                    rowIterator.next();
                }

                String sql = "INSERT INTO categories (id, name, catalog_id) VALUES (?, ?, ?)";

                // 3. Process the remaining rows as Categories directly via SQL
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    Cell idCell = row.getCell(0);
                    Cell nameCell = row.getCell(1);
                    Cell parentIdCell = row.getCell(2);

                    if (idCell == null || nameCell == null || parentIdCell == null) {
                        continue;
                    }

                    long id = (long) idCell.getNumericCellValue();
                    String name = getCellValueAsString(nameCell);
                    long parentId = (long) parentIdCell.getNumericCellValue();

                    if (name.isEmpty()) {
                        continue;
                    }

                    // Directly executing SQL inserts bypasses Hibernate's merge vs persist tracking completely
                    jdbcTemplate.update(sql, id, name, parentId);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load and save categories inside MockedDataLoader", e);
        }
    }

    private void loadProducts() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("products/xlsx/tv_and_audio_products.xlsx")) {
            if (is == null) {
                throw new IllegalStateException("Resource tv_and_audio_products.xlsx not found in resources folder");
            }

            try (Workbook workbook = new XSSFWorkbook(is)) {
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rowIterator = sheet.iterator();

                if (rowIterator.hasNext()) {
                    rowIterator.next();
                }

                String productSql = "INSERT INTO products (id, name, online_status, long_description, short_description) VALUES (?, ?, ?, ?, ?)";
                String relationshipSql = "INSERT INTO products_categories (product_id, categories_id) VALUES (?, ?)";

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    Cell nameCell = row.getCell(0);
                    Cell categoriesCell = row.getCell(1);
                    Cell statusCell = row.getCell(2);
                    Cell longDescCell = row.getCell(3);
                    Cell shortDescCell = row.getCell(4);

                    String productNameText = getCellValueAsString(nameCell);
                    if (productNameText.isEmpty()) {
                        continue;
                    }

                    String categoriesRaw = getCellValueAsString(categoriesCell);
                    String statusRaw = getCellValueAsString(statusCell);
                    String longDescRaw = getCellValueAsString(longDescCell);
                    String shortDescRaw = getCellValueAsString(shortDescCell);

                    // Parse category string sequence
                    Set<Long> categoryIds = Arrays.stream(categoriesRaw.split(";"))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty())
                            .map(Long::parseLong)
                            .collect(Collectors.toSet());

                    // Domain ID generation via injected service port
                    long generatedProductId = productIdGenerator.generate().value();

                    // 1. Insert product base
                    jdbcTemplate.update(productSql,
                            generatedProductId,
                            productNameText,
                            statusRaw,
                            longDescRaw,
                            shortDescRaw
                    );

                    // 2. Insert joint category relations via direct native mapping loop
                    for (Long categoryId : categoryIds) {
                        try {
                            jdbcTemplate.update(relationshipSql, generatedProductId, categoryId);
                        } catch (org.springframework.jdbc.BadSqlGrammarException ex) {
                            // Secondary fallback catch block in case your ManyToMany mapping is singularized ('product_categories' instead of 'products_categories')
                            String fallbackSql = "INSERT INTO product_categories (product_id, category_id) VALUES (?, ?)";
                            jdbcTemplate.update(fallbackSql, generatedProductId, categoryId);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load and save products inside MockedDataLoader", e);
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                double numericValue = cell.getNumericCellValue();
                if (numericValue == (long) numericValue) {
                    return String.valueOf((long) numericValue);
                }
                return String.valueOf(numericValue);
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }

}