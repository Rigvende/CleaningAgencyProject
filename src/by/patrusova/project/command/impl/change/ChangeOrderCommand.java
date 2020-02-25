package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.MailService;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ChangeOrderCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ERROR_CHANGE_ORDER = "errorChangeOrderMessage";
    private final static String MESSAGE_ERROR_CHANGE_ORDER = "message.changeerror";
    private final static String PAGE_CHANGE_ORDER = "page.changeorder";
    private final static String PAGE_CONFIRM = "page.confirm";
    private final static String PAGE_MAIL = "page.mail";
    private final static String ORDER_DONE = "orderDone";
    private OrderInfoService infoService = new OrderInfoService();
    private MailService mailService = new MailService();
    private Client client = new Client();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            Optional<AbstractEntity> optional1 = infoService.createEntity(request);
            if (optional1.isPresent()) {
                Order order = (Order) optional1.get();
                Optional<AbstractEntity> optional2 = infoService.doService(order);
                if (optional2.isPresent()) {
                    if (order.getOrderStatus().equals(Order.Status.DONE.getValue())) {
                        User user;
                        client.setId(order.getIdClient());
                        Optional<AbstractEntity> optional3 = mailService.doService(client);
                        if (optional3.isPresent()) {
                            user = (User) optional3.get();
                            request.getSession().setAttribute(ORDER_DONE, user);
                            return ConfigurationManager.getProperty(PAGE_MAIL);
                        }
                    }
                    return ConfigurationManager.getProperty(PAGE_CONFIRM);
                }
            }
            request.getSession().setAttribute(ERROR_CHANGE_ORDER,
                    MessageManager.getProperty(MESSAGE_ERROR_CHANGE_ORDER));
            return ConfigurationManager.getProperty(PAGE_CHANGE_ORDER);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while changing service info was processing. ", e);
            throw new CommandException(e);
        }
    }
}