package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.DeleteEntityService;
import by.patrusova.project.service.impl.MailService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.util.stringholder.Parameters;
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
        long id = Long.parseLong(request.getParameter(Parameters.ID.getValue()));
        DeleteEntityService entityService = new DeleteEntityService();
        String type = request.getParameter(Parameters.ENTITITYPE.getValue());
        try {
            switch (type) {
                case "admin":
                    User user = new User();
                    user.setId(id);
                    if (entityService.doService(user) == null) {
                        page = ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
                        return page;
                    }
                case "client":
                    Client client = new Client();
                    client.setIdUser(id);
                    if (entityService.doService(client) == null) {
                        page = ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
                        return page;
                    }
                case "cleaner":
                    Cleaner cleaner = new Cleaner();
                    cleaner.setIdUser(id);
                    if (entityService.doService(cleaner) == null) {
                        page = ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
                        return page;
                    }
                case "service":
                    Service service = new Service();
                    service.setId(id);
                    if (entityService.doService(service) == null) {
                        page = ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
                        return page;
                    }
            }
        } catch (SQLException | ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while deleting entity has occurred. ", e);
            throw new CommandException(e);
        }
        page = ConfigurationManager.getProperty(Pages.PAGE_CONFIRMFALSE.getValue());
        return page;
    }
}