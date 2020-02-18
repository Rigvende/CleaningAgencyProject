package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.RoleService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.util.stringholder.Parameters;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class ChangeGuestCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String Id = request.getParameter(Parameters.ID.getValue());
        if (!NumberValidator.isValidID(Id)){
            request.getSession().setAttribute(Attributes.ERROR_CHANGE_GUEST_ID.getValue(),
                    MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_GUEST_ID.getValue()));
            return ConfigurationManager.getProperty(Pages.PAGE_GUESTLIST.getValue());
        }
        RoleService roleService = new RoleService();
        long id = Long.parseLong(Id);
        String role = request.getParameter(Attributes.ROLE.getValue());
        AbstractEntity entity;
        try {
            entity = roleService.doService(id, role);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while changing role was processing. ", e);
            throw new CommandException(e);
        }
        if (entity == null) {
            request.getSession().setAttribute(Attributes.ERROR_CHANGE_GUEST.getValue(),
                    MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_GUEST.getValue()));
            return ConfigurationManager.getProperty(Pages.PAGE_GUESTLIST.getValue());
        } else {
            User user = (User) entity;
            request.getSession().setAttribute("formerguest", user);
            return ConfigurationManager.getProperty(Pages.PAGE_MAIL.getValue());
        }
    }
}