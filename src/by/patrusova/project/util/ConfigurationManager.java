package by.patrusova.project.util;

import java.util.ResourceBundle;

public class ConfigurationManager {

    private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("resources.message");

    private ConfigurationManager() { }

    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}