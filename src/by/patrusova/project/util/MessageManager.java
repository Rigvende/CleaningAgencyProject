package by.patrusova.project.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class for accessing to message resources.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class MessageManager {

    private final static String BUNDLE = "resources.message";

    private MessageManager() { }

    public static String getProperty(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE, Locale.getDefault());
        return resourceBundle.getString(key);
    }
}