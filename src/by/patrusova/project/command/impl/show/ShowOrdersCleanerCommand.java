package by.patrusova.project.command.impl.show;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.OrderComplex;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ShowOrderService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowOrdersCleanerCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        ShowOrderService service = new ShowOrderService();
        Cleaner cleaner = (Cleaner) request.getSession().getAttribute(Attribute.CLEANER.getValue());
        String role = (String) request.getSession().getAttribute(Attribute.ROLE.getValue());
        try {
            List<OrderComplex> list = service.doService(role, cleaner);
            if (!list.isEmpty()) {
                request.getSession().setAttribute(Attribute.ORDER_LIST.getValue(), list);
                page = ConfigurationManager.getProperty(Page.PAGE_ORDERLIST.getValue());
            } else {
                request.getSession().setAttribute(Attribute.EMPTY_LIST.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_LIST.getValue()));
                page = ConfigurationManager.getProperty(Page.PAGE_MAIN_CLEANER.getValue());
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while finding orders was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}