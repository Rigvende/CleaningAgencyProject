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

public class AddEntityCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ERROR_ADD_SERVICE = "errorAddServiceMessage";
    private final static String MESSAGE_ERROR_ADD_SERVICE = "message.adderror";
    private final static String PAGE_ADD_SERVICE = "page.addservice";
    private final static String PAGE_CONFIRM = "page.confirm";
    private ServiceInfoService infoService = new ServiceInfoService();

    public String execute(HttpServletRequest request) throws CommandException {
        try {
            Optional<AbstractEntity> optional = infoService.createNewEntity(request);
            if (optional.isPresent()) {
                Service service = (Service) optional.get();
                Optional<AbstractEntity> opt = infoService.doServiceAdd(service);
                if (opt.isPresent()) {
                    return ConfigurationManager.getProperty(PAGE_CONFIRM);
                }
            }
            request.getSession().setAttribute(ERROR_ADD_SERVICE,
                    MessageManager.getProperty(MESSAGE_ERROR_ADD_SERVICE));
            return ConfigurationManager.getProperty(PAGE_ADD_SERVICE);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while adding service was processing. ", e);
            throw new CommandException(e);
        }
    }
}