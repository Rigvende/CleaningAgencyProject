package by.patrusova.project.command.impl.show;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ShowService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ShowCleanersCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String CLEANER_LIST = "cleanerList";
    private final static String PAGE_CLEANERLIST = "page.cleanerlist";
    private final static String EMPTY_LIST = "emptyList";
    private final static String MESSAGE_ERROR_LIST = "message.listerror";
    private final static String PAGE_MAIN_ADMIN = "page.mainadmin";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        ShowService service = new ShowService();
        try {
            List<AbstractEntity> list = service.doService(Role.CLEANER.getValue());
            if (!list.isEmpty()) {
                List<User> users = new ArrayList<>();
                for (AbstractEntity entity : list) {
                    users.add((User) entity);
                }
                request.getSession().setAttribute(CLEANER_LIST, users);
                page = ConfigurationManager.getProperty(PAGE_CLEANERLIST);
            } else {
                request.getSession().setAttribute(EMPTY_LIST,
                        MessageManager.getProperty(MESSAGE_ERROR_LIST));
                page = ConfigurationManager.getProperty(PAGE_MAIN_ADMIN);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while finding cleaners was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}