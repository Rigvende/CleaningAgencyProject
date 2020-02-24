package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(Attribute.NULLPAGE.getValue(),
                MessageManager.getProperty(Message.MESSAGE_EMPTY.getValue()));
        return ConfigurationManager.getProperty(Page.PAGE_LOGIN.getValue());
    }
}