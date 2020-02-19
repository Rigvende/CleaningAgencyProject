package by.patrusova.project.command.impl.show;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.DaoException;
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

public class ShowOrdersCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        ShowService service = new ShowService();
        try {
            List<AbstractEntity> list = service.doService(Attributes.ORDER.getValue());
            if (list.size() != 0) {
                List<Order> orders = new ArrayList<>();
                for (AbstractEntity entity : list) {
                    orders.add((Order)entity);
                }
                request.getSession().setAttribute(Attributes.ORDER_LIST.getValue(), orders);
                page = ConfigurationManager.getProperty(Pages.PAGE_ORDERLIST.getValue());
            } else {
                request.getSession().setAttribute(Attributes.EMPTY_LIST.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_LIST.getValue()));
                page = ConfigurationManager.getProperty(Pages.PAGE_MAIN_ADMIN.getValue());
            }
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while finding orders was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}