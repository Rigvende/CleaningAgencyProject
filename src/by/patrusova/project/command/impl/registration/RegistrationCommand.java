package by.patrusova.project.command.impl.registration;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.RegistrationService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RegistrationCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ERROR_REG = "errorRegistrationMessage";
    private final static String MESSAGE_ERROR_REG = "message.registrationerror";
    private final static String PAGE_REG = "page.registrationform";
    private final static String NEW_USER = "newuser";
    private final static String PAGE_REG_TRUE = "page.registrationtrue";
    private RegistrationService service = new RegistrationService();

    public String execute(HttpServletRequest request) throws CommandException {
        try {
            Optional<AbstractEntity> optional = service.createEntity(request);
            if (optional.isPresent()) {
                User user = (User) optional.get();
                if (service.doService(user).isPresent()) {
                    request.getSession().setAttribute(NEW_USER, user);
                    return ConfigurationManager.getProperty(PAGE_REG_TRUE);
                }
            }
            request.getSession().setAttribute(ERROR_REG,
                    MessageManager.getProperty(MESSAGE_ERROR_REG));
            return ConfigurationManager.getProperty(PAGE_REG);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while registration was processing. ", e);
            throw new CommandException(e);
        }
    }
}