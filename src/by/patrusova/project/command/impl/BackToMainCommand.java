package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Class for command to come back to Main page
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class BackToMainCommand implements ActionCommand {

    private final static String ROLE = "role";
    private final static String PAGE_MAIN_ADMIN = "page.mainadmin";
    private final static String PAGE_MAIN_CLIENT = "page.mainclient";
    private final static String PAGE_MAIN_CLEANER = "page.maincleaner";
    private final static String PAGE_LOGIN = "page.login";

    @Override
    public String execute(HttpServletRequest request) {
        String role = (String) request.getSession().getAttribute(ROLE);
        String page;
        if(role != null) {
            switch (role) {
                case "admin":
                    page = ConfigurationManager.getProperty(PAGE_MAIN_ADMIN);
                    break;
                case "client":
                    page = ConfigurationManager.getProperty(PAGE_MAIN_CLIENT);
                    break;
                case "cleaner":
                    page = ConfigurationManager.getProperty(PAGE_MAIN_CLEANER);
                    break;
                case "guest":
                default:
                    page = ConfigurationManager.getProperty(PAGE_LOGIN);
                    break;
            }
        } else {
            page = ConfigurationManager.getProperty(PAGE_LOGIN);
        }
        return page;
    }
}