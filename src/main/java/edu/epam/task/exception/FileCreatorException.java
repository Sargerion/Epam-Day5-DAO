package edu.epam.task.exception;

public class FileCreatorException extends Exception{

    public FileCreatorException() {

    }

    public FileCreatorException(String message) {
        super(message);
    }

    public FileCreatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileCreatorException(Throwable cause) {
        super(cause);
    }
}
