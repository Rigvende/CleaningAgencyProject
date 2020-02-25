package by.patrusova.project.command.impl.menu;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class InfoCommand implements ActionCommand {

    private final static String PAGE_INFO = "page.info";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(PAGE_INFO);
    }
}