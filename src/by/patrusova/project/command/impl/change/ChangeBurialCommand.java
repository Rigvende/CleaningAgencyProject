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

public class ChangeBurialCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ERROR_CHANGE_BURIAL = "errorChangeBurialMessage";
    private final static String MESSAGE_ERROR_CHANGE_BURIAL = "message.changeerror";
    private final static String PAGE_CHANGE_BURIAL = "page.changeburialform";
    private final static String PAGE_PROFILE = "page.profile";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        ClientInfoService clientInfoService = new ClientInfoService();
        try {
            Optional<AbstractEntity> opt = clientInfoService.createEntity(request);
            if (opt.isEmpty()) {
                request.getSession().setAttribute(ERROR_CHANGE_BURIAL,
                        MessageManager.getProperty(MESSAGE_ERROR_CHANGE_BURIAL));
                page = ConfigurationManager.getProperty(PAGE_CHANGE_BURIAL);
                return page;
            } else {
                Client client = (Client)opt.get();
                Optional<AbstractEntity> optional = clientInfoService.doService(client);
                if (optional.isPresent()) {
                    page = ConfigurationManager.getProperty(PAGE_PROFILE);
                } else {
                    request.getSession().setAttribute(ERROR_CHANGE_BURIAL,
                            MessageManager.getProperty(MESSAGE_ERROR_CHANGE_BURIAL));
                    page = ConfigurationManager.getProperty(PAGE_CHANGE_BURIAL);
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