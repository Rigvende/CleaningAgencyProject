package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.stringholder.Pages;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(Pages.PAGE_INDEX.getValue());
        Enumeration<String> enumeration = request.getSession().getAttributeNames();
        Iterator<String> iterator = enumeration.asIterator();
        while (iterator.hasNext()) {
            request.getSession().removeAttribute(iterator.next());
        }
        request.getSession().invalidate();
        return page;
    }
}