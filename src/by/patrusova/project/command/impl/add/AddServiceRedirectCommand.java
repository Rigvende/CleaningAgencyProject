package by.patrusova.project.command.impl.add;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Class for command to redirect to the Add service page
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class AddServiceRedirectCommand implements ActionCommand {

    private final static String PAGE_ADD_SERVICE = "page.addservice";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(PAGE_ADD_SERVICE);
    }
}