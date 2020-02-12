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
        String referer = request.getHeader( "referer" );
////        String page = request.getRequestURL().toString();
//        HttpSession session = request.getSession();
//        String language = request.getParameter("language");
//        Locale locale;
//        switch (language) {
//            case "ENGLISH":
//                locale = new Locale("en", "EN");
//                Locale.setDefault(locale);
//                break;
//            case "РУССКИЙ":
//                locale = new Locale("ru", "RU");
//                Locale.setDefault(locale);
//                break;
//            default:
//                locale = Locale.getDefault();
//                break;
//        }
//        session.setAttribute("locale", Locale.getDefault());
//        ResourceBundle bundle =
//                ResourceBundle.getBundle("resources.message", locale);
//        Enumeration<String> e = bundle.getKeys();
//        while (e.hasMoreElements()) {
//            String key = e.nextElement();
//            String s = bundle.getString(key);
//            session.setAttribute(key, s);
//        }
        return referer;
    }
}