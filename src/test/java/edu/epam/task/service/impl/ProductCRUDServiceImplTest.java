package edu.epam.task.service.impl;

import edu.epam.task.constant.Country;
import edu.epam.task.creator.WarehouseFromFileCreator;
import edu.epam.task.entity.Product;
import edu.epam.task.exception.FileCreatorException;
import edu.epam.task.exception.ServiceException;
import edu.epam.task.service.ProductCRUDService;
import edu.epam.task.storage.Warehouse;

import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class ProductCRUDServiceImplTest {

    public static final String FILE_PATH = "input_files/warehouse_input.txt";
    static Warehouse warehouse;
    static WarehouseFromFileCreator creator;
    static List<Product> products;
    static ProductCRUDService<Long, Product> productService;

    @BeforeClass
    public static void initialize() throws FileCreatorException {
        warehouse = Warehouse.getInstance();
        creator = new WarehouseFromFileCreator();
        products = creator.fillWarehouseFromFile(FILE_PATH);
        for (Product product : products) {
            warehouse.setProduct(product);
        }
        productService = ProductCRUDServiceImpl.getInstance();
    }

    @Test
    public void testAddToWarehouse() throws ServiceException {
        productService.addProduct(new Product("mouse", 542367244L, Country.SWEDEN, new BigDecimal("353.4"), 1, 56));
        List<Product> actualResult = warehouse.getProducts();
        List<Product> expectedResult = Arrays.asList(
                new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
                new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200),
                new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
                new Product("sneakers", 2343563342L, Country.MALAYSIA, new BigDecimal("200"), 2, 120),
                new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50),
                new Product("powerbank", 6578405864L, Country.JAPAN, new BigDecimal("95"), 4, 80),
                new Product("mouse", 542367244L, Country.SWEDEN, new BigDecimal("353.4"), 1, 56)
        );
        assertEquals(actualResult, expectedResult);
        productService.deleteProduct(new Product("mouse", 542367244L, Country.SWEDEN, new BigDecimal("353.4"), 1, 56));
    }

    @Test
    public void testFindProductByName() throws ServiceException {
        String nameForFind = "suit";
        Product actualResult = productService.findProductByName(nameForFind);
        Product expectedResult = new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testFindProductByUPC() throws ServiceException {
        long UPCForFind = 6568565864L;
        Product actualResult = productService.findProductByUPC(UPCForFind);
        Product expectedResult = new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349);
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testFindAllProducts() throws ServiceException {
        List<Product> actualResult = productService.findAllProducts();
        List<Product> expectedResult = products;
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testFindProductsByExpirationYear() throws ServiceException {
        List<Product> actualResult = productService.findProductsByExpirationYear(4);
        List<Product> expectedResult = Arrays.asList(new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
                new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
                new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50));
        assertEquals(actualResult, expectedResult);
    }

    @Test
    public void testUpdateProduct() throws ServiceException {
        Product productForUpdate = new Product("car", 2153563243L, Country.POLAND, new BigDecimal("15630.49"), 12, 2);
        productService.updateProduct(productForUpdate, 4);
        List<Product> actualResult = warehouse.getProducts();
        List<Product> expectedResult = Arrays.asList(
                new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
                new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200),
                new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
                new Product("sneakers", 2343563342L, Country.MALAYSIA, new BigDecimal("200"), 2, 120),
                new Product("car", 2153563243L, Country.POLAND, new BigDecimal("15630.49"), 12, 2),
                new Product("powerbank", 6578405864L, Country.JAPAN, new BigDecimal("95"), 4, 80)
        );
        assertEquals(actualResult, expectedResult);
        Product returnState = new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50);
        productService.updateProduct(returnState, 4);
    }

    @Test
    public void testUpdateProductByName() throws ServiceException {
        Product productForUpdate = new Product("car", 2153563243L, Country.POLAND, new BigDecimal("15630.49"), 12, 2);
        productService.updateProductByName("car", productForUpdate);
        List<Product> actualResult = warehouse.getProducts();
        List<Product> expectedResult = Arrays.asList(
                new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
                new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200),
                new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
                new Product("sneakers", 2343563342L, Country.MALAYSIA, new BigDecimal("200"), 2, 120),
                new Product("car", 2153563243L, Country.POLAND, new BigDecimal("15630.49"), 12, 2),
                new Product("powerbank", 6578405864L, Country.JAPAN, new BigDecimal("95"), 4, 80)
        );
        assertEquals(actualResult, expectedResult);
        Product returnState = new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50);
        productService.updateProduct(returnState, 4);
    }

    @Test
    public void testUpdateProductByUPC() throws ServiceException {
        Product productForUpdate = new Product("car", 2153563243L, Country.POLAND, new BigDecimal("15630.49"), 12, 2);
        productService.updateProductByUPC(5153563243L, productForUpdate);
        List<Product> actualResult = warehouse.getProducts();
        List<Product> expectedResult = Arrays.asList(
                new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
                new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200),
                new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
                new Product("sneakers", 2343563342L, Country.MALAYSIA, new BigDecimal("200"), 2, 120),
                new Product("car", 2153563243L, Country.POLAND, new BigDecimal("15630.49"), 12, 2),
                new Product("powerbank", 6578405864L, Country.JAPAN, new BigDecimal("95"), 4, 80)
        );
        assertEquals(actualResult, expectedResult);
        Product returnState = new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50);
        productService.updateProduct(returnState, 4);
    }

    @Test
    public void testDeleteProductByName() throws ServiceException {
        Product saveProduct = productService.findProductByName("powerbank");
        productService.deleteProductByName("powerbank");
        List<Product> actualResult = warehouse.getProducts();
        List<Product> expectedResult = Arrays.asList(
                new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564),
                new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200),
                new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
                new Product("sneakers", 2343563342L, Country.MALAYSIA, new BigDecimal("200"), 2, 120),
                new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50)
        );
        assertEquals(actualResult, expectedResult);
        productService.addProduct(saveProduct);
    }

    @AfterClass
    public static void clear() {
        warehouse = null;
        creator = null;
        products = null;
        productService = null;
    }
}
