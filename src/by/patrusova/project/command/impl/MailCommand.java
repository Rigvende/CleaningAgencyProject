package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.util.AttributesEnum;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MailThread;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Properties;

public class MailCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        try {
            Properties properties = new Properties();
            ServletContext context = request.getServletContext();
            String filename = context.getInitParameter(AttributesEnum.MAIL.getValue());
            properties.load(context.getResourceAsStream(filename));
            MailThread mailOperator = new MailThread(request.getParameter("to"),
                                                     request.getParameter("subject"),
                                                     request.getParameter("body"), properties);
            mailOperator.start();
            page = ConfigurationManager.getProperty(AttributesEnum.PAGE_MAIL.getValue());
        } catch (IOException e) {
            throw new CommandException(e);//todo logger
        }
        return page;
    }
}