package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ClientInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ChangeClientRedirectCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ClientInfoService service = new ClientInfoService();
        Client client = new Client();
        String id = request.getParameter(Attributes.ID.getValue());
        try {
            if (NumberValidator.isValidUserID(id)) {
                client.setIdUser(Long.parseLong(id));
                client = service.getClient(client);
                if (client != null) {
                    request.getSession().setAttribute(Attributes.CLIENT.getValue(), client);
                    return ConfigurationManager.getProperty(Pages.PAGE_CHANGE_CLIENT.getValue());
                } else {
                    request.getSession().setAttribute(Attributes.ERROR_CHANGE_CLIENT_ID.getValue(),
                            MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_CLIENT_ID.getValue()));
                    return ConfigurationManager.getProperty(Pages.PAGE_CLIENTLIST.getValue());
                }
            } else {
                request.getSession().setAttribute(Attributes.ERROR_CHANGE_CLIENT_ID.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_CLIENT_ID.getValue()));
                return ConfigurationManager.getProperty(Pages.PAGE_CLIENTLIST.getValue());
            }
        } catch (ServiceException | DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while redirecting client was processing. ", e);
            throw new CommandException(e);
        }
    }
}