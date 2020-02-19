package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.stringholder.Pages;
import javax.servlet.http.HttpServletRequest;

public class AddServiceRedirectCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(Pages.PAGE_ADD_SERVICE.getValue());
    }
}