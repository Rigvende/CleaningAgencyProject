package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.CancelOrderService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import by.patrusova.project.util.stringholder.Parameter;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class CancelCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String orderId = request.getParameter(Parameter.ID.getValue());
        try {
            if (!NumberValidator.isValidOrderID(orderId)) {
                request.getSession().setAttribute(Attribute.ERROR_CHANGE_ORDER.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_ORDER.getValue()));
                return ConfigurationManager.getProperty(Page.PAGE_ORDERLIST.getValue());
            }
            long id = Long.parseLong(orderId);
            Client client = (Client)request.getSession().getAttribute(Attribute.CLIENT.getValue());
            long clientId = client.getId();
            CancelOrderService service = new CancelOrderService();
            Order order = new Order();
            order.setId(id);
            order.setIdClient(clientId);
            if (service.doService(order).isPresent()) {
                return ConfigurationManager.getProperty(Page.PAGE_CONFIRM.getValue());
            } else {
                request.getSession().setAttribute(Attribute.ERROR_CHANGE_ORDER.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_ORDER.getValue()));
                return ConfigurationManager.getProperty(Page.PAGE_ORDERLIST.getValue());
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while cancelling order has occurred. ", e);
            throw new CommandException(e);
        }
    }
}