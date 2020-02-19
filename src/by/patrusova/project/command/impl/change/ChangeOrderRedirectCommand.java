package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.service.impl.ServiceInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
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
        String id = request.getParameter(Attributes.ID.getValue());
        if (NumberValidator.isValidID(id)) {
            order.setId(Long.parseLong(id));
            try {
                order = infoService.getOrder(order);
                request.getSession().setAttribute(Attributes.ORDER.getValue(), order);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, "Exception has occurred while redirecting service was processing. ", e);
                throw new CommandException(e);
            }
            return ConfigurationManager.getProperty(Pages.PAGE_CHANGE_ORDER.getValue());
        } else {
            request.getSession().setAttribute(Attributes.ERROR_CHANGE_ORDER_ID.getValue(),
                    MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_ORDER_ID.getValue()));
            return ConfigurationManager.getProperty(Pages.PAGE_ORDERLIST.getValue());
        }
    }
}
