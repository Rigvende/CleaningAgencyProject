package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute("logoutMessage",
                MessageManager.getProperty("message.logout"));
        String page = ConfigurationManager.getProperty("page.login");
        request.getSession().invalidate();
        return page;
    }
}