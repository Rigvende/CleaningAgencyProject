package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.RoleService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.util.stringholder.Parameters;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Optional;

public class ChangeGuestCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String Id = request.getParameter(Parameters.ID.getValue());
        String role = request.getParameter(Attributes.ROLE.getValue());
        try {
            if (!NumberValidator.isValidUserID(Id) || !StringValidator.isValidRole(role)){
                request.getSession().setAttribute(Attributes.ERROR_CHANGE_GUEST.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_GUEST.getValue()));
                return ConfigurationManager.getProperty(Pages.PAGE_GUESTLIST.getValue());
            }
        } catch (DaoException | SQLException e) {
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
            request.getSession().setAttribute(Attributes.ERROR_CHANGE_GUEST.getValue(),
                    MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_GUEST.getValue()));
            return ConfigurationManager.getProperty(Pages.PAGE_GUESTLIST.getValue());
        } else {
            User user = (User) optional.get();
            request.getSession().setAttribute(Attributes.FORMERGUEST.getValue(), user);
            return ConfigurationManager.getProperty(Pages.PAGE_MAIL.getValue());
        }
    }
}