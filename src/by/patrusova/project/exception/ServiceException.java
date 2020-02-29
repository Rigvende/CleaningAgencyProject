package by.patrusova.project.exception;

/**
 * Class for handling exceptions on service level.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ServiceException extends Exception {

    public ServiceException() {}
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceException(Throwable cause) {
        super(cause);
    }
}