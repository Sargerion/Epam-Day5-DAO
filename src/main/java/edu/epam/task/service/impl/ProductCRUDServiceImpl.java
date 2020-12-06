package edu.epam.task.service.impl;

import edu.epam.task.dao.ProductDao;
import edu.epam.task.dao.impl.ProductDaoImpl;
import edu.epam.task.entity.Product;
import edu.epam.task.exception.DaoException;
import edu.epam.task.exception.ServiceException;
import edu.epam.task.service.ProductCRUDService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProductCRUDServiceImpl implements ProductCRUDService<Long, Product> {

    private static final Logger logger = LogManager.getLogger();
    private static ProductCRUDServiceImpl service;
    private final ProductDao productDao = ProductDaoImpl.getInstance();

    private ProductCRUDServiceImpl() {

    }

    public static ProductCRUDServiceImpl getInstance() {
        if (service == null) {
            service = new ProductCRUDServiceImpl();
            logger.info("Product service created");
        }
        return service;
    }

    @Override
    public boolean addProduct(Product product) throws ServiceException {
        try {
            productDao.createEntity(product);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public Product findProductById(Long id) throws ServiceException {
        Product result = new Product();
        try {
            result = productDao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Product> findAllProducts() throws ServiceException {
        List<Product> result = new ArrayList<>();
        try {
            result = productDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Product findProductByName(String name) throws ServiceException {
        Product result = new Product();
        try {
            result = productDao.findProductByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Product findProductByUPC(long UPC) throws ServiceException {
        Product result = new Product();
        try {
            result = productDao.findProductByUPC(UPC);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Product> findProductsByExpirationYear(int expirationYear) throws ServiceException {
        List<Product> result = new ArrayList<>();
        try {
            result = productDao.findProductsByExpirationYear(expirationYear);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public Product updateProduct(Product product, int index) throws ServiceException {
        Product productForUpdate = new Product();
        try {
            productForUpdate = productDao.updateEntity(product, index);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return productForUpdate;
    }

    @Override
    public Product updateProductById(Product product, Long id) throws ServiceException {
        Product productForUpdate = new Product();
        try {
            productForUpdate = productDao.updateEntityById(product, id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return productForUpdate;
    }

    @Override
    public Product updateProductByName(String productName, Product product) throws ServiceException {
        Product productForUpdate = new Product();
        try {
            productForUpdate = productDao.updateProductByName(productName, product);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return productForUpdate;
    }

    @Override
    public Product updateProductByUPC(long UPC, Product product) throws ServiceException {
        Product productForUpdate = new Product();
        try {
            productForUpdate = productDao.updateProductByUPC(UPC, product);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return productForUpdate;
    }

    @Override
    public boolean deleteProduct(Product product) throws ServiceException {
        try {
            productDao.deleteEntity(product);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public boolean deleteProductById(Long id) throws ServiceException {
        try {
            productDao.deleteEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public boolean deleteProductByName(String productName) throws ServiceException {
        try {
            productDao.deleteProductByName(productName);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }

    @Override
    public boolean deleteProductByUPC(long UPC) throws ServiceException {
        try {
            productDao.deleteProductByUPC(UPC);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return true;
    }
}
