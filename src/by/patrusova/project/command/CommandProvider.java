package by.patrusova.project.command;

import by.patrusova.project.command.impl.EmptyCommand;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class CommandProvider {

    private final static Logger LOGGER = LogManager.getLogger();

    public ActionCommand defineCommand(HttpServletRequest request) throws CommandException {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");//fixme
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute(Attributes.WRONG_ACTION.getValue(), action
                    + MessageManager.getProperty(Attributes.MESSAGE_WRONG.getValue()));
            LOGGER.log(Level.ERROR, "Cannot define current command.");
            throw new CommandException(e);
        }
        return current;
    }
}