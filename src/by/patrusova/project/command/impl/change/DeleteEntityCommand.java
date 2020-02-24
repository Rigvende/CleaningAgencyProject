package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.DeleteEntityService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Page;
import by.patrusova.project.util.stringholder.Parameter;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class DeleteEntityCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String Id = request.getParameter(Parameter.ID.getValue());
        DeleteEntityService entityService = new DeleteEntityService();
        String type = request.getParameter(Parameter.ENTITITYPE.getValue());
        try {
            switch (type) {
                case "admin":
                    if (!NumberValidator.isValidUserID(Id)) {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRMFALSE.getValue());
                    }
                    long id1 = Long.parseLong(Id);
                    User user = new User();
                    user.setId(id1);
                    user.setRole(Attribute.ADMIN.getValue());
                    if (entityService.doService(user).isEmpty()) {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRM.getValue());
                    } else {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRMFALSE.getValue());
                    }
                case "client":
                    if (!NumberValidator.isValidUserID(Id)) {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRMFALSE.getValue());
                    }
                    long id2 = Long.parseLong(Id);
                    Client client = new Client();
                    client.setIdUser(id2);
                    if (entityService.doService(client).isEmpty()) {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRM.getValue());
                    } else {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRMFALSE.getValue());
                    }
                case "cleaner":
                    if (!NumberValidator.isValidUserID(Id)) {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRMFALSE.getValue());
                    }
                    long id3 = Long.parseLong(Id);
                    Cleaner cleaner = new Cleaner();
                    cleaner.setIdUser(id3);
                    if (entityService.doService(cleaner).isEmpty()) {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRM.getValue());
                    } else {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRMFALSE.getValue());
                    }
                case "service":
                    if (!NumberValidator.isValidServiceID(Id)) {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRMFALSE.getValue());
                    }
                    long id4 = Long.parseLong(Id);
                    Service service = new Service();
                    service.setId(id4);
                    if (entityService.doService(service).isEmpty()) {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRM.getValue());
                    } else {
                        return ConfigurationManager.getProperty(Page.PAGE_CONFIRMFALSE.getValue());
                    }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while deleting entity has occurred. ", e);
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(Page.PAGE_CONFIRMFALSE.getValue());
    }
}