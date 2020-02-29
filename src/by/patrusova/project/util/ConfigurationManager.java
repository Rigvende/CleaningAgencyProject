package by.patrusova.project.util;

import java.util.ResourceBundle;

/**
 * Class for accessing to pages' configuration.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ConfigurationManager {

    private static ResourceBundle RESOURCE_BUNDLE
            = ResourceBundle.getBundle("resources.configurationPages");

    private ConfigurationManager() { }

    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}