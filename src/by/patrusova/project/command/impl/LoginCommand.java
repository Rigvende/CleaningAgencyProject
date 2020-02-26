package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.*;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger();
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String EMPTY = "";
    private final static String ERROR_LOGIN = "errorLoginPassMessage";
    private final static String MESSAGE_ERROR_LOGIN = "message.loginerror";
    private final static String PAGE_LOGIN = "page.login";
    private final static String USER = "user";
    private final static String ROLE = "role";
    private final static String PAGE_MAIN_ADMIN = "page.mainadmin";
    private final static String PAGE_MAIN_CLEANER = "page.maincleaner";
    private final static String ORDER_NEW = "orderNew";
    private final static String PAGE_MAIN_CLIENT = "page.mainclient";
    private final static String MESSAGE_NOT_REG = "message.notregistered";
    private LoginService loginService = new LoginService();
    private CleanerInfoService cleanerInfoService = new CleanerInfoService();
    private ClientInfoService clientInfoService = new ClientInfoService();
    private OrderInfoService infoService = new OrderInfoService();
    private DeleteEntityService deleteService = new DeleteEntityService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String login = request.getParameter(LOGIN);
        String pass = request.getParameter(PASSWORD);
        if (login.equals(EMPTY) || pass.equals(EMPTY)) {
            request.getSession().setAttribute(ERROR_LOGIN,
                    MessageManager.getProperty(MESSAGE_ERROR_LOGIN));
            page = ConfigurationManager.getProperty(PAGE_LOGIN);
            return page;
        }
        User user = new User();
        user.setLogin(login);
        user.setPassword(pass);
        try {
            Optional<AbstractEntity> optional = loginService.doService(user);
            if (optional.isPresent()) {
                user = (User) optional.get();
                HttpSession session = request.getSession(true);
                session.setAttribute(USER, user);
                String role = user.getRole();
                switch (role) {
                    case "admin":
                        session.setAttribute(ROLE, Role.ADMIN.getValue());
                        page = ConfigurationManager.getProperty(PAGE_MAIN_ADMIN);
                        break;
                    case "cleaner":
                        session.setAttribute(ROLE, Role.CLEANER.getValue());
                        Cleaner cleaner = new Cleaner();
                        cleaner.setIdUser(user.getId());
                        cleaner = cleanerInfoService.getCleaner(cleaner); //extracting cleaner from DB by ID
                        session.setAttribute(Role.CLEANER.getValue(), cleaner);
                        page = ConfigurationManager.getProperty(PAGE_MAIN_CLEANER);
                        break;
                    case "client":
                        session.setAttribute(ROLE, Role.CLIENT.getValue());
                        Client client = new Client();
                        client.setIdUser(user.getId());
                        client = clientInfoService.getClient(client);        //extracting client from DB by ID
                        session.setAttribute(Role.CLIENT.getValue(), client);
                        deleteService.doService(client.getId()); //delete previous "new" order of the same client
                        Order order = new Order(); //create order with status "new"
                        order.setIdClient(client.getId());
                        order.setOrderStatus(Order.Status.NEW.getValue());
                        Optional<AbstractEntity> opt = infoService.doService(order);
                        if (opt.isPresent()) {
                            order = (Order) opt.get();
                            session.setAttribute(ORDER_NEW, order);
                        }
                        page = ConfigurationManager.getProperty(PAGE_MAIN_CLIENT);
                        break;
                    case "guest":
                    default:
                        session.setAttribute(ROLE, Role.GUEST.getValue());
                        session.setAttribute(ERROR_LOGIN,
                                MessageManager.getProperty(MESSAGE_NOT_REG));
                        page = ConfigurationManager.getProperty(PAGE_LOGIN);
                        break;
                }
            } else {
                request.getSession().setAttribute(ERROR_LOGIN,
                        MessageManager.getProperty(MESSAGE_ERROR_LOGIN));
                page = ConfigurationManager.getProperty(PAGE_LOGIN);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while logging in has occurred. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}