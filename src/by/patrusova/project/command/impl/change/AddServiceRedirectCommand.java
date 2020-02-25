package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class AddServiceRedirectCommand implements ActionCommand {

    private final static String PAGE_ADD_SERVICE = "page.addservice";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(PAGE_ADD_SERVICE);
    }
}