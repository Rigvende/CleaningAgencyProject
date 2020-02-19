package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.CleanerInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.validator.NumberValidator;
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
        String id = request.getParameter(Attributes.ID.getValue());
        if (NumberValidator.isValidID(id)) {
            cleaner.setIdUser(Long.parseLong(id));
            try {
                cleaner = service.getCleaner(cleaner);
                request.getSession().setAttribute(Attributes.CLEANER.getValue(), cleaner);
            } catch (ServiceException e) {
                LOGGER.log(Level.ERROR, "Exception has occurred while redirecting cleaner was processing. ", e);
                throw new CommandException(e);
            }
            return ConfigurationManager.getProperty(Pages.PAGE_CHANGE_CLEANER.getValue());
        } else {
            request.getSession().setAttribute(Attributes.ERROR_CHANGE_CLEANER_ID.getValue(),
                    MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_CLEANER_ID.getValue()));
            return ConfigurationManager.getProperty(Pages.PAGE_CLEANERLIST.getValue());
        }
    }
}