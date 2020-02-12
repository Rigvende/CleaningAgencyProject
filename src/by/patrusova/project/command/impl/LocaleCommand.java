package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = request.getRequestURL().toString();
        HttpSession session = request.getSession();
        String lang = request.getParameter("language");
        Locale locale;
        switch (lang) {
            case "АНГЛИЙСКИЙ":
                locale = new Locale("en", "EN");
                break;
            case "RUSSIAN":
                locale = new Locale("ru", "RU");
                break;
            default:
                locale = Locale.getDefault();
                break;
        }
        session.setAttribute("locale", locale);
        ResourceBundle bundle =
                ResourceBundle.getBundle("resources.message", locale);
        Enumeration<String> e = bundle.getKeys();
        while (e.hasMoreElements()) {
            String key = e.nextElement();
            String s = bundle.getString(key);
            session.setAttribute(key, s);
        }
        return page;
//        return ConfigurationManager.getProperty("page.index");
    }
}