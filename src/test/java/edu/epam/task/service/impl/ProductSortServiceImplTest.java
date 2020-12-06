package edu.epam.task.service.impl;

import edu.epam.task.constant.Country;
import edu.epam.task.creator.WarehouseFromFileCreator;
import edu.epam.task.entity.Product;
import edu.epam.task.exception.FileCreatorException;
import edu.epam.task.exception.ServiceException;
import edu.epam.task.service.ProductSortService;
import edu.epam.task.storage.Warehouse;

import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ProductSortServiceImplTest {

    public static final String FILE_PATH = "input_files/warehouse_input.txt";
    static Warehouse warehouse;
    static List<Product> products;
    static WarehouseFromFileCreator creator;
    static ProductSortService<Product> sortService;

    List<Product> expectedResultAfterSortByUPC = Arrays.asList(
            new Product("sneakers", 2343563342L, Country.MALAYSIA, new BigDecimal("200"), 2, 120),
            new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50),
            new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200),
            new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
            new Product("powerbank", 6578405864L, Country.JAPAN, new BigDecimal("95"), 4, 80),
            new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564)
    );
    List<Product> expectedResultAfterSortByName = Arrays.asList(
            new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50),
            new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
            new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
            new Product("powerbank", 6578405864L, Country.JAPAN, new BigDecimal("95"), 4, 80),
            new Product("sneakers", 2343563342L, Country.MALAYSIA, new BigDecimal("200"), 2, 120),
            new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200)
    );
    List<Product> expectedResultAfterSortByPrice = Arrays.asList(
            new Product("powerbank", 6578405864L, Country.JAPAN, new BigDecimal("95"), 4, 80),
            new Product("sneakers", 2343563342L, Country.MALAYSIA, new BigDecimal("200"), 2, 120),
            new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
            new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200),
            new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
            new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50)
    );
    List<Product> expectedResultAfterSortByExpirationYear = Arrays.asList(
            new Product("sneakers", 2343563342L, Country.MALAYSIA, new BigDecimal("200"), 2, 120),
            new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200),
            new Product("powerbank", 6578405864L, Country.JAPAN, new BigDecimal("95"), 4, 80),
            new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
            new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
            new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50)
    );

    @BeforeClass
    public static void initialize() throws FileCreatorException {
        warehouse = Warehouse.getInstance();
        creator = new WarehouseFromFileCreator();
        products = creator.fillWarehouseFromFile(FILE_PATH);
        for (Product product : products) {
            warehouse.setProduct(product);
        }
        sortService = ProductSortServiceImpl.getInstance();
    }

    @Test
    public void testSortByUPC() throws ServiceException {
        List<Product> actualResult = sortService.sortProductsByUPC(products);
        assertEquals(actualResult, expectedResultAfterSortByUPC);
    }

    @Test
    public void testSortByName() throws ServiceException {
        List<Product> actualResult = sortService.sortProductsByName(products);
        assertEquals(actualResult, expectedResultAfterSortByName);
    }

    @Test
    public void testSortByPrice() throws ServiceException {
        List<Product> actualResult = sortService.sortProductsByPrice(products);
        assertEquals(actualResult, expectedResultAfterSortByPrice);
    }

    @Test
    public void testSortByExpirationYear() throws ServiceException {
        List<Product> actualResult = sortService.sortProductsByExpirationYear(products);
        assertEquals(actualResult, expectedResultAfterSortByExpirationYear);
    }

    @AfterClass
    public static void clear() {
        warehouse = null;
        creator = null;
        products = null;
        sortService = null;
    }
}