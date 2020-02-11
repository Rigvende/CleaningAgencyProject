package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.AttributesEnum;
import by.patrusova.project.entity.Role;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class BackToMainCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute(AttributesEnum.ROLE.getValue());
        String page;
        if(role != null) {
            switch (role) {
                case "admin":
                    page = ConfigurationManager.getProperty(AttributesEnum.PAGE_MAIN_ADMIN.getValue());
                    break;
                case "client":
                    page = ConfigurationManager.getProperty(AttributesEnum.PAGE_MAIN_CLIENT.getValue());
                    break;
                case "cleaner":
                    page = ConfigurationManager.getProperty(AttributesEnum.PAGE_MAIN_CLEANER.getValue());
                    break;
                case "guest":
                default:
                    page = ConfigurationManager.getProperty(AttributesEnum.PAGE_LOGIN.getValue());
                    break;
            }
        } else {
            page = ConfigurationManager.getProperty(AttributesEnum.PAGE_LOGIN.getValue());
        }
        return page;
    }
}