package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.DeleteEntityService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.util.stringholder.Parameters;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class DeleteEntityCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String Id = request.getParameter(Parameters.ID.getValue());
        DeleteEntityService entityService = new DeleteEntityService();
        String type = request.getParameter(Parameters.ENTITITYPE.getValue());
        try {
            switch (type) {
                case "admin":
                    if (!NumberValidator.isValidUserID(Id)) {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRMFALSE.getValue());
                    }
                    long id1 = Long.parseLong(Id);
                    User user = new User();
                    user.setId(id1);
                    user.setRole(Attributes.ADMIN.getValue());
                    if (entityService.doService(user) == null) {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
                    } else {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRMFALSE.getValue());
                    }
                case "client":
                    if (!NumberValidator.isValidUserID(Id)) {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRMFALSE.getValue());
                    }
                    long id2 = Long.parseLong(Id);
                    Client client = new Client();
                    client.setIdUser(id2);
                    if (entityService.doService(client) == null) {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
                    } else {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRMFALSE.getValue());
                    }
                case "cleaner":
                    if (!NumberValidator.isValidUserID(Id)) {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRMFALSE.getValue());
                    }
                    long id3 = Long.parseLong(Id);
                    Cleaner cleaner = new Cleaner();
                    cleaner.setIdUser(id3);
                    if (entityService.doService(cleaner) == null) {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
                    } else {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRMFALSE.getValue());
                    }
                case "service":
                    if (!NumberValidator.isValidServiceID(Id)) {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRMFALSE.getValue());
                    }
                    long id4 = Long.parseLong(Id);
                    Service service = new Service();
                    service.setId(id4);
                    if (entityService.doService(service) == null) {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
                    } else {
                        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRMFALSE.getValue());
                    }
            }
        } catch (SQLException | ServiceException | DaoException e) {
            LOGGER.log(Level.ERROR, "Exception while deleting entity has occurred. ", e);
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(Pages.PAGE_CONFIRMFALSE.getValue());
    }
}