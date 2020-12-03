package edu.epam.task.service;

import edu.epam.task.exception.ProductServiceException;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService<T> {
    void add(T item) throws ProductServiceException;
    void deleteByName(String name) throws ProductServiceException;
    void updateByName(String name, T item) throws ProductServiceException;
    List<T> findAll() throws ProductServiceException;
    List<T> findProductsByName(String productName) throws ProductServiceException;
    List<T> findProductsByName(String productName, BigDecimal productPrice) throws ProductServiceException;
    List<T> findProductsByExpirationYear(int expirationYear) throws ProductServiceException;
    List<T> sortByPrice() throws ProductServiceException;
}
