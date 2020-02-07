package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("page.login");
        request.getSession().invalidate();
        request.setAttribute("logoutMessage",
                MessageManager.getProperty("message.logout"));//fixme почему не выводится сообщение?
        return page;
    }
}