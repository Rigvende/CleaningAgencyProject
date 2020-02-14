package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeInfoCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
//        User user = UserService.createNewUser(request);
//        if (user == null) {
//            request.setAttribute(AttributesEnum.ERROR_REG.getValue(),
//                    MessageManager.getProperty(AttributesEnum.MESSAGE_ERROR_REG.getValue()));
//            page = ConfigurationManager.getProperty(AttributesEnum.PAGE_REG.getValue());
//            return page;
//        }
//        try {
//            if (UserService.registerUser(user) != null) {//todo changeservice для внесения изменений в бд
//                page = ConfigurationManager.getProperty(AttributesEnum.PAGE_PROFILE.getValue());
//            } else {
//                request.setAttribute(AttributesEnum.ERROR_CHANGE.getValue(),
//                        MessageManager.getProperty(AttributesEnum.MESSAGE_ERROR_CHANGE.getValue()));
//                page = ConfigurationManager.getProperty(AttributesEnum.PAGE_CHANGE.getValue());
//            }
//        } catch (ServiceException | SQLException e) {
//            throw new CommandException(e);//todo logger
//        }
        page = ConfigurationManager.getProperty(Attributes.PAGE_LOGIN.getValue());
        return page;
    }
}