package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import javax.servlet.http.HttpServletRequest;

public class BackToMainCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute("role");
        String page;
        switch (role) {
            case "admin":
                page = ConfigurationManager.getProperty("page.mainadmin");
                break;
            case "client":
                page = ConfigurationManager.getProperty("page.mainclient");
                break;
            case "cleaner":
                page = ConfigurationManager.getProperty("page.maincleaner");
                break;
            default:
                request.setAttribute("errorLoginPassMessage",
                        MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("page.login");
                break;
        }
        return page;
    }
}