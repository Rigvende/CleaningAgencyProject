package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Class for command to redirect to change user's info form
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ChangeRedirectCommand implements ActionCommand {

    private final static String PAGE_CHANGE = "page.changeform";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(PAGE_CHANGE);
    }
}