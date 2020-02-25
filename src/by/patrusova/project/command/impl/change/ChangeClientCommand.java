package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ClientInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ChangeClientCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ERROR_CHANGE_CLIENT = "errorChangeClientMessage";
    private final static String MESSAGE_ERROR_CHANGE_CLIENT = "message.changeerror";
    private final static String PAGE_CHANGE_CLIENT = "page.changeclient";
    private final static String PAGE_CONFIRM = "page.confirm";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        ClientInfoService clientInfoService = new ClientInfoService();
        try {
            Optional<AbstractEntity> optional = clientInfoService.createEntity(request);
            if (optional.isEmpty()) {
                request.getSession().setAttribute(ERROR_CHANGE_CLIENT,
                        MessageManager.getProperty(MESSAGE_ERROR_CHANGE_CLIENT));
                page = ConfigurationManager.getProperty(PAGE_CHANGE_CLIENT);
                return page;
            } else {
                Client client = (Client)optional.get();
                if (clientInfoService.doServiceByAdmin(client).isPresent()) {
                    page = ConfigurationManager.getProperty(PAGE_CONFIRM);
                } else {
                    request.getSession().setAttribute(ERROR_CHANGE_CLIENT,
                            MessageManager.getProperty(MESSAGE_ERROR_CHANGE_CLIENT));
                    page = ConfigurationManager.getProperty(PAGE_CHANGE_CLIENT);
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while changing client info was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}