package edu.epam.task.creator;

import edu.epam.task.constant.Country;
import edu.epam.task.entity.Product;
import edu.epam.task.exception.FileCreatorException;
import edu.epam.task.storage.Warehouse;

import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

public class WarehouseFromFileCreatorTest {

    public static final String FILE_PATH = "input_files/warehouse_input.txt";
    static Warehouse warehouse;
    static WarehouseFromFileCreator creator;

    List<Product> expectedResultAfterRead = Arrays.asList(
            new Product("sneakers", 2343563342L, Country.MALAYSIA, new BigDecimal("200"), 2, 120),
            new Product("car", 5153563243L, Country.BELARUS, new BigDecimal("20000.49"), 10, 50),
            new Product("suit", 5755659334L, Country.ITALY, new BigDecimal("1500"), 3, 200),
            new Product("laptop", 6568565864L, Country.USA, new BigDecimal("2499.99"), 6, 349),
            new Product("powerbank", 6578405864L, Country.JAPAN, new BigDecimal("95"), 4, 80),
            new Product("phone", 8934393244L, Country.CHINA, new BigDecimal("329.99"), 5, 564)
    );

    @BeforeClass
    public static void initialize() {
        warehouse = Warehouse.getInstance();
        creator = new WarehouseFromFileCreator();
    }

    @Test
    public void testFileRead() throws FileCreatorException {
        List<Product> actualResultAfterRead = creator.fillWarehouseFromFile(FILE_PATH);
        assertEquals(actualResultAfterRead, expectedResultAfterRead);
    }

    @AfterClass
    public static void clear() {
        warehouse = null;
        creator = null;
    }
}