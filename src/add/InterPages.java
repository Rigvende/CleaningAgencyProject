package add;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class InterPages {

    private InterPages() { }

    public static String getProperty(String key) {
        String country = "";
        String language = "";
        int request = new Random().nextInt(3);
        switch (request) {
            case 1:
                country = "US";
                language = "EN";
                break;
            case 2:
                country = "BY";
                language = "BE";
                break;
            default:
                country = "RU";
                language = "RU";
        }
        Locale current = new Locale(language, country);
        ResourceBundle bundle = ResourceBundle.getBundle("resources/message", current);
        return bundle.getString(key);
    }
}