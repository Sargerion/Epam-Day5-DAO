package edu.epam.task.dao.impl;

import edu.epam.task.dao.ProductDao;
import edu.epam.task.entity.Product;
import edu.epam.task.exception.ProductDaoException;
import edu.epam.task.storage.Warehouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductDaoImpl implements ProductDao<Product> {

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
    public void add(Product item) throws ProductDaoException {
        if (warehouse.contains(item)) {
            throw new ProductDaoException("Product is already in the warehouse");
        }
        warehouse.setProduct(item);
        logger.info("Product - {} was added to warehouse", item);
    }

    @Override
    public void deleteByName(String name) throws ProductDaoException {
        if (warehouse.isEmpty()) {
            throw new ProductDaoException("Warehouse is empty");
        }
        Product product = null;
        for (int i = 0; i < warehouse.getSize(); i++) {
            if (warehouse.getProduct(i).getProductName().equals(name.toLowerCase())) {
                product = warehouse.getProduct(i);
                warehouse.deleteProduct(product);
            }
        }
        if (product == null) {
            throw new ProductDaoException("There are no such product");
        }
        warehouse.deleteProduct(product);
        logger.info("Product - {} was deleted from warehouse", product);
    }

    @Override
    public void updateByName(String name, Product item) throws ProductDaoException {
        if (warehouse.isEmpty()) {
            throw new ProductDaoException("Warehouse is empty");
        }
        List<Product> productsToDelete = new ArrayList<>();
        Product product = null;
        for (int i = 0; i < warehouse.getSize(); i++) {
            if (warehouse.getProduct(i).getProductName().equals(name.toLowerCase())) {
                productsToDelete.add(warehouse.getProduct(i));
            }
        }
        for (int i = 0; i < warehouse.getSize(); i++) {
            product = warehouse.getProduct(i);
            for (int j = 0; j < productsToDelete.size(); j++) {
                if (warehouse.getProduct(i).equals(productsToDelete.get(j))) {
                    warehouse.setProduct(item, i);
                    warehouse.deleteProduct(product);
                }
            }
        }
        if (productsToDelete.isEmpty()) {
            throw new ProductDaoException("There are no such product");
        }
        logger.info("Products - {} was updated by name", productsToDelete);
    }

    @Override
    public List<Product> findAll() throws ProductDaoException {
        if (warehouse.isEmpty()) {
            throw new ProductDaoException("Warehouse is empty");
        }
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < warehouse.getSize(); i++) {
            result.add(warehouse.getProduct(i));
        }
        logger.info("Product were loaded from warehouse - {}", result);
        return result;
    }

    @Override
    public List<Product> findProductsByName(String name) throws ProductDaoException {
        if (warehouse.isEmpty()) {
            throw new ProductDaoException("Warehouse is empty");
        }
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < warehouse.getSize(); i++) {
            Product product = warehouse.getProduct(i);
            if (product.getProductName().equals(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public List<Product> findProductsByName(String name, BigDecimal price) throws ProductDaoException {
        if (warehouse.isEmpty()) {
            throw new ProductDaoException("Warehouse is empty");
        }
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < warehouse.getSize(); i++) {
            Product product = warehouse.getProduct(i);
            if (product.getProductName().equals(name.toLowerCase()) && product.getPrice().compareTo(price) <= 0) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public List<Product> findProductsByExpirationYear(int expirationYear) throws ProductDaoException {
        if (warehouse.isEmpty()) {
            throw new ProductDaoException("Warehouse is empty");
        }
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < warehouse.getSize(); i++) {
            Product product = warehouse.getProduct(i);
            if (product.getExpirationYear() > expirationYear) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public List<Product> sortByPrice() throws ProductDaoException {
        if (warehouse.isEmpty()) {
            throw new ProductDaoException("Warehouse is empty");
        }
        List<Product> result = warehouse.getProducts();
        result.sort(Comparator.comparing(Product::getPrice));
        return result;
    }
}
