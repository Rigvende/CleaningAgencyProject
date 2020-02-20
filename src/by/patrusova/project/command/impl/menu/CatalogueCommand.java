package by.patrusova.project.command.impl.menu;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.service.impl.ShowService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogueCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String role = (String) request.getSession().getAttribute(Attributes.ROLE.getValue());
        ShowService service = new ShowService();
        try {
            List<AbstractEntity> list = service.doService(Attributes.CATALOGUE.getValue());
            if (list.size() != 0) {
                List<Service> services = new ArrayList<>();
                for (AbstractEntity entity : list) {
                    services.add((Service) entity);
                }
                request.getSession().setAttribute(Attributes.CATALOGUE_LIST.getValue(), services);
                page = ConfigurationManager.getProperty(Pages.PAGE_CATALOGUE.getValue());
            } else {
                request.getSession().setAttribute(Attributes.EMPTY_LIST.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_LIST.getValue()));
                switch (role) {
                    case "admin":
                        page = ConfigurationManager.getProperty(Pages.PAGE_MAIN_ADMIN.getValue());
                        break;
                    case "client":
                        page = ConfigurationManager.getProperty(Pages.PAGE_MAIN_CLIENT.getValue());
                        break;
                    case "cleaner":
                        page = ConfigurationManager.getProperty(Pages.PAGE_MAIN_CLEANER.getValue());
                        break;
                    default:
                        page = ConfigurationManager.getProperty(Pages.PAGE_LOGIN.getValue());
                }
            }
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while finding catalogue was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}