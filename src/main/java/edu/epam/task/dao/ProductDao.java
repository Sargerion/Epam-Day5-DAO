package edu.epam.task.dao;

import edu.epam.task.exception.ProductDaoException;

import java.math.BigDecimal;
import java.util.List;

public interface ProductDao<T> {
    void add(T item) throws ProductDaoException;
    void deleteByName(String name) throws ProductDaoException;
    void updateByName(String name, T item) throws ProductDaoException;
    List<T> findAll() throws ProductDaoException;
    List<T> findProductsByName(String name) throws ProductDaoException;
    List<T> findProductsByName(String name, BigDecimal price) throws ProductDaoException;
    List<T> findProductsByExpirationYear(int expirationYear) throws ProductDaoException;
    List<T> sortByPrice() throws ProductDaoException;
}
