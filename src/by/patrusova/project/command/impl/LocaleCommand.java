package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.stringholder.Pages;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class LocaleCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        String language;
        Locale locale;
        if (request.getSession().getAttribute("locale") != null) {
            language = (String) request.getSession().getAttribute("locale");
            LOGGER.log(Level.INFO, language);
            switch (language) {
                case "ru_RU":
                    locale = new Locale("en", "EN");
                    Locale.setDefault(locale);
                    language = "en_EN";
                    break;
                case "en_EN":
                    locale = new Locale("ru", "RU");
                    Locale.setDefault(locale);
                    language = "ru_RU";
                    break;
            }
        } else {
            locale = Locale.getDefault();
            language = locale.toString();
        }
        request.getSession().setAttribute("locale", language);
        return ConfigurationManager.getProperty(Pages.PAGE_LOGIN.getValue());
    }
}