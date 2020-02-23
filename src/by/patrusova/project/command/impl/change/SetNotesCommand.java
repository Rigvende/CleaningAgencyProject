package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ClientInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.util.stringholder.Parameters;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class SetNotesCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Optional<AbstractEntity> optional;
        String requestId = request.getParameter(Parameters.ID.getValue());
        String requestNotes = request.getParameter(Parameters.NOTES.getValue());
        try {
            if (!NumberValidator.isValidOrderID(requestId)
                    || !StringValidator.isValidStringSize(Parameters.NOTES.getValue(), requestNotes)) {
                request.getSession().setAttribute(Attributes.ERROR_CHANGE_CLIENT.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_CLIENT.getValue()));
                return ConfigurationManager.getProperty(Pages.PAGE_ORDERLIST.getValue());
            }
            long idOrder = Long.parseLong(requestId);
            Cleaner cleaner = (Cleaner) request.getSession().getAttribute(Attributes.CLEANER.getValue());
            long idCleaner = cleaner.getId();
            ClientInfoService service = new ClientInfoService();
            optional = service.doService(idOrder, idCleaner, requestNotes);
        } catch (ServiceException | DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while changing client was processing. ", e);
            throw new CommandException(e);
        }
        if (optional.isEmpty()) {
            request.getSession().setAttribute(Attributes.ERROR_CHANGE_CLIENT.getValue(),
                    MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_CLIENT.getValue()));
            return ConfigurationManager.getProperty(Pages.PAGE_ORDERLIST.getValue());
        } else {
            return ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
        }
    }
}