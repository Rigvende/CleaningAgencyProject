package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ClientInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import by.patrusova.project.util.stringholder.Parameter;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SetNotesCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Optional<AbstractEntity> optional;
        String requestId = request.getParameter(Parameter.ID.getValue());
        String requestNotes = request.getParameter(Parameter.NOTES.getValue());
        try {
            if (!NumberValidator.isValidOrderID(requestId)
                    || !StringValidator.isValidStringSize(Parameter.NOTES.getValue(), requestNotes)) {
                request.getSession().setAttribute(Attribute.ERROR_CHANGE_CLIENT.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_CLIENT.getValue()));
                return ConfigurationManager.getProperty(Page.PAGE_ORDERLIST.getValue());
            }
            long idOrder = Long.parseLong(requestId);
            Cleaner cleaner = (Cleaner) request.getSession().getAttribute(Attribute.CLEANER.getValue());
            long idCleaner = cleaner.getId();
            ClientInfoService service = new ClientInfoService();
            optional = service.doService(idOrder, idCleaner, requestNotes);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while changing client was processing. ", e);
            throw new CommandException(e);
        }
        if (optional.isEmpty()) {
            request.getSession().setAttribute(Attribute.ERROR_CHANGE_CLIENT.getValue(),
                    MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_CLIENT.getValue()));
            return ConfigurationManager.getProperty(Page.PAGE_ORDERLIST.getValue());
        } else {
            return ConfigurationManager.getProperty(Page.PAGE_CONFIRM.getValue());
        }
    }
}