package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.util.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class ChangeRedirectCommand implements ActionCommand {

        @Override
        public String execute(HttpServletRequest request) {
            return ConfigurationManager.getProperty("page.changeform");
        }
    }