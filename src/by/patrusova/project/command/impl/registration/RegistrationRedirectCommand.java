package by.patrusova.project.command.impl.registration;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class RegistrationRedirectCommand implements ActionCommand {

    private final static String PAGE_REG = "page.registrationform";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(PAGE_REG);
    }
}