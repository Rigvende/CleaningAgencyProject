package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.UserInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ChangeUserCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ERROR_CHANGE_USER = "errorChangeUserMessage";
    private final static String MESSAGE_ERROR_CHANGE_USER = "message.changeerror";
    private final static String PAGE_CHANGE = "page.changeform";
    private final static String PAGE_PROFILE = "page.profile";
    private UserInfoService userInfoService = new UserInfoService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        try {
            Optional<AbstractEntity> opt = userInfoService.createEntity(request);
            if (opt.isPresent()) {
                User user = (User) opt.get();
                Optional<AbstractEntity> optional = userInfoService.doService(user);
                if (optional.isPresent()) {
                    return ConfigurationManager.getProperty(PAGE_PROFILE);
                }
            }
            request.getSession().setAttribute(ERROR_CHANGE_USER,
                    MessageManager.getProperty(MESSAGE_ERROR_CHANGE_USER));
            return ConfigurationManager.getProperty(PAGE_CHANGE);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while changing client info was processing. ", e);
            throw new CommandException(e);
        }
    }
}