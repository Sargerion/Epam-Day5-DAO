package edu.epam.task.service;

import edu.epam.task.entity.Entity;
import edu.epam.task.exception.ServiceException;

import java.util.List;

public interface ProductSortService<T extends Entity> {

    List<T> sortProductsByUPC(List<T> products) throws ServiceException;

    List<T> sortProductsByName(List<T> products) throws ServiceException;

    List<T> sortProductsByPrice(List<T> products) throws ServiceException;

    List<T> sortProductsByExpirationYear(List<T> products) throws ServiceException;
}
