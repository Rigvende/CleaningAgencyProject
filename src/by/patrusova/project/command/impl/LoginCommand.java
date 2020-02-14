package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.CleanerService;
import by.patrusova.project.service.impl.ClientService;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.service.impl.LoginService;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class LoginCommand implements ActionCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        if (login.equals("") || pass.equals("")) {
            request.getSession().setAttribute(Attributes.ERROR_LOGIN.getValue(),
                    MessageManager.getProperty(Attributes.MESSAGE_ERROR_LOGIN.getValue()));
            page = ConfigurationManager.getProperty(Attributes.PAGE_LOGIN.getValue());
            return page;
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(pass);
        try {
            user = LoginService.checkLogin(user);
            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute(Attributes.USER.getValue(), user);
                String role = user.getRole();
                switch (role) {
                    case "admin":
                        session.setAttribute(Attributes.ROLE.getValue(), Attributes.ADMIN.getValue());
                        page = ConfigurationManager.getProperty(Attributes.PAGE_MAIN_ADMIN.getValue());
                        break;
                    case "cleaner":
                        session.setAttribute(Attributes.ROLE.getValue(), Attributes.CLEANER.getValue());
                        Cleaner cleaner = new Cleaner();
                        cleaner.setIdUser(user.getId());
                        cleaner = CleanerService.getCleaner(cleaner);
                        session.setAttribute(Attributes.CLEANER.getValue(), cleaner);
                        page = ConfigurationManager.getProperty(Attributes.PAGE_MAIN_CLEANER.getValue());
                        break;
                    case "client":
                        session.setAttribute(Attributes.ROLE.getValue(), Attributes.CLIENT.getValue());
                        Client client = new Client();
                        client.setIdUser(user.getId());
                        client = ClientService.getClient(client);
                        session.setAttribute(Attributes.CLIENT.getValue(), client);
                        page = ConfigurationManager.getProperty(Attributes.PAGE_MAIN_CLIENT.getValue());
                        break;
                    case "guest":
                    default:
                        session.setAttribute(Attributes.ROLE.getValue(), Attributes.GUEST.getValue());
                        session.setAttribute(Attributes.ERROR_LOGIN.getValue(),
                                MessageManager.getProperty(Attributes.MESSAGE_NOT_REG.getValue()));
                        page = ConfigurationManager.getProperty(Attributes.PAGE_LOGIN.getValue());
                        break;
                }
            } else {
                request.getSession().setAttribute(Attributes.ERROR_LOGIN.getValue(),
                        MessageManager.getProperty(Attributes.MESSAGE_ERROR_LOGIN.getValue()));
                page = ConfigurationManager.getProperty(Attributes.PAGE_LOGIN.getValue());
            }
        } catch (ServiceException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while logged in.");
            throw new CommandException(e);
        }
        return page;
    }
}