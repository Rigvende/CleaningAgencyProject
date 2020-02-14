package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.UserService;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RegistrationCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        UserService service = new UserService();
        User user = (User)service.createEntity(request);
        if (user == null) {
            request.getSession().setAttribute(Attributes.ERROR_REG.getValue(),
                    MessageManager.getProperty(Attributes.MESSAGE_ERROR_REG.getValue()));
            page = ConfigurationManager.getProperty(Attributes.PAGE_REG.getValue());
            return page;
        } else {
            try {
                if (service.doService(user) != null) {
                    request.getSession().setAttribute(Attributes.NEW_USER.getValue(), user);
                    page = ConfigurationManager.getProperty(Attributes.PAGE_REG_TRUE.getValue());
                } else {
                    request.getSession().setAttribute(Attributes.ERROR_REG.getValue(),
                            MessageManager.getProperty(Attributes.MESSAGE_ERROR_REG.getValue()));
                    page = ConfigurationManager.getProperty(Attributes.PAGE_REG.getValue());
                }
            } catch (ServiceException | SQLException e) {
                LOGGER.log(Level.ERROR, "Exception while registration has occurred.");
                throw new CommandException(e);
            }
        }
        return page;
    }
}