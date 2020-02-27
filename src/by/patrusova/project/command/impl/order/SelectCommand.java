package by.patrusova.project.command.impl.order;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.BasketService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SelectCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ORDER = "orderNew";
    private final static String POSITION = "position";
    private final static String CHOICE = "choice";
    private final static String EMPTY = "";
    private final static String PAGE_CATALOGUE = "page.catalogue";
    private final static String ERROR_SELECT = "errorSelect";
    private final static String MESSAGE_ERROR_SELECT = "message.selecterror";
    private final static String MESSAGE_ERROR_EMPTY = "message.emptychoice";
    private BasketService service = new BasketService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Order order = (Order) request.getSession().getAttribute(ORDER);
        String idService = request.getParameter(POSITION);
        String choice = request.getParameter(CHOICE);
        if (choice.equals(EMPTY)){
            request.getSession().setAttribute(ERROR_SELECT,
                    MessageManager.getProperty(MESSAGE_ERROR_EMPTY));
            return ConfigurationManager.getProperty(PAGE_CATALOGUE);
        }
        try {
            if (NumberValidator.isValidServiceID(idService)) {
                BasketPosition position = new BasketPosition();
                position.setIdService(Long.parseLong(idService));
                position.setIdOrder(order.getId());
                Optional<AbstractEntity> optional = service.doService(position);
                if (optional.isPresent()) {
                    return ConfigurationManager.getProperty(PAGE_CATALOGUE);
                }
            }
            request.getSession().setAttribute(ERROR_SELECT,
                    MessageManager.getProperty(MESSAGE_ERROR_SELECT));
            return ConfigurationManager.getProperty(PAGE_CATALOGUE);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while selecting service has occurred.", e);
            throw new CommandException(e);
        }
    }
}