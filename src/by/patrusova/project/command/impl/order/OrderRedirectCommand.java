package by.patrusova.project.command.impl.order;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

/**
 * Class for command to redirect to order form
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class OrderRedirectCommand implements ActionCommand {

    private final static String PAGE_PLACE_ORDER = "page.placeorder";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return ConfigurationManager.getProperty(PAGE_PLACE_ORDER);
    }
}