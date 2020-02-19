package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class ChangeOrderCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        OrderInfoService infoService = new OrderInfoService();
        try {
            Order order = infoService.createEntity(request);
            if (order == null) {
                request.getSession().setAttribute(Attributes.ERROR_CHANGE_ORDER.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_ORDER.getValue()));
                page = ConfigurationManager.getProperty(Pages.PAGE_CHANGE_ORDER.getValue());
                return page;
            } else {
                order = infoService.doService(order);
                if (order != null) {
                    page = ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
                } else {
                    request.getSession().setAttribute(Attributes.ERROR_CHANGE_ORDER.getValue(),
                            MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_ORDER.getValue()));
                    page = ConfigurationManager.getProperty(Pages.PAGE_CHANGE_ORDER.getValue());
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while changing service info was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}