package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.AttributesEnum;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(AttributesEnum.NULLPAGE.getValue(),
                MessageManager.getProperty(AttributesEnum.MESSAGE_EMPTY.getValue()));
        return ConfigurationManager.getProperty(AttributesEnum.PAGE_LOGIN.getValue());
    }
}