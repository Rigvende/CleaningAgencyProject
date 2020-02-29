package by.patrusova.project.exception;

/**
 * Class for handling exceptions on DAO level.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class DaoException extends Exception {

    public DaoException() {}
    public DaoException(String message) {
        super(message);
    }
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
    public DaoException(Throwable cause) {
        super(cause);
    }
}