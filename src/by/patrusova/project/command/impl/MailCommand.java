package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.MailService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class MailCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String TO = "to";
    private final static String SUBJECT = "subject";
    private final static String BODY = "body";
    private final static String PAGE_SEND = "page.sendmail";
    private final static String ERROR_MAIL = "errorMail";
    private final static String MESSAGE_ERROR_MAIL = "message.mailerror";
    private final static String PAGE_MAIL = "page.mail";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        MailService sender = new MailService();
        try {
            if (sender.doService(request.getParameter(TO),
                                 request.getParameter(SUBJECT),
                                 request.getParameter(BODY))) {
                page = ConfigurationManager.getProperty(PAGE_SEND);
            } else {
                request.getSession().setAttribute(ERROR_MAIL,
                        MessageManager.getProperty(MESSAGE_ERROR_MAIL));
                page = ConfigurationManager.getProperty(PAGE_MAIL);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while sending mail has occurred.", e);
            throw new CommandException(e);
        }
        return page;
    }
}