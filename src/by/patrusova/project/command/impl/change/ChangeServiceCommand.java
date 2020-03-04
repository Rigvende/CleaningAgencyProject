package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ServiceInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * Class for command to change service's info by admin
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ChangeServiceCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String SERVICE = "service";
    private final static String ERROR_CHANGE_SERVICE = "errorChangeServiceMessage";
    private final static String MESSAGE_ERROR_CHANGE_SERVICE = "message.changeerror";
    private final static String PAGE_CHANGE_SERVICE = "page.changeservice";
    private final static String PAGE_CONFIRM = "page.confirm";
    private ServiceInfoService infoService = new ServiceInfoService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            Optional<AbstractEntity> opt = infoService.createEntity(request);
            if (opt.isPresent()) {
                Service service = (Service) opt.get();
                Optional<AbstractEntity> optional = infoService.doService(service);
                request.getSession().removeAttribute(SERVICE);
                if (optional.isPresent()) {
                    return ConfigurationManager.getProperty(PAGE_CONFIRM);
                }
            }
            request.getSession().setAttribute(ERROR_CHANGE_SERVICE,
                    MessageManager.getProperty(MESSAGE_ERROR_CHANGE_SERVICE));
            return ConfigurationManager.getProperty(PAGE_CHANGE_SERVICE);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while changing service info was processing. ", e);
            throw new CommandException(e);
        }
    }
}
