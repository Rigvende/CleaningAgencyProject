package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ChangeBurialCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page;
//        (if ) //todo проверку
       // Client client = ClientService.createNewClient(request);
//        if (client == null) {
//            request.setAttribute(AttributesEnum.ERROR_CHANGE.getValue(),
//                    MessageManager.getProperty(AttributesEnum.MESSAGE_ERROR_CHANGE.getValue()));
//        }
//            page = ConfigurationManager.getProperty(AttributesEnum.PAGE_PROFILE.getValue());
        page = ConfigurationManager.getProperty(Attributes.PAGE_LOGIN.getValue());
        return page;
    }
}