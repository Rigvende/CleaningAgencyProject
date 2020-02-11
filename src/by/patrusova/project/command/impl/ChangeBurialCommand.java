package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import javax.servlet.http.HttpServletRequest;

public class ChangeBurialCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return null;//todo расписать команду по аналогии с changeinfocommand
    }
}