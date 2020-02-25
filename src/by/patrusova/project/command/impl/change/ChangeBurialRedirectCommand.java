package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class ChangeBurialRedirectCommand implements ActionCommand {

    private final static String PAGE_CHANGE_BURIAL = "page.changeburialform";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(PAGE_CHANGE_BURIAL);
    }
}