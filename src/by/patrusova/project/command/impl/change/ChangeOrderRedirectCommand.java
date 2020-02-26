package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class ChangeOrderRedirectCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID = "id";
    private final static String ORDER = "order";
    private final static String PAGE_CHANGE_ORDER = "page.changeorder";
    private final static String ERROR_CHANGE_ORDER_ID = "errorChangeOrderIdMessage";
    private final static String MESSAGE_ERROR_CHANGE_ORDER_ID = "message.changeerrorid";
    private final static String PAGE_ORDERLIST = "page.orderlist";
    private OrderInfoService infoService = new OrderInfoService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String id = request.getParameter(ID);
        try {
            if (NumberValidator.isValidOrderID(id)) {
                Order order = new Order();
                order.setId(Long.parseLong(id));
                order = infoService.getOrder(order);
                request.getSession().setAttribute(ORDER, order);
                return ConfigurationManager.getProperty(PAGE_CHANGE_ORDER);
            } else {
                request.getSession().setAttribute(ERROR_CHANGE_ORDER_ID,
                        MessageManager.getProperty(MESSAGE_ERROR_CHANGE_ORDER_ID));
                return ConfigurationManager.getProperty(PAGE_ORDERLIST);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while redirecting service was processing. ", e);
            throw new CommandException(e);
        }
    }
}