package by.patrusova.project.validator;

public class NumberValidator {

    private NumberValidator() {}

    public static boolean isValidDecimal(String column, double decimal) {
        switch (column.toLowerCase()) {
            case "commission":
            case "discount":
                return (decimal > 0 && decimal < 1);
            case "cost":
                return (decimal > 0 && decimal < 1000);
            default:
                return false;
        }
    }

    public static boolean isValidMark(int mark) {
        return (mark > 0 && mark < 10);
    }
}