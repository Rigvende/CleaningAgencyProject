package by.patrusova.project.command.impl.menu;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.ComplexPosition;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ShowService;
import by.patrusova.project.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BasketCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String BASKET_LIST = "basketList";
    private final static String ORDER_NEW = "orderNew";
    private final static String PAGE_BASKET = "page.basket";
    private ShowService service = new ShowService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            Order order = (Order) request.getSession().getAttribute(ORDER_NEW);
            if (order != null) {
                List<ComplexPosition> positions = service.doService(order.getId());
                if (!positions.isEmpty()) {
                    request.getSession().setAttribute(BASKET_LIST, positions);
                }
            }
            return ConfigurationManager.getProperty(PAGE_BASKET);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while assembling basket list was processing. ", e);
            throw new CommandException(e);
        }
    }
}