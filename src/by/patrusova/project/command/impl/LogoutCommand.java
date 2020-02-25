package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.DeleteEntityService;
import by.patrusova.project.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Optional;

public class LogoutCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String PAGE_INDEX = "page.index";
    private final static String ORDER = "order";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigurationManager.getProperty(PAGE_INDEX);
        Order order = (Order) request.getSession().getAttribute(ORDER);
        try {                                           //delete not registered order from session
            if (order != null && order.getOrderStatus().equals(Order.Status.NEW.getValue())) {
                DeleteEntityService service = new DeleteEntityService();
                Optional<AbstractEntity> optional = service.doService(order);
                if (optional.isPresent()) {
                    throw new ServiceException("Cannot delete order or basket positions.");
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while deleting new order has occurred.", e);
            throw new CommandException(e);
        } finally {
            Enumeration<String> enumeration = request.getSession().getAttributeNames();
            Iterator<String> iterator = enumeration.asIterator();
            while (iterator.hasNext()) {
                request.getSession().removeAttribute(iterator.next());
            }
            request.getSession().invalidate();
        }
        return page;
    }
}