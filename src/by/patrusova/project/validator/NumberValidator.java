package by.patrusova.project.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberValidator {

    private final static String CHECK_DECIMAL
            = "^0.[\\d]{1,2}$";
    private final static String CHECK_ID
            = "^[\\d]{1,18}$";

    private NumberValidator() {}

    public static boolean isValidDecimal(String column, double decimal) {
        switch (column.toLowerCase()) {
            case "commission":
            case "discount":
                return (decimal >= 0 && decimal < 1);
            case "cost":
                return (decimal >= 0 && decimal < 1000);
            default:
                return false;
        }
    }

    public static boolean isValidDecimal(String decimal) {
        Pattern pattern = Pattern.compile(CHECK_DECIMAL);
        Matcher matcher = pattern.matcher(decimal);
        return matcher.find();
    }

    public static boolean isValidID(String id) {
        Pattern pattern = Pattern.compile(CHECK_ID);
        Matcher matcher = pattern.matcher(id);
        return matcher.find();
    }

    public static boolean isValidMark(int mark) {
        return (mark >= 0 && mark < 10);
    }
}