package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.CleanerInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class ChangeCleanerRedirectCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID = "id";
    private final static String PAGE_CHANGE_CLEANER = "page.changecleaner";
    private final static String ERROR_CHANGE_CLEANER_ID = "errorChangeCleanerIdMessage";
    private final static String MESSAGE_ERROR_CHANGE_CLEANER_ID = "message.changeerrorid";
    private final static String PAGE_CLEANERLIST = "page.cleanerlist";
    private CleanerInfoService service = new CleanerInfoService();


    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String id = request.getParameter(ID);
        try {
            if (NumberValidator.isValidUserID(id)) {
                Cleaner cleaner = new Cleaner();
                cleaner.setIdUser(Long.parseLong(id));
                cleaner = service.getCleaner(cleaner);
                if (cleaner != null) {
                    request.getSession().setAttribute(Role.CLEANER.getValue(), cleaner);
                    return ConfigurationManager.getProperty(PAGE_CHANGE_CLEANER);
                }
            }
            request.getSession().setAttribute(ERROR_CHANGE_CLEANER_ID,
                    MessageManager.getProperty(MESSAGE_ERROR_CHANGE_CLEANER_ID));
            return ConfigurationManager.getProperty(PAGE_CLEANERLIST);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while redirecting cleaner was processing. ", e);
            throw new CommandException(e);
        }
    }
}