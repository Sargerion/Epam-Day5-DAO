package edu.epam.task.dao.impl;

import edu.epam.task.dao.ProductDao;
import edu.epam.task.entity.Product;
import edu.epam.task.exception.DaoException;
import edu.epam.task.storage.Warehouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private static final Logger logger = LogManager.getLogger();
    private static ProductDaoImpl productDao;
    private final Warehouse warehouse = Warehouse.getInstance();

    private ProductDaoImpl() {

    }

    public static ProductDaoImpl getInstance() {
        if (productDao == null) {
            productDao = new ProductDaoImpl();
            logger.info("ProductDao created");
        }
        return productDao;
    }

    @Override
    public boolean createEntity(Product product) throws DaoException {
        if (warehouse.contains(product)) {
            throw new DaoException("Product is already in the warehouse");
        }
        warehouse.setProduct(product);
        logger.info("Product - {} was added to the warehouse", product);
        return true;
    }

    @Override
    public Product findEntityById(Long id) throws DaoException {
        if (warehouse.isEmpty()) {
            throw new DaoException("Warehouse is empty");
        }
        Product foundProduct = new Product();
        int index = 0;
        while (index < warehouse.getSize()) {
            if (warehouse.getProduct(index).getId() == id) {
                foundProduct = warehouse.getProduct(index);
                break;
            }
            index++;
        }
        logger.info("Product - {} with id = {} was found", foundProduct, id);
        return foundProduct;
    }

    @Override
    public List<Product> findAll() throws DaoException {
        if (warehouse.isEmpty()) {
            throw new DaoException("Warehouse is empty");
        }
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < warehouse.getSize(); i++) {
            result.add(warehouse.getProduct(i));
        }
        logger.info("Products were loaded from warehouse - {}", result);
        return result;
    }

    @Override
    public Product findProductByName(String productName) throws DaoException {
        if (warehouse.isEmpty()) {
            throw new DaoException("Warehouse is empty");
        }
        Product foundProduct = new Product();
        int index = 0;
        while (index < warehouse.getSize()) {
            if (warehouse.getProduct(index).getProductName().equals(productName.toLowerCase())) {
                foundProduct = warehouse.getProduct(index);
                break;
            }
            index++;
        }
        logger.info("Product - {} with name = {} was found", foundProduct, productName);
        return foundProduct;
    }

    @Override
    public Product findProductByUPC(long UPC) throws DaoException {
        if (warehouse.isEmpty()) {
            throw new DaoException("Warehouse is empty");
        }
        Product foundProduct = new Product();
        int index = 0;
        while (index < warehouse.getSize()) {
            if (warehouse.getProduct(index).getUPC() == UPC) {
                foundProduct = warehouse.getProduct(index);
                break;
            }
            index++;
        }
        logger.info("Product - {} with UPC = {} was found", foundProduct, UPC);
        return foundProduct;
    }

    @Override
    public List<Product> findProductsByExpirationYear(int expirationYear) throws DaoException {
        if (warehouse.isEmpty()) {
            throw new DaoException("Warehouse is empty");
        }
        List<Product> result = new ArrayList<>();
        Product product;
        for (int i = 0; i < warehouse.getSize(); i++) {
            product = warehouse.getProduct(i);
            if (product.getExpirationYear() > expirationYear) {
                result.add(product);
            }
        }
        logger.info("Products with expiration year > {} were found\nResult - {}", expirationYear, result);
        return result;
    }

    @Override
    public Product updateEntity(Product product, int index) throws DaoException {
        if (warehouse.contains(product)) {
            throw new DaoException("The warehouse alredy has that product -> " + product);
        }
        Product productForUpdate = warehouse.getProduct(index);
        warehouse.replaceProductByIndex(product, index);
        logger.info("Product {} was updated to {} product", productForUpdate, product);
        return productForUpdate;
    }

    @Override
    public Product updateEntityById(Product product, Long id) throws DaoException {
        if (warehouse.contains(product)) {
            throw new DaoException("The warehouse already has that product -> " + product);
        }
        Product productForUpdate = findEntityById(id);
        int indexForUpdate = warehouse.getProductIndex(productForUpdate);
        warehouse.replaceProductByIndex(product, indexForUpdate);
        logger.info("Product {} was updated to {} product by id = {}", productForUpdate, product, id);
        return productForUpdate;
    }

    @Override
    public Product updateProductByName(String productName, Product product) throws DaoException {
        if (warehouse.contains(product)) {
            throw new DaoException("The warehouse alredy has that product -> " + product);
        }
        Product productForUpdate = findProductByName(productName);
        int indexForUpdate = warehouse.getProductIndex(productForUpdate);
        warehouse.replaceProductByIndex(product, indexForUpdate);
        logger.info("Product {} was updated to {} product by productName = {}", productForUpdate, product, productName);
        return productForUpdate;
    }

    @Override
    public Product updateProductByUPC(long UPC, Product product) throws DaoException {
        if (warehouse.contains(product)) {
            throw new DaoException("The warehouse alredy has that product -> " + product);
        }
        Product productForUpdate = findProductByUPC(UPC);
        int indexForUpdate = warehouse.getProductIndex(productForUpdate);
        warehouse.replaceProductByIndex(product, indexForUpdate);
        logger.info("Product {} was updated to {} product by UPC = {}", productForUpdate, product, UPC);
        return productForUpdate;
    }

    @Override
    public boolean deleteEntity(Product product) throws DaoException {
        if (!warehouse.contains(product)) {
            throw new DaoException("In the warehouse is no such product -> " + product);
        }
        warehouse.deleteProduct(product);
        logger.info("Product {} was deleted", product);
        return true;
    }

    @Override
    public boolean deleteEntityById(Long id) throws DaoException {
        Product product = findEntityById(id);
        warehouse.deleteProduct(product);
        logger.info("Product {} was deleted by id = {}", product, id);
        return true;
    }

    @Override
    public boolean deleteProductByName(String productName) throws DaoException {
        Product product = findProductByName(productName);
        warehouse.deleteProduct(product);
        logger.info("Product {} was deleted by productName = {}", product, productName);
        return true;
    }

    @Override
    public boolean deleteProductByUPC(long UPC) throws DaoException {
        Product product = findProductByUPC(UPC);
        warehouse.deleteProduct(product);
        logger.info("Product {} was deleted by UPC = {}", product, UPC);
        return true;
    }
}
