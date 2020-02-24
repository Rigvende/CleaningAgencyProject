package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.MailService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import by.patrusova.project.util.stringholder.Parameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class MailCommand implements ActionCommand {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        MailService sender = new MailService();
        try {
            if (sender.doService(request.getParameter(Parameter.TO.getValue()),
                    request.getParameter(Parameter.SUBJECT.getValue()),
                    request.getParameter(Parameter.BODY.getValue()))) {
                page = ConfigurationManager.getProperty(Page.PAGE_SEND.getValue());
            } else {
                request.getSession().setAttribute(Attribute.ERROR_MAIL.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_MAIL.getValue()));
                page = ConfigurationManager.getProperty(Page.PAGE_MAIL.getValue());
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while sending mail has occurred.", e);
            throw new CommandException(e);
        }
        return page;
    }
}