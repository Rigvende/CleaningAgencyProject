package by.patrusova.project.validator;

public class IdValidator {

    private IdValidator() {}

    public static boolean isValidId(long id) {
        return (id >= 0 && id < Long.MAX_VALUE);
    }
}