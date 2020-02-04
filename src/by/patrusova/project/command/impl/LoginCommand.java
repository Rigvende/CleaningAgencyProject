package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.service.LoginService;
import by.patrusova.project.util.MessageManager;
import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        User user = new User();
        user.setLogin(login);
        user.setPassword(pass);
        try {
            if (LoginService.checkLogin(user)) {
                request.setAttribute("user", user);
                page = ConfigurationManager.getProperty("page.main");
            } else  {
                request.setAttribute("errorLoginPassMessage",
                        MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("page.loginfalse");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
//todo убрать в константы строки