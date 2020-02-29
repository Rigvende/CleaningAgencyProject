package by.patrusova.project.exception;

/**
 * Class for handling exceptions on command level.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class CommandException extends Exception {

    public CommandException() {}
    public CommandException(String message) {
        super(message);
    }
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
    public CommandException(Throwable cause) {
        super(cause);
    }
}