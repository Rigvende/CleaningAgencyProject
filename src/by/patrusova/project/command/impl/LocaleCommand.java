package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Class for command to change locale
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class LocaleCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String LOCALE = "locale";
    private final static String EN_EN = "en_EN";
    private final static String RU_RU = "ru_RU";
    private final static String PAGE_LOGIN = "page.login";
    private Locale localeEn = new Locale("en", "EN");
    private Locale localeRu = new Locale("ru", "RU");

    @Override
    public String execute(HttpServletRequest request) {
        String language;
        if (request.getSession().getAttribute(LOCALE) != null) {
            language = (String) request.getSession().getAttribute(LOCALE);
            LOGGER.log(Level.INFO, language);
            switch (language) {
                case "ru_RU":
                    Locale.setDefault(localeEn);
                    language = EN_EN;
                    break;
                case "en_EN":
                    Locale.setDefault(localeRu);
                    language = RU_RU;
                    break;
            }
        } else {
            Locale locale = Locale.getDefault();
            language = locale.toString();
        }
        request.getSession().setAttribute(LOCALE, language);
        return ConfigurationManager.getProperty(PAGE_LOGIN);
    }
}