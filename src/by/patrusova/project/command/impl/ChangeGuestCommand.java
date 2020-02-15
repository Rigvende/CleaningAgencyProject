package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.RoleService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import javax.servlet.http.HttpServletRequest;

public class ChangeGuestCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        RoleService roleService = new RoleService();
        String role = request.getParameter(Attributes.ROLE.getValue());
        User user = (User)request.getAttribute(Attributes.USER.getValue());
        user.setRole(role);
        try {
            if (roleService.doService(user) != null) {
                request.getSession().setAttribute(Attributes.CLIENT.getValue(), user);

            } else  {
                request.setAttribute(Attributes.ERROR_LOGIN.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_LOGIN.getValue()));
                page = ConfigurationManager.getProperty(Pages.PAGE_MAIN_ADMIN.getValue());
            }
        } catch (ServiceException e) {
            throw new CommandException(e);//todo logger
        }
        return page;
    }
}
