package by.patrusova.project.command.impl.menu;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Class for command to redirect to user's profile
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ProfileCommand implements ActionCommand {

    private final static String PAGE_PROFILE = "page.profile";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(PAGE_PROFILE);
    }
}