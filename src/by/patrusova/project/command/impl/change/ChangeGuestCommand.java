package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.RoleService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ChangeGuestCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID = "id";
    private final static String ROLE = "role";
    private final static String ERROR_CHANGE_GUEST = "errorChangeGuestMessage";
    private final static String MESSAGE_ERROR_CHANGE_GUEST = "message.changeerror";
    private final static String PAGE_GUESTLIST = "page.guestlist";
    private final static String FORMERGUEST = "formerguest";
    private final static String PAGE_MAIL = "page.mail";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String Id = request.getParameter(ID);
        String role = request.getParameter(ROLE);
        try {
            if (!NumberValidator.isValidUserID(Id) || !StringValidator.isValidRole(role)){
                request.getSession().setAttribute(ERROR_CHANGE_GUEST,
                        MessageManager.getProperty(MESSAGE_ERROR_CHANGE_GUEST));
                return ConfigurationManager.getProperty(PAGE_GUESTLIST);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while changing role was processing. ", e);
            throw new CommandException(e);
        }
        RoleService roleService = new RoleService();
        long id = Long.parseLong(Id);
        Optional<AbstractEntity> optional;
        try {
            optional = roleService.doService(id, role);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while changing role was processing. ", e);
            throw new CommandException(e);
        }
        if (optional.isEmpty()) {
            request.getSession().setAttribute(ERROR_CHANGE_GUEST,
                    MessageManager.getProperty(MESSAGE_ERROR_CHANGE_GUEST));
            return ConfigurationManager.getProperty(PAGE_GUESTLIST);
        } else {
            User user = (User) optional.get();
            request.getSession().setAttribute(FORMERGUEST, user);
            return ConfigurationManager.getProperty(PAGE_MAIL);
        }
    }
}