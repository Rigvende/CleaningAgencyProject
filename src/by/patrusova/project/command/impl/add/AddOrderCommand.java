package by.patrusova.project.command.impl.add;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * Class for command to add new order
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class AddOrderCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger();
    private final static String ORDER_NEW = "orderNew";
    private final static String PAGE_MAIN_CLIENT = "page.mainclient";
    private OrderInfoService infoService = new OrderInfoService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Client client = (Client) request.getSession().getAttribute(Role.CLIENT.getValue());
        Order order = new Order();
        order.setIdClient(client.getId());
        order.setOrderStatus(Order.Status.NEW.getValue());
        HttpSession session = request.getSession(true);
        try {
            Optional<AbstractEntity> opt = infoService.doService(order);
            if (opt.isPresent()) {
                order = (Order) opt.get();
                session.setAttribute(ORDER_NEW, order);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while creating new order has occurred. ", e);
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(PAGE_MAIN_CLIENT);
    }
}