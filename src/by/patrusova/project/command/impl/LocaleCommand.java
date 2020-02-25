package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class LocaleCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String LOCALE = "locale";
    private final static String EN1 = "EN";
    private final static String EN2 = "en";
    private final static String EN_EN = "en_EN";
    private final static String RU2 = "ru";
    private final static String RU1 = "RU";
    private final static String RU_RU = "ru_RU";
    private final static String PAGE_LOGIN = "page.login";

    @Override
    public String execute(HttpServletRequest request) {
        String language;
        Locale locale;
        if (request.getSession().getAttribute(LOCALE) != null) {
            language = (String) request.getSession().getAttribute(LOCALE);
            LOGGER.log(Level.INFO, language);
            switch (language) {
                case "ru_RU":
                    locale = new Locale(EN2, EN1);
                    Locale.setDefault(locale);
                    language = EN_EN;
                    break;
                case "en_EN":
                    locale = new Locale(RU2, RU1);
                    Locale.setDefault(locale);
                    language = RU_RU;
                    break;
            }
        } else {
            locale = Locale.getDefault();
            language = locale.toString();
        }
        request.getSession().setAttribute(LOCALE, language);
        return ConfigurationManager.getProperty(PAGE_LOGIN);
    }
}