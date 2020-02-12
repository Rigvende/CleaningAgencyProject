package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.RegistrationService;
import by.patrusova.project.util.AttributesEnum;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RegistrationCommand implements ActionCommand {

    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        User user = RegistrationService.createNewUser(request);
        if (user == null) {
            request.setAttribute(AttributesEnum.ERROR_REG.getValue(),
                    MessageManager.getProperty(AttributesEnum.MESSAGE_ERROR_REG.getValue()));
            page = ConfigurationManager.getProperty(AttributesEnum.PAGE_REG.getValue());
            return page;
        } else {
            try {
                if (RegistrationService.registerUser(user) != null) {
                    request.setAttribute(AttributesEnum.NEW_USER.getValue(), user);
                    page = ConfigurationManager.getProperty(AttributesEnum.PAGE_REG_TRUE.getValue());
                } else {
                    request.setAttribute(AttributesEnum.ERROR_REG.getValue(),
                            MessageManager.getProperty(AttributesEnum.MESSAGE_ERROR_REG.getValue()));
                    page = ConfigurationManager.getProperty(AttributesEnum.PAGE_REG.getValue());
                }
            } catch (ServiceException | SQLException e) {
                throw new CommandException(e);//todo logger
            }
        }
        return page;
    }
}