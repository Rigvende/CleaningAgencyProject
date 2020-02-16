package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
//        String referer = request.getHeader( "referer" );//todo как передать эту же страницу?
//        String page = request.getRequestURL().toString();

        Locale locale = request.getLocale();
        switch (locale.toString()) {
            case "ru_RU":
                locale = new Locale("en", "EN");
                Locale.setDefault(locale);
                break;
            case "en_EN":
                locale = new Locale("ru", "RU");
                Locale.setDefault(locale);
                break;
            default:
                locale = Locale.getDefault();
                break;
        }
        request.getSession().setAttribute("locale", locale);
//        ResourceBundle bundle =
//                ResourceBundle.getBundle("resources.message", locale);
//        Enumeration<String> e = bundle.getKeys();
//        while (e.hasMoreElements()) {
//            String key = e.nextElement();
//            String s = bundle.getString(key);
//            session.setAttribute(key, s);
//        }
//        return referer.substring(3);
        return ConfigurationManager.getProperty(Pages.PAGE_LOGIN.getValue());
    }
}