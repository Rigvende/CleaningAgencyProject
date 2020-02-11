package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class BackToMainCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute("role");
        String page;
        if(role != null) {
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
                    page = ConfigurationManager.getProperty("page.login");
                    break;
            }
        } else {
            page = ConfigurationManager.getProperty("page.login");
        }
        return page;
    }
}