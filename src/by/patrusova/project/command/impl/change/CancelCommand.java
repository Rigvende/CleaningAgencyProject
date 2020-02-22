package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.CancelOrderService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.util.stringholder.Parameters;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CancelCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String orderId = request.getParameter(Parameters.ID.getValue());
        try {
            if (!NumberValidator.isValidOrderID(orderId)) {
                request.getSession().setAttribute(Attributes.ERROR_CHANGE_ORDER.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_ORDER.getValue()));
                return ConfigurationManager.getProperty(Pages.PAGE_ORDERLIST.getValue());
            }
            long id = Long.parseLong(orderId);
            Client client = (Client)request.getSession().getAttribute(Attributes.CLIENT.getValue());
            long clientId = client.getId();
            CancelOrderService service = new CancelOrderService();
            Order order = new Order();
            order.setId(id);
            order.setIdClient(clientId);
            if (service.doService(order) != null) {
                return ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
            } else {
                request.getSession().setAttribute(Attributes.ERROR_CHANGE_ORDER.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_ORDER.getValue()));
                return ConfigurationManager.getProperty(Pages.PAGE_ORDERLIST.getValue());
            }
        } catch (ServiceException | DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while cancelling order has occurred. ", e);
            throw new CommandException(e);
        }
    }
}