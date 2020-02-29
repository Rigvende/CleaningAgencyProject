package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ClientInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Class for command to change client's notes by cleaner
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class SetNotesCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID = "id";
    private final static String NOTES = "notes";
    private final static String ERROR_CHANGE_CLIENT = "errorChangeClientMessage";
    private final static String MESSAGE_ERROR_CHANGE_CLIENT = "message.changeerror";
    private final static String PAGE_ORDERLIST = "page.orderlist";
    private final static String PAGE_CONFIRM = "page.confirm";
    private ClientInfoService service = new ClientInfoService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String requestId = request.getParameter(ID);
        String requestNotes = request.getParameter(NOTES);
        try {
            if (NumberValidator.isValidOrderID(requestId)
                    && StringValidator.isValidStringSize(NOTES, requestNotes)) {
                long idOrder = Long.parseLong(requestId);
                Cleaner cleaner = (Cleaner) request.getSession().getAttribute(Role.CLEANER.getValue());
                long idCleaner = cleaner.getId();
                Optional<AbstractEntity> optional = service.doService(idOrder, idCleaner, requestNotes);
                if (optional.isPresent()) {
                    return ConfigurationManager.getProperty(PAGE_CONFIRM);
                }
            }
            request.getSession().setAttribute(ERROR_CHANGE_CLIENT,
                    MessageManager.getProperty(MESSAGE_ERROR_CHANGE_CLIENT));
            return ConfigurationManager.getProperty(PAGE_ORDERLIST);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while changing client was processing. ", e);
            throw new CommandException(e);
        }
    }
}