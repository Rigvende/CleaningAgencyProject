package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.UserInfoService;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ChangeUserCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        UserInfoService userInfoService = new UserInfoService();
        try {
            Optional<AbstractEntity> opt = userInfoService.createEntity(request);
            if (opt.isEmpty()) {
                request.getSession().setAttribute(Attribute.ERROR_CHANGE_USER.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_USER.getValue()));
                page = ConfigurationManager.getProperty(Page.PAGE_CHANGE.getValue());
                return page;
            } else {
                User user = (User)opt.get();
                Optional<AbstractEntity> optional = userInfoService.doService(user);
                if (optional.isPresent()) {
                    page = ConfigurationManager.getProperty(Page.PAGE_PROFILE.getValue());
                } else {
                    request.getSession().setAttribute(Attribute.ERROR_CHANGE_USER.getValue(),
                            MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_USER.getValue()));
                    page = ConfigurationManager.getProperty(Page.PAGE_CHANGE.getValue());
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