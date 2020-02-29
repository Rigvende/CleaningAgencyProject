package by.patrusova.project.command.impl.registration;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Class for command to redirect to registration form
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class RegistrationRedirectCommand implements ActionCommand {

    private final static String PAGE_REG = "page.registrationform";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(PAGE_REG);
    }
}