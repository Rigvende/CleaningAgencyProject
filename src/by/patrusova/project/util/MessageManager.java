package by.patrusova.project.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {

    private final static String BUNDLE = "resources.message";

    private MessageManager() { }

    public static String getProperty(String key) {
        ResourceBundle resourceBundle = ResourceBundle
                .getBundle(BUNDLE, Locale.getDefault());
        return resourceBundle.getString(key);
    }
}