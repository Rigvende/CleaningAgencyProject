package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SetMarkCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID = "id";
    private final static String MARK = "mark";
    private final static String ERROR_CHANGE_ORDER = "errorChangeOrderMessage";
    private final static String MESSAGE_ERROR_CHANGE_ORDER = "message.changeerror";
    private final static String PAGE_ORDERLIST = "page.orderlist";
    private final static String PAGE_CONFIRM = "page.confirm";
    private OrderInfoService service = new OrderInfoService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String requestId = request.getParameter(ID);
        String requestMark = request.getParameter(MARK);
        try {
            if (NumberValidator.isValidOrderID(requestId) && NumberValidator.isValidMark(requestMark)) {
                long id = Long.parseLong(requestId);
                int mark = Integer.parseInt(requestMark);
                Client client = (Client) request.getSession().getAttribute(Role.CLIENT.getValue());
                long idClient = client.getId();
                Optional<AbstractEntity> optional = service.doService(id, idClient, mark);
                if (optional.isPresent()) {
                    return ConfigurationManager.getProperty(PAGE_CONFIRM);
                }
            }
            request.getSession().setAttribute(ERROR_CHANGE_ORDER,
                    MessageManager.getProperty(MESSAGE_ERROR_CHANGE_ORDER));
            return ConfigurationManager.getProperty(PAGE_ORDERLIST);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while changing order was processing. ", e);
            throw new CommandException(e);
        }
    }
}