package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class BackToCatalogueCommand implements ActionCommand {

    private final static String PAGE_CATALOGUE = "page.catalogue";

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(PAGE_CATALOGUE);
    }
}