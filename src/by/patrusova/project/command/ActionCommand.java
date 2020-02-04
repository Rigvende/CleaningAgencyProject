package by.patrusova.project.command;

import by.patrusova.project.exception.CommandException;
import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {

    String execute(HttpServletRequest request) throws CommandException;
}