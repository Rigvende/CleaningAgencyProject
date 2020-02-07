package by.patrusova.project.command;

import by.patrusova.project.command.impl.EmptyCommand;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

public class CommandProvider {

    private final static Logger LOGGER = LogManager.getLogger();

    public ActionCommand defineCommand(HttpServletRequest request) throws CommandException {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action
                    + MessageManager.getProperty("message.wrongaction"));
            LOGGER.log(Level.ERROR, "Cannot define current command.");
            throw new CommandException(e); //todo нужно ли бросать CommandException
        }
        return current;
    }

//    public static Optional<ActionCommand> defineCommand(String commandName)
//            throws CommandException {
//        Optional<ActionCommand> current;
//        if (commandName == null || commandName.isBlank()) {
//            return Optional.empty();
//        }
//        try {
//            CommandEnum type = CommandEnum.valueOf(commandName.toUpperCase());
//            current = Optional.of(type.getCurrentCommand());
//        } catch (IllegalArgumentException e) {
//            LOGGER.log(Level.ERROR, "Cannot find command.");
//            throw new CommandException(e);
//        }
//        return current;
//    }
//
//    public static Optional<ActionCommand> takeCommand(String commandName) {
//        return Arrays.stream(CommandEnum.values())
//                .filter(o -> o.name().equalsIgnoreCase(commandName))
//                .map(CommandEnum::getCurrentCommand)
//                .findAny();
//    }
}