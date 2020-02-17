package by.patrusova.project.command.impl;

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
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class ChangeGuestCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        RoleService roleService = new RoleService();
        long id = Long.parseLong(request.getParameter(Attributes.ID.getValue()));
        String role = request.getParameter(Attributes.ROLE.getValue());
        AbstractEntity entity;
        try {
            entity = roleService.doService(id, role);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while changing role was processing.");
            throw new CommandException(e);
        }
        if (entity == null) {
            request.getSession().setAttribute(Attributes.ERROR_CHANGE.getValue(),
                    MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE.getValue()));
            return ConfigurationManager.getProperty(Pages.PAGE_MAIN_ADMIN.getValue());
        } else {
            User user = (User) entity;
            request.getSession().setAttribute("formerguest", user);
            return ConfigurationManager.getProperty(Pages.PAGE_MAIL.getValue());
        }
    }
}