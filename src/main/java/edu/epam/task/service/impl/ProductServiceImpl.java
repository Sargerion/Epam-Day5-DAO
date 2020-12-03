package edu.epam.task.service.impl;

import edu.epam.task.dao.ProductDao;
import edu.epam.task.dao.impl.ProductDaoImpl;
import edu.epam.task.entity.Product;
import edu.epam.task.exception.ProductDaoException;
import edu.epam.task.exception.ProductServiceException;
import edu.epam.task.service.ProductService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService<Product> {

    private static final Logger logger = LogManager.getLogger();
    private static ProductServiceImpl service;
    private final ProductDao<Product> productDao = ProductDaoImpl.getInstance();

    private ProductServiceImpl() {

    }

    public static ProductServiceImpl getInstance() {
        if (service == null) {
            service = new ProductServiceImpl();
            logger.info("Product service created");
        }
        return service;
    }

    @Override
    public void add(Product item) throws ProductServiceException {
        try {
            productDao.add(item);
        } catch (ProductDaoException e) {
            throw new ProductServiceException("No connect with warehouse");
        }
    }

    @Override
    public void deleteByName(String name) throws ProductServiceException {
        try {
            productDao.deleteByName(name);
        } catch (ProductDaoException e) {
            throw new ProductServiceException("No connect with warehouse");
        }
    }

    @Override
    public void updateByName(String name, Product item) throws ProductServiceException {
        try {
            productDao.updateByName(name, item);
        } catch (ProductDaoException e) {
            throw new ProductServiceException("No connect with warehouse");
        }
    }

    @Override
    public List<Product> findAll() throws ProductServiceException {
        List<Product> result = new ArrayList<>();
        try {
            result = productDao.findAll();
        } catch (ProductDaoException e) {
            throw new ProductServiceException("No connect with warehouse");
        }
        logger.info("All products loaded from warehouse, result -> {}", result);
        return result;
    }

    @Override
    public List<Product> findProductsByName(String productName) throws ProductServiceException {
        List<Product> result = new ArrayList<>();
        try {
            result = productDao.findProductsByName(productName);
        } catch (ProductDaoException e) {
            throw new ProductServiceException("No connect with warehouse");
        }
        logger.info("Products with this name - {} were found\nResult - {}", productName, result);
        return result;
    }

    @Override
    public List<Product> findProductsByName(String productName, BigDecimal productPrice) throws ProductServiceException {
        List<Product> result = new ArrayList<>();
        try {
            result = productDao.findProductsByName(productName, productPrice);
        } catch (ProductDaoException e) {
            throw new ProductServiceException("No connect with warehouse");
        }
        logger.info("Products with this name - {} and price <= {} were found\nResult - {}", productName, productPrice, result);
        return result;
    }

    @Override
    public List<Product> findProductsByExpirationYear(int expirationYear) throws ProductServiceException {
        List<Product> result = new ArrayList<>();
        try {
            result = productDao.findProductsByExpirationYear(expirationYear);
        } catch (ProductDaoException e) {
            throw new ProductServiceException("No connect with warehouse");
        }
        logger.info("Products with expiration year > {} were found\nResult - {}", expirationYear, result);
        return result;
    }

    @Override
    public List<Product> sortByPrice() throws ProductServiceException {
        List<Product> result = new ArrayList<>();
        try {
            result = productDao.sortByPrice();
        } catch (ProductDaoException e) {
            throw new ProductServiceException("No connect with warehouse");
        }
        logger.info("Products was sorted by price\nResult - {}", result);
        return result;
    }
}
