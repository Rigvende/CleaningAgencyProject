package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.CleanerInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ChangeCleanerCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ERROR_CHANGE_CLEANER = "errorChangeCleanerMessage";
    private final static String MESSAGE_ERROR_CHANGE_CLEANER = "message.changeerror";
    private final static String PAGE_CHANGE_CLEANER = "page.changecleaner";
    private final static String PAGE_CONFIRM = "page.confirm";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        CleanerInfoService cleanerInfoService = new CleanerInfoService();
        try {
            Optional<AbstractEntity> optional = cleanerInfoService.createEntity(request);
            if (optional.isEmpty()) {
                request.getSession().setAttribute(ERROR_CHANGE_CLEANER,
                        MessageManager.getProperty(MESSAGE_ERROR_CHANGE_CLEANER));
                page = ConfigurationManager.getProperty(PAGE_CHANGE_CLEANER);
                return page;
            } else {
                Cleaner cleaner = (Cleaner)optional.get();
                if (cleanerInfoService.doService(cleaner).isPresent()) {
                    page = ConfigurationManager.getProperty(PAGE_CONFIRM);
                } else {
                    request.getSession().setAttribute(ERROR_CHANGE_CLEANER,
                            MessageManager.getProperty(MESSAGE_ERROR_CHANGE_CLEANER));
                    page = ConfigurationManager.getProperty(PAGE_CHANGE_CLEANER);
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while changing cleaner info was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}