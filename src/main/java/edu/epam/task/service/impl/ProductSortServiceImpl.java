package edu.epam.task.service.impl;

import edu.epam.task.entity.Product;
import edu.epam.task.exception.ServiceException;
import edu.epam.task.service.ProductSortService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;

public class ProductSortServiceImpl implements ProductSortService<Product> {

    private static final Logger logger = LogManager.getLogger();
    private static ProductSortServiceImpl sortService;

    private ProductSortServiceImpl() {

    }

    public static ProductSortServiceImpl getInstance() {
        if (sortService == null) {
            sortService = new ProductSortServiceImpl();
            logger.info("Sort service created");
        }
        return sortService;
    }

    @Override
    public List<Product> sortProductsByUPC(List<Product> products) throws ServiceException {
        products.sort(Comparator.comparing(Product::getUPC));
        logger.info("Sort by UPC completed, result -> {}", products);
        return products;
    }

    @Override
    public List<Product> sortProductsByName(List<Product> products) throws ServiceException {
        products.sort(Comparator.comparing(Product::getProductName));
        logger.info("Sort by name completed, result -> {}", products);
        return products;
    }

    @Override
    public List<Product> sortProductsByPrice(List<Product> products) throws ServiceException {
        products.sort(Comparator.comparing(Product::getPrice));
        logger.info("Sort by price completed, result -> {}", products);
        return products;
    }

    @Override
    public List<Product> sortProductsByExpirationYear(List<Product> products) throws ServiceException {
        products.sort(Comparator.comparing(Product::getExpirationYear));
        logger.info("Sort by expiration year completed, result -> {}", products);
        return products;
    }
}