package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class ChangeOrderRedirectCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        OrderInfoService infoService = new OrderInfoService();
        Order order = new Order();
        String id = request.getParameter(Attribute.ID.getValue());
        try {
            if (NumberValidator.isValidOrderID(id)) {
                order.setId(Long.parseLong(id));
                order = infoService.getOrder(order);
                request.getSession().setAttribute(Attribute.ORDER.getValue(), order);
                return ConfigurationManager.getProperty(Page.PAGE_CHANGE_ORDER.getValue());
            } else {
                request.getSession().setAttribute(Attribute.ERROR_CHANGE_ORDER_ID.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_ORDER_ID.getValue()));
                return ConfigurationManager.getProperty(Page.PAGE_ORDERLIST.getValue());
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while redirecting service was processing. ", e);
            throw new CommandException(e);
        }
    }
}