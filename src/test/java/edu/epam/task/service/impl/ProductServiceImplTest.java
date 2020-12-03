package edu.epam.task.service.impl;

import edu.epam.task.constant.Country;
import edu.epam.task.creator.WarehouseFromFileCreator;
import edu.epam.task.entity.Product;
import edu.epam.task.exception.FileCreatorException;
import edu.epam.task.exception.ProductServiceException;
import edu.epam.task.service.ProductService;
import edu.epam.task.storage.Warehouse;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ProductServiceImplTest {

    public static final String FILE_PATH = "input_files/warehouse_input.txt";
    static Warehouse warehouse;
    static WarehouseFromFileCreator creator;
    static List<Product> products;
    static ProductService<Product> productService;

    @BeforeClass
    public static void initialize() throws FileCreatorException {
        warehouse = Warehouse.getInstance();
        creator = new WarehouseFromFileCreator();
        products = creator.fillWarehouseFromFile(FILE_PATH);
        for (Product product : products) {
            warehouse.setProduct(product);
        }
        productService = ProductServiceImpl.getInstance();
    }

    @Test
    public void equalsFindByName() throws ProductServiceException {
        List<Product> actualResult = productService.findProductsByName("phone");
        List<Product> expectedResult = Arrays.asList(new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
                new Product("phone", 3755659225L, Country.UK, new BigDecimal("900"), 4, 100));
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void equalsFindProductsByExpirationYear() throws ProductServiceException {
        List<Product> actualResult = productService.findProductsByExpirationYear(4);
        List<Product> expectedResult = Arrays.asList(new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
                new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
                new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50));
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void equalsSortPrice() throws ProductServiceException {
        List<Product> actualResult = productService.sortByPrice();
        List<Product> expectedResult = Arrays.asList(new Product("powerbank", 6578405864L, Country.JAPAN, new BigDecimal("95"), 4, 80),
                new Product("sneakers", 2343563342L, Country.MALAYSIA, new BigDecimal("200"), 2, 120),
                new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
                new Product("phone", 3755659225L, Country.UK, new BigDecimal("900"), 4, 100),
                new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200),
                new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
                new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50));
        assertEquals(actualResult, expectedResult);
    }

    @AfterClass
    public static void clear() {
        warehouse = null;
        creator = null;
        products = null;
        productService = null;
    }
}
