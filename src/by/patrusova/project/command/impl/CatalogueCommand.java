package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class CatalogueCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(Attributes.PAGE_LOGIN.getValue());
    }
}