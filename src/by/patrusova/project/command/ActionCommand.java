package by.patrusova.project.command;

import by.patrusova.project.exception.CommandException;
import javax.servlet.http.HttpServletRequest;

/**
 * Basic interface for all commands.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public interface ActionCommand {

    /**
     * Method: fulfills any incoming request depending on command type.
     * @param request is incoming request
     */
    String execute(HttpServletRequest request) throws CommandException;
}