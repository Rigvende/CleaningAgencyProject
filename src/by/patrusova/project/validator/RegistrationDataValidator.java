package by.patrusova.project.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationDataValidator {

    private final static String CHECK_LOGIN
            = "^[A-z0-9_]{5,15}$";
    private final static String CHECK_PASSWORD
            = "^[A-z0-9_]{5,15}$";
    private final static String CHECK_EMAIL
            = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private final static String CHECK_PHONE
            = "^[\\d]{5,20}$";

    public static boolean isValidLogin(String login) {
        Pattern pattern = Pattern.compile(CHECK_LOGIN);
        Matcher matcher = pattern.matcher(login);
        return matcher.find();
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(CHECK_PASSWORD);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    public static boolean isPasswordRepeated(String password, String repeatedPassword) {
        return password.equals(repeatedPassword);
    }

    public static boolean isValidPhone(String phone) {
        Pattern pattern = Pattern.compile(CHECK_PHONE);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(CHECK_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}