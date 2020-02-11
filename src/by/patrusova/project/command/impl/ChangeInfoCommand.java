package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.RegistrationService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import javax.servlet.http.HttpServletRequest;

public class ChangeInfoCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        User user = RegistrationService.createNewUser(request);
        if (user == null) {//todo validation не проходит Т_Т
            request.setAttribute("errorRegistrationMessage",
                    MessageManager.getProperty("message.registrationerror"));
            page = ConfigurationManager.getProperty("page.registrationform");
            return page;
        }
        try {
            if (RegistrationService.registerUser(user) != null) {//todo changeservice для внесения изменений в бд
                page = ConfigurationManager.getProperty("page.profile");
            } else {
                request.setAttribute("errorChangeMessage",
                        MessageManager.getProperty("message.changeerror"));
                page = ConfigurationManager.getProperty("page.changeform");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}