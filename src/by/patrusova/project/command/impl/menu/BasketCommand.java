package by.patrusova.project.command.impl.menu;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.ComplexPosition;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.service.impl.ShowService;
import by.patrusova.project.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Class for command to show basket
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class BasketCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String BASKET_LIST = "basketList";
    private final static String ORDER_NEW = "orderNew";
    private final static String TOTAL_COST = "totalCost";
    private final static String PAGE_BASKET = "page.basket";
    private ShowService showService = new ShowService();
    private OrderInfoService infoService = new OrderInfoService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            request.getSession().removeAttribute(BASKET_LIST);
            Order order = (Order) request.getSession().getAttribute(ORDER_NEW);
            if (order != null) {
                List<ComplexPosition> positions = showService.doService(order.getId());
                if (!positions.isEmpty()) {
                    request.getSession().setAttribute(BASKET_LIST, positions);
                    BigDecimal totalCost =                                      //show total cost with discount
                            infoService.doService(order.getId()).setScale(2, RoundingMode.HALF_UP);
                    request.getSession().setAttribute(TOTAL_COST, totalCost);
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