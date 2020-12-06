package edu.epam.task.dao;

import edu.epam.task.entity.Product;
import edu.epam.task.exception.DaoException;

import java.util.List;

public interface ProductDao extends BaseDao<Long, Product> {

    Product findProductByName(String productName) throws DaoException;

    Product findProductByUPC(long UPC) throws DaoException;

    List<Product> findProductsByExpirationYear(int expirationYear) throws DaoException;

    Product updateProductByName(String productName, Product product) throws DaoException;

    Product updateProductByUPC(long UPC, Product product) throws DaoException;

    boolean deleteProductByName(String productName) throws DaoException;

    boolean deleteProductByUPC(long UPC) throws DaoException;
}