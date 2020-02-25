package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {

    private final static String NULLPAGE = "nullpage";
    private final static String MESSAGE_EMPTY = "message.nullpage";
    private final static String PAGE_LOGIN = "page.login";

    @Override
    public String execute(HttpServletRequest request) {
        request.setAttribute(NULLPAGE,
                MessageManager.getProperty(MESSAGE_EMPTY));
        return ConfigurationManager.getProperty(PAGE_LOGIN);
    }
}