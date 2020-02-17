package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.stringholder.Attributes;
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
        if (request.getSession().getAttribute(Attributes.LOCALE.getValue()) != null) {
            language = (String) request.getSession().getAttribute(Attributes.LOCALE.getValue());
            LOGGER.log(Level.INFO, language);
            switch (language) {
                case "ru_RU":
                    locale = new Locale(Attributes.EN2.getValue(), Attributes.EN1.getValue());
                    Locale.setDefault(locale);
                    language = Attributes.EN_EN.getValue();
                    break;
                case "en_EN":
                    locale = new Locale(Attributes.RU2.getValue(), Attributes.RU1.getValue());
                    Locale.setDefault(locale);
                    language = Attributes.RU_RU.getValue();
                    break;
            }
        } else {
            locale = Locale.getDefault();
            language = locale.toString();
        }
        request.getSession().setAttribute(Attributes.LOCALE.getValue(), language);
        return ConfigurationManager.getProperty(Pages.PAGE_LOGIN.getValue());
    }
}