package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ClientInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 * Class for command to redirect to change client's info form
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ChangeClientRedirectCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID = "id";
    private final static String PAGE_CHANGE_CLIENT = "page.changeclient";
    private final static String ERROR_CHANGE_CLIENT_ID = "errorChangeClientIdMessage";
    private final static String MESSAGE_ERROR_CHANGE_CLIENT_ID = "message.changeerrorid";
    private final static String PAGE_CLIENTLIST = "page.clientlist";
    private ClientInfoService service = new ClientInfoService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String id = request.getParameter(ID);
        try {
            if (NumberValidator.isValidUserID(id)) {
                Client client = new Client();
                client.setIdUser(Long.parseLong(id));
                client = service.getClient(client);
                if (client != null) {
                    request.getSession().setAttribute(Role.CLIENT.getValue(), client);
                    return ConfigurationManager.getProperty(PAGE_CHANGE_CLIENT);
                }
            }
            request.getSession().setAttribute(ERROR_CHANGE_CLIENT_ID,
                    MessageManager.getProperty(MESSAGE_ERROR_CHANGE_CLIENT_ID));
            return ConfigurationManager.getProperty(PAGE_CLIENTLIST);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while redirecting client was processing. ", e);
            throw new CommandException(e);
        }
    }
}