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

/**
 * Class for command to change client's info by admin
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ChangeClientCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ERROR_CHANGE_CLIENT = "errorChangeClientMessage";
    private final static String MESSAGE_ERROR_CHANGE_CLIENT = "message.changeerror";
    private final static String PAGE_CHANGE_CLIENT = "page.changeclient";
    private final static String PAGE_CONFIRM = "page.confirm";
    private ClientInfoService clientInfoService = new ClientInfoService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            Optional<AbstractEntity> optional = clientInfoService.createEntity(request);
            if (optional.isPresent()) {
                Client client = (Client) optional.get();
                if (clientInfoService.doServiceByAdmin(client).isPresent()) {
                    return ConfigurationManager.getProperty(PAGE_CONFIRM);
                }
            }
            request.getSession().setAttribute(ERROR_CHANGE_CLIENT,
                    MessageManager.getProperty(MESSAGE_ERROR_CHANGE_CLIENT));
            return ConfigurationManager.getProperty(PAGE_CHANGE_CLIENT);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while changing client info was processing. ", e);
            throw new CommandException(e);
        }
    }
}