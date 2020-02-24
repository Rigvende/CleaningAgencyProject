package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.CleanerInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
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
        String id = request.getParameter(Attribute.ID.getValue());
        try {
            if (NumberValidator.isValidUserID(id)) {
                cleaner.setIdUser(Long.parseLong(id));
                cleaner = service.getCleaner(cleaner);
                if (cleaner != null) {
                    request.getSession().setAttribute(Attribute.CLEANER.getValue(), cleaner);
                    return ConfigurationManager.getProperty(Page.PAGE_CHANGE_CLEANER.getValue());
                } else {
                    request.getSession().setAttribute(Attribute.ERROR_CHANGE_CLEANER_ID.getValue(),
                            MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_CLEANER_ID.getValue()));
                    return ConfigurationManager.getProperty(Page.PAGE_CLEANERLIST.getValue());
                }
            } else {
                request.getSession().setAttribute(Attribute.ERROR_CHANGE_CLEANER_ID.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_CLEANER_ID.getValue()));
                return ConfigurationManager.getProperty(Page.PAGE_CLEANERLIST.getValue());
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while redirecting cleaner was processing. ", e);
            throw new CommandException(e);
        }
    }
}