package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.CancelOrderService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CancelCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID = "id";
    private final static String ERROR_CHANGE_ORDER = "errorChangeOrderMessage";
    private final static String MESSAGE_ERROR_CHANGE_ORDER = "message.changeerror";
    private final static String PAGE_ORDERLIST = "page.orderlist";
    private final static String PAGE_CONFIRM = "page.confirm";
    private CancelOrderService service = new CancelOrderService();
    private Order order = new Order();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String orderId = request.getParameter(ID);
        try {
            if (NumberValidator.isValidOrderID(orderId)) {
                long id = Long.parseLong(orderId);
                Client client = (Client) request.getSession().getAttribute(Role.CLIENT.getValue());
                long clientId = client.getId();
                order.setId(id);
                order.setIdClient(clientId);
                if (service.doService(order).isPresent()) {
                    return ConfigurationManager.getProperty(PAGE_CONFIRM);
                }
            }
            request.getSession().setAttribute(ERROR_CHANGE_ORDER,
                    MessageManager.getProperty(MESSAGE_ERROR_CHANGE_ORDER));
            return ConfigurationManager.getProperty(PAGE_ORDERLIST);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while cancelling order has occurred. ", e);
            throw new CommandException(e);
        }
    }
}