package by.patrusova.project.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationDataValidator {

    private final static String CHECK_LOGIN
            = "^[a-zA-Z][a-zA-Z0-9-_]{4,15}$";
    private final static String CHECK_PASSWORD
            = "(?=^.{5,15}$)((?=.*\\d)|(?=.*[_]))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$";
    private final static String CHECK_EMAIL
            = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

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

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(CHECK_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }
}