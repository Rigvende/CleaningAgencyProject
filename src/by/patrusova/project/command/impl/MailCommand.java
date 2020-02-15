package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MailThread;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.util.stringholder.Parameters;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

public class MailCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            Properties properties = new Properties();
            ServletContext context = request.getServletContext();
            String filename = context.getInitParameter(Attributes.MAIL.getValue());
            properties.load(context.getResourceAsStream(filename));
            MailThread mailOperator
                    = new MailThread(request.getParameter(Parameters.TO.getValue()),
                                     request.getParameter(Parameters.SUBJECT.getValue()),
                                     request.getParameter(Parameters.BODY.getValue()), properties);
            mailOperator.start();
            page = ConfigurationManager.getProperty(Pages.PAGE_MAIL.getValue());
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Error has occurred while sending mail.");
            throw new CommandException(e);
        }
        return page;
    }
}