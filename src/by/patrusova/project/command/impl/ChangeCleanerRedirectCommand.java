package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.CleanerInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Pages;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class ChangeCleanerRedirectCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        CleanerInfoService service = new CleanerInfoService();
        Cleaner cleaner = new Cleaner();
        cleaner.setIdUser(Long.parseLong(request.getParameter(Attributes.ID.getValue())));
        try {
            cleaner = service.getCleaner(cleaner);
            request.getSession().setAttribute(Attributes.CLEANER.getValue(), cleaner);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while redirecting cleaner was processing. ", e);
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(Pages.PAGE_CHANGE_CLEANER.getValue());
    }
}