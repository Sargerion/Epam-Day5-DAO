package edu.epam.task.service;

import edu.epam.task.entity.Entity;
import edu.epam.task.entity.Product;
import edu.epam.task.exception.ServiceException;

import java.util.List;

public interface ProductCRUDService<K, T extends Entity> {

    boolean addProduct(T t) throws ServiceException;

    T findProductById(K id) throws ServiceException;

    T findProductByName(String name) throws ServiceException;

    T findProductByUPC(long UPC) throws ServiceException;

    List<T> findAllProducts() throws ServiceException;

    List<T> findProductsByExpirationYear(int expirationYear) throws ServiceException;

    T updateProduct(T t, int index) throws ServiceException;

    T updateProductById(T t, K id) throws ServiceException;

    T updateProductByName(String productName, Product product) throws ServiceException;

    T updateProductByUPC(long UPC, Product product) throws ServiceException;

    boolean deleteProduct(T t) throws ServiceException;

    boolean deleteProductById(K id) throws ServiceException;

    boolean deleteProductByName(String productName) throws ServiceException;

    boolean deleteProductByUPC(long UPC) throws ServiceException;
}
