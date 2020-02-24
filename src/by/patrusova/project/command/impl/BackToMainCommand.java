package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.stringholder.Page;
import javax.servlet.http.HttpServletRequest;

public class BackToMainCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute(Attribute.ROLE.getValue());
        String page;
        if(role != null) {
            switch (role) {
                case "admin":
                    page = ConfigurationManager.getProperty(Page.PAGE_MAIN_ADMIN.getValue());
                    break;
                case "client":
                    page = ConfigurationManager.getProperty(Page.PAGE_MAIN_CLIENT.getValue());
                    break;
                case "cleaner":
                    page = ConfigurationManager.getProperty(Page.PAGE_MAIN_CLEANER.getValue());
                    break;
                case "guest":
                default:
                    page = ConfigurationManager.getProperty(Page.PAGE_LOGIN.getValue());
                    break;
            }
        } else {
            page = ConfigurationManager.getProperty(Page.PAGE_LOGIN.getValue());
        }
        return page;
    }
}