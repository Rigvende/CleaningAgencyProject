package by.patrusova.project.command;

import by.patrusova.project.command.impl.EmptyCommand;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class for defining type of command
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class CommandProvider {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String COMMAND = "command";
    private final static String WRONG_ACTION = "wrongAction";
    private final static String MESSAGE_WRONG = "message.wrongaction";

    /**
     * Method: define type of command depending on incoming request.
     * @param request is incoming request
     * @return type of {@link ActionCommand}
     */
    public ActionCommand defineCommand(HttpServletRequest request) throws CommandException {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(COMMAND);
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute(WRONG_ACTION, action
                    + MessageManager.getProperty(MESSAGE_WRONG));
            LOGGER.log(Level.ERROR, "Cannot define current command. ", e);
            throw new CommandException(e);
        }
        return current;
    }
}