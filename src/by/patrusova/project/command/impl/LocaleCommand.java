package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Page;
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
        if (request.getSession().getAttribute(Attribute.LOCALE.getValue()) != null) {
            language = (String) request.getSession().getAttribute(Attribute.LOCALE.getValue());
            LOGGER.log(Level.INFO, language);
            switch (language) {
                case "ru_RU":
                    locale = new Locale(Attribute.EN2.getValue(), Attribute.EN1.getValue());
                    Locale.setDefault(locale);
                    language = Attribute.EN_EN.getValue();
                    break;
                case "en_EN":
                    locale = new Locale(Attribute.RU2.getValue(), Attribute.RU1.getValue());
                    Locale.setDefault(locale);
                    language = Attribute.RU_RU.getValue();
                    break;
            }
        } else {
            locale = Locale.getDefault();
            language = locale.toString();
        }
        request.getSession().setAttribute(Attribute.LOCALE.getValue(), language);
        return ConfigurationManager.getProperty(Page.PAGE_LOGIN.getValue());
    }
}