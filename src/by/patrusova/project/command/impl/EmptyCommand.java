package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Attributes.NULLPAGE.getValue(),
                MessageManager.getProperty(Messages.MESSAGE_EMPTY.getValue()));
        return ConfigurationManager.getProperty(Pages.PAGE_LOGIN.getValue());
    }
}