package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.RegistrationService;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class RegistrationCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        RegistrationService service = new RegistrationService();
        try {
            Optional<AbstractEntity> optional = service.createEntity(request);
            if (optional.isEmpty()) {
                request.getSession().setAttribute(Attribute.ERROR_REG.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_REG.getValue()));
                page = ConfigurationManager.getProperty(Page.PAGE_REG.getValue());
                return page;
            } else {
                User user = (User) optional.get();
                if (service.doService(user).isPresent()) {
                    request.getSession().setAttribute(Attribute.NEW_USER.getValue(), user);
                    page = ConfigurationManager.getProperty(Page.PAGE_REG_TRUE.getValue());
                } else {
                    request.getSession().setAttribute(Attribute.ERROR_REG.getValue(),
                            MessageManager.getProperty(Message.MESSAGE_ERROR_REG.getValue()));
                    page = ConfigurationManager.getProperty(Page.PAGE_REG.getValue());
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while registration was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}