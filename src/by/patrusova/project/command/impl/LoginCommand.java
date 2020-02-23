package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.CleanerInfoService;
import by.patrusova.project.service.impl.ClientInfoService;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.service.impl.LoginService;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.util.stringholder.Parameters;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String login = request.getParameter(Parameters.LOGIN.getValue());
        String pass = request.getParameter(Parameters.PASSWORD.getValue());
        if (login.equals(Parameters.EMPTY.getValue()) || pass.equals(Parameters.EMPTY.getValue())) {
            request.getSession().setAttribute(Attributes.ERROR_LOGIN.getValue(),
                    MessageManager.getProperty(Messages.MESSAGE_ERROR_LOGIN.getValue()));
            page = ConfigurationManager.getProperty(Pages.PAGE_LOGIN.getValue());
            return page;
        }
        LoginService loginService = new LoginService();
        User user = new User();
        user.setLogin(login);
        user.setPassword(pass);
        try {
            Optional<AbstractEntity> optional = loginService.doService(user);
            if (optional.isPresent()) {
                user = (User) optional.get();
                HttpSession session = request.getSession(true);
                session.setAttribute(Attributes.USER.getValue(), user);
                String role = user.getRole();
                switch (role) {
                    case "admin":
                        session.setAttribute(Attributes.ROLE.getValue(), Attributes.ADMIN.getValue());
                        page = ConfigurationManager.getProperty(Pages.PAGE_MAIN_ADMIN.getValue());
                        break;
                    case "cleaner":
                        session.setAttribute(Attributes.ROLE.getValue(), Attributes.CLEANER.getValue());
                        CleanerInfoService cleanerInfoService = new CleanerInfoService();
                        Cleaner cleaner = new Cleaner();
                        cleaner.setIdUser(user.getId());
                        cleaner = cleanerInfoService.getCleaner(cleaner);
                        session.setAttribute(Attributes.CLEANER.getValue(), cleaner);
                        page = ConfigurationManager.getProperty(Pages.PAGE_MAIN_CLEANER.getValue());
                        break;
                    case "client":
                        session.setAttribute(Attributes.ROLE.getValue(), Attributes.CLIENT.getValue());
                        ClientInfoService clientInfoService = new ClientInfoService();
                        Client client = new Client();
                        client.setIdUser(user.getId());
                        client = clientInfoService.getClient(client); //ищем по айди клиента в бд
                        session.setAttribute(Attributes.CLIENT.getValue(), client);
                        page = ConfigurationManager.getProperty(Pages.PAGE_MAIN_CLIENT.getValue());
                        break;
                    case "guest":
                    default:
                        session.setAttribute(Attributes.ROLE.getValue(), Attributes.GUEST.getValue());
                        session.setAttribute(Attributes.ERROR_LOGIN.getValue(),
                                MessageManager.getProperty(Messages.MESSAGE_NOT_REG.getValue()));
                        page = ConfigurationManager.getProperty(Pages.PAGE_LOGIN.getValue());
                        break;
                }
            } else {
                request.getSession().setAttribute(Attributes.ERROR_LOGIN.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_LOGIN.getValue()));
                page = ConfigurationManager.getProperty(Pages.PAGE_LOGIN.getValue());
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while logged in. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}