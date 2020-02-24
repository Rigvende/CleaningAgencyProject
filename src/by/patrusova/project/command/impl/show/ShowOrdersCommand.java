package by.patrusova.project.command.impl.show;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ShowService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowOrdersCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        ShowService service = new ShowService();
        try {
            List<AbstractEntity> list = service.doService(Attribute.ORDER.getValue());
            if (!list.isEmpty()) {
                List<Order> orders = new ArrayList<>();
                for (AbstractEntity entity : list) {
                    orders.add((Order)entity);
                }
                request.getSession().setAttribute(Attribute.ORDER_LIST.getValue(), orders);
                page = ConfigurationManager.getProperty(Page.PAGE_ORDERLIST.getValue());
            } else {
                request.getSession().setAttribute(Attribute.EMPTY_LIST.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_LIST.getValue()));
                page = ConfigurationManager.getProperty(Page.PAGE_MAIN_ADMIN.getValue());
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while finding orders was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}