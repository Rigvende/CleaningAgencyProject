package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.AttributesEnum;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class BasketCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {

        return ConfigurationManager.getProperty(AttributesEnum.PAGE_LOGIN.getValue());
    }
}