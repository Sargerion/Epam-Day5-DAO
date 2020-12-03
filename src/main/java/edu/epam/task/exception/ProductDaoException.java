package edu.epam.task.exception;

public class ProductDaoException extends Exception{

    public ProductDaoException() {

    }

    public ProductDaoException(String message) {
        super(message);
    }

    public ProductDaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductDaoException(Throwable cause) {
        super(cause);
    }
}
