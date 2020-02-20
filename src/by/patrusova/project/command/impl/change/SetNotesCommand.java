package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.exception.CommandException;
import javax.servlet.http.HttpServletRequest;

public class SetNotesCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}