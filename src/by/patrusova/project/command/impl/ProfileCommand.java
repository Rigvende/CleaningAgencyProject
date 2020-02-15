package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.stringholder.Pages;

import javax.servlet.http.HttpServletRequest;

public class ProfileCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(Pages.PAGE_PROFILE.getValue());
    }
}