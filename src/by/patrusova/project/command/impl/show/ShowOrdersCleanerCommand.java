package by.patrusova.project.command.impl.show;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.entity.impl.OrderComplex;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ShowOrderService;
import by.patrusova.project.service.impl.ShowService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowOrdersCleanerCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        ShowOrderService service = new ShowOrderService();
        Cleaner cleaner = (Cleaner) request.getSession().getAttribute(Attributes.CLEANER.getValue());
        String role = (String) request.getSession().getAttribute(Attributes.ROLE.getValue());
        try {
            List<OrderComplex> list = service.doService(role, cleaner);
            if (list.size() != 0) {
                request.getSession().setAttribute(Attributes.ORDER_LIST.getValue(), list);
                page = ConfigurationManager.getProperty(Pages.PAGE_ORDERLIST.getValue());
            } else {
                request.getSession().setAttribute(Attributes.EMPTY_LIST.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_LIST.getValue()));
                page = ConfigurationManager.getProperty(Pages.PAGE_MAIN_CLEANER.getValue());
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while finding orders was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}