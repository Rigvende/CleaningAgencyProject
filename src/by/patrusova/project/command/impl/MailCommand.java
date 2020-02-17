package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.service.impl.MailService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.util.stringholder.Parameters;
import javax.servlet.http.HttpServletRequest;

public class MailCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        MailService sender = new MailService();
        if (sender.doService(request.getParameter(Parameters.TO.getValue()),
                    request.getParameter(Parameters.SUBJECT.getValue()),
                    request.getParameter(Parameters.BODY.getValue()))) {
            page = ConfigurationManager.getProperty(Pages.PAGE_SEND.getValue());
        } else {
            request.getSession().setAttribute(Attributes.ERROR_MAIL.getValue(),
                    MessageManager.getProperty(Messages.MESSAGE_ERROR_MAIL.getValue()));
            page = ConfigurationManager.getProperty(Pages.PAGE_MAIL.getValue());
        }
        return page;
    }
}