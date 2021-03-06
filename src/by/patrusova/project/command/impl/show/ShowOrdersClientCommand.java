package by.patrusova.project.command.impl.show;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.ComplexOrder;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ShowOrderService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Class for command to show orders list for client
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ShowOrdersClientCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ROLE = "role";
    private final static String ORDER_LIST = "orderList";
    private final static String PAGE_ORDERLIST = "page.orderlist";
    private final static String EMPTY_LIST = "emptyList";
    private final static String MESSAGE_ERROR_LIST = "message.listerror";
    private final static String PAGE_MAIN_CLIENT = "page.mainclient";
    private ShowOrderService service = new ShowOrderService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Client client = (Client) request.getSession().getAttribute(Role.CLIENT.getValue());
        String role = (String) request.getSession().getAttribute(ROLE);
        try {
            List<ComplexOrder> list = service.doService(role, client);      //without orders with "new" status
            if (!list.isEmpty()) {
                request.getSession().setAttribute(ORDER_LIST, list);
                return ConfigurationManager.getProperty(PAGE_ORDERLIST);
            } else {
                request.getSession().setAttribute(EMPTY_LIST,
                        MessageManager.getProperty(MESSAGE_ERROR_LIST));
                return ConfigurationManager.getProperty(PAGE_MAIN_CLIENT);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while finding orders was processing. ", e);
            throw new CommandException(e);
        }
    }
}