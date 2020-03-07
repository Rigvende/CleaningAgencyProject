package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
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

/**
 * Class for command to log out with associated activities
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class LogoutCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String PAGE_INDEX = "page.index";
    private final static String ORDER_NEW = "orderNew";
    private final static String CLIENT = "client";
    private final static String ROLE = "role";
    private final static String USER = "user";
    private DeleteEntityService service = new DeleteEntityService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        request.getSession().removeAttribute(USER);                    //prevent to go back
        String page = ConfigurationManager.getProperty(PAGE_INDEX);
        if (request.getSession().getAttribute(ROLE).equals(CLIENT)) {
            Order order = (Order) request.getSession().getAttribute(ORDER_NEW);
            Client client = (Client) request.getSession().getAttribute(CLIENT);
            Enumeration<String> enumeration = request.getSession().getAttributeNames();
            Iterator<String> iterator = enumeration.asIterator();
            while (iterator.hasNext()) {
                request.getSession().removeAttribute(iterator.next());
            }
            request.getSession().invalidate();
            request.getSession(false);
            try {
                if (order != null && client != null                    //delete not registered client's order
                        && order.getIdClient() == client.getId()
                        && order.getOrderStatus().equals(Order.Status.NEW.getValue())) {
                    Optional<AbstractEntity> optional = service.doService(order);
                    if (optional.isPresent()) {
                        throw new ServiceException("Cannot delete order or basket positions.");
                    }
                }
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, "Exception while deleting new order has occurred.", e);
                throw new CommandException(e);
            }
        }
        return page;
    }
}