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

/**
 * Class for command to show admins list for admin
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ShowAdminsCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ADMIN_LIST = "adminList";
    private final static String PAGE_ADMINLIST = "page.adminlist";
    private final static String EMPTY_LIST = "emptyList";
    private final static String MESSAGE_ERROR_LIST = "message.listerror";
    private final static String PAGE_MAIN_ADMIN = "page.mainadmin";
    private ShowService service = new ShowService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            List<AbstractEntity> list = service.doService(Role.ADMIN.getValue());
            if (!list.isEmpty()) {
                List<User> users = new ArrayList<>();
                for (AbstractEntity entity : list) {
                    users.add((User) entity);
                }
                request.getSession().setAttribute(ADMIN_LIST, users);
                return ConfigurationManager.getProperty(PAGE_ADMINLIST);
            } else {
                request.getSession().setAttribute(EMPTY_LIST,
                        MessageManager.getProperty(MESSAGE_ERROR_LIST));
                return ConfigurationManager.getProperty(PAGE_MAIN_ADMIN);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while finding admins was processing. ", e);
            throw new CommandException(e);
        }
    }
}