package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Attributes.NULLPAGE.getValue(),
                MessageManager.getProperty(Attributes.MESSAGE_EMPTY.getValue()));
        return ConfigurationManager.getProperty(Attributes.PAGE_LOGIN.getValue());
    }
}