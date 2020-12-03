package edu.epam.task.exception;

public class ProductServiceException extends Exception{

    public ProductServiceException() {

    }

    public ProductServiceException(String message) {
        super(message);
    }

    public ProductServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductServiceException(Throwable cause) {
        super(cause);
    }
}
