package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.AttributesEnum;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty(AttributesEnum.PAGE_LOGIN.getValue());
        request.getSession().removeAttribute(AttributesEnum.USER.getValue());
        request.getSession().invalidate();
        return page;
    }
}