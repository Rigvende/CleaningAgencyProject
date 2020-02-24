package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.DeleteEntityService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Pages;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Optional;

public class LogoutCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = ConfigurationManager.getProperty(Pages.PAGE_INDEX.getValue());
        Order order = (Order) request.getSession().getAttribute(Attributes.ORDER.getValue());
        try {                                           //delete not registered order from session
            if (order != null) {
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