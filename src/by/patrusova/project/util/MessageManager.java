package by.patrusova.project.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {

    private MessageManager() { }

    public static String getProperty(String key) {
        ResourceBundle resourceBundle = ResourceBundle
                .getBundle("resources.message", Locale.getDefault());
        return resourceBundle.getString(key);
    }
}