package edu.epam.task.dao;

import edu.epam.task.entity.Entity;
import edu.epam.task.exception.DaoException;

import java.util.List;

public interface BaseDao<K, T extends Entity> {

    boolean createEntity(T t) throws DaoException;

    T findEntityById(K id) throws DaoException;

    List<T> findAll() throws DaoException;

    T updateEntity(T t, int index) throws DaoException;

    T updateEntityById(T t, K id) throws DaoException;

    boolean deleteEntity(T t) throws DaoException;

    boolean deleteEntityById(K id) throws DaoException;
}
