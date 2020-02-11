package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.CleanerService;
import by.patrusova.project.service.ClientService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.service.LoginService;
import by.patrusova.project.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

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
            user = LoginService.checkLogin(user);
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                String role = user.getRole();
                switch (role) {
                    case "admin":
                        session.setAttribute("role", "admin");
                        page = ConfigurationManager.getProperty("page.mainadmin");
                        break;
                    case "cleaner":
                        session.setAttribute("role", "cleaner");
                        Cleaner cleaner = new Cleaner();
                        cleaner.setIdUser(user.getId());
                        cleaner = CleanerService.getCleaner(cleaner);
                        request.setAttribute("cleaner", cleaner);
                        page = ConfigurationManager.getProperty("page.maincleaner");
                        break;
                    case "client":
                        session.setAttribute("role", "client");
                        Client client = new Client();
                        client.setIdUser(user.getId());
                        client = ClientService.getClient(client);
                        request.setAttribute("client", client);
                        page = ConfigurationManager.getProperty("page.mainclient");
                        break;
                    default:
                        session.setAttribute("role", "guest");
                        request.setAttribute("errorLoginPassMessage",
                                MessageManager.getProperty("message.notregistered"));
                        page = ConfigurationManager.getProperty("page.login");
                        break;
                }
            } else {
                request.setAttribute("errorLoginPassMessage",
                        MessageManager.getProperty("message.loginerror"));
                page = ConfigurationManager.getProperty("page.login");
            }
        } catch (ServiceException | SQLException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
//todo убрать в константы строки