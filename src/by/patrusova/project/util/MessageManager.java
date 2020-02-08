package by.patrusova.project.util;

import java.util.ResourceBundle;

public class MessageManager {

    private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("resources.message_en_US");//todo

    private MessageManager() { }

    public static String getProperty(String key) {
        return RESOURCE_BUNDLE.getString(key);
    }
}