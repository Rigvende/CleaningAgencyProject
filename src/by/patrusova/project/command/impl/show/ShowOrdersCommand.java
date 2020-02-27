package by.patrusova.project.command.impl.show;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ShowService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowOrdersCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ORDERS = "orders";
    private final static String ORDER_LIST = "orderList";
    private final static String PAGE_ORDERLIST = "page.orderlist";
    private final static String EMPTY_LIST = "emptyList";
    private final static String MESSAGE_ERROR_LIST = "message.listerror";
    private final static String PAGE_MAIN_ADMIN = "page.mainadmin";
    private ShowService service = new ShowService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            List<AbstractEntity> list = service.doService(ORDERS);
            if (!list.isEmpty()) {
                List<Order> orders = new ArrayList<>();
                for (AbstractEntity entity : list) {
                    orders.add((Order)entity);
                }
                request.getSession().setAttribute(ORDER_LIST, orders);
                return ConfigurationManager.getProperty(PAGE_ORDERLIST);
            } else {
                request.getSession().setAttribute(EMPTY_LIST,
                        MessageManager.getProperty(MESSAGE_ERROR_LIST));
                return ConfigurationManager.getProperty(PAGE_MAIN_ADMIN);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while finding orders was processing. ", e);
            throw new CommandException(e);
        }
    }
}