package by.patrusova.project.command.impl.menu;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.BasketService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import by.patrusova.project.util.stringholder.Parameter;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SelectCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Order order = (Order) request.getSession().getAttribute(Attribute.ORDER.getValue());
        String idService = request.getParameter(Parameter.POSITION.getValue());
        String choice = request.getParameter(Parameter.CHOICE.getValue());
        if (choice.equals(Parameter.EMPTY.getValue())){
            return ConfigurationManager.getProperty(Page.PAGE_CATALOGUE.getValue());
        }
        try {
            if (!NumberValidator.isValidServiceID(idService)) {
                request.getSession().setAttribute(Attribute.ERROR_SELECT.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_SELECT.getValue()));
                return ConfigurationManager.getProperty(Page.PAGE_CATALOGUE.getValue());
            }
            BasketService service = new BasketService();
            BasketPosition position = new BasketPosition();
            position.setIdService(Long.parseLong(idService));
            position.setIdOrder(order.getId());
            Optional<AbstractEntity> optional = service.doService(position);
            if (optional.isEmpty()) {
                request.getSession().setAttribute(Attribute.ERROR_SELECT.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_SELECT.getValue()));
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while selecting service has occurred.", e);
        }
        return ConfigurationManager.getProperty(Page.PAGE_CATALOGUE.getValue());
    }
}