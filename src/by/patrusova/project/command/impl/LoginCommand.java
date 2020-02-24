package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.*;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.CleanerInfoService;
import by.patrusova.project.service.impl.ClientInfoService;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.service.impl.LoginService;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import by.patrusova.project.util.stringholder.Parameter;
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
        String login = request.getParameter(Parameter.LOGIN.getValue());
        String pass = request.getParameter(Parameter.PASSWORD.getValue());
        if (login.equals(Parameter.EMPTY.getValue()) || pass.equals(Parameter.EMPTY.getValue())) {
            request.getSession().setAttribute(Attribute.ERROR_LOGIN.getValue(),
                    MessageManager.getProperty(Message.MESSAGE_ERROR_LOGIN.getValue()));
            page = ConfigurationManager.getProperty(Page.PAGE_LOGIN.getValue());
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
                session.setAttribute(Attribute.USER.getValue(), user);
                String role = user.getRole();
                switch (role) {
                    case "admin":
                        session.setAttribute(Attribute.ROLE.getValue(), Attribute.ADMIN.getValue());
                        page = ConfigurationManager.getProperty(Page.PAGE_MAIN_ADMIN.getValue());
                        break;
                    case "cleaner":
                        session.setAttribute(Attribute.ROLE.getValue(), Attribute.CLEANER.getValue());
                        CleanerInfoService cleanerInfoService = new CleanerInfoService();
                        Cleaner cleaner = new Cleaner();
                        cleaner.setIdUser(user.getId());
                        cleaner = cleanerInfoService.getCleaner(cleaner); //extracting cleaner from DB by ID
                        session.setAttribute(Attribute.CLEANER.getValue(), cleaner);
                        page = ConfigurationManager.getProperty(Page.PAGE_MAIN_CLEANER.getValue());
                        break;
                    case "client":
                        session.setAttribute(Attribute.ROLE.getValue(), Attribute.CLIENT.getValue());
                        ClientInfoService clientInfoService = new ClientInfoService();
                        Client client = new Client();
                        client.setIdUser(user.getId());
                        client = clientInfoService.getClient(client);   //extracting client from DB by ID
                        session.setAttribute(Attribute.CLIENT.getValue(), client);
                        Order order = new Order();                      //create order with status "new"
                        order.setIdClient(client.getId());
                        order.setOrderStatus(Order.Status.NEW.getValue());
                        OrderInfoService infoService = new OrderInfoService();
                        Optional<AbstractEntity> opt = infoService.doService(order);
                        if (opt.isPresent()) {
                            order = (Order) opt.get();
                            session.setAttribute(Attribute.ORDER.getValue(), order);
                        }
                        page = ConfigurationManager.getProperty(Page.PAGE_MAIN_CLIENT.getValue());
                        break;
                    case "guest":
                    default:
                        session.setAttribute(Attribute.ROLE.getValue(), Attribute.GUEST.getValue());
                        session.setAttribute(Attribute.ERROR_LOGIN.getValue(),
                                MessageManager.getProperty(Message.MESSAGE_NOT_REG.getValue()));
                        page = ConfigurationManager.getProperty(Page.PAGE_LOGIN.getValue());
                        break;
                }
            } else {
                request.getSession().setAttribute(Attribute.ERROR_LOGIN.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_LOGIN.getValue()));
                page = ConfigurationManager.getProperty(Page.PAGE_LOGIN.getValue());
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while logging in has occurred. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}