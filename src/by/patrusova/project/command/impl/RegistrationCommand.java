package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.EntityFactory;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.RegistrationService;
import by.patrusova.project.servlet.SessionRequestContent;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import javax.servlet.http.HttpServletRequest;

public class RegistrationCommand implements ActionCommand {

    public String execute(HttpServletRequest request) throws CommandException {
        String page;
//        SessionRequestContent content = new SessionRequestContent();
//        User user = EntityFactory.createNewUser(content.extractValues(request));//todo через извлеченные данные
        User user = EntityFactory.createNewUser(request);
        try {
            if (RegistrationService.registerUser(user) != null) {
                request.setAttribute("newuser", user);
                page = ConfigurationManager.getProperty("page.registrationtrue");
            } else {
                request.setAttribute("errorRegistrationMessage",
                        MessageManager.getProperty("message.registrationerror"));
                page = ConfigurationManager.getProperty("page.registrationform");
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
//todo убрать в константы строки