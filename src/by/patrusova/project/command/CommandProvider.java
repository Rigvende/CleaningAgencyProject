package by.patrusova.project.command;

import by.patrusova.project.command.impl.EmptyCommand;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Parameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class CommandProvider {

    private final static Logger LOGGER = LogManager.getLogger();

    public ActionCommand defineCommand(HttpServletRequest request) throws CommandException {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter(Parameter.COMMAND.getValue());
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute(Attribute.WRONG_ACTION.getValue(), action
                    + MessageManager.getProperty(Message.MESSAGE_WRONG.getValue()));
            LOGGER.log(Level.ERROR, "Cannot define current command. ", e);
            throw new CommandException(e);
        }
        return current;
    }
}