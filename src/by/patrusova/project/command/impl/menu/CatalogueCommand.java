package by.patrusova.project.command.impl.menu;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ShowService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CatalogueCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        String role = (String) request.getSession().getAttribute(Attribute.ROLE.getValue());
        ShowService service = new ShowService();
        try {
            List<AbstractEntity> list = service.doService(Attribute.CATALOGUE.getValue());
            if (list.size() != 0) {
                List<Service> services = new ArrayList<>();
                for (AbstractEntity entity : list) {
                    services.add((Service) entity);
                }
                request.getSession().setAttribute(Attribute.CATALOGUE_LIST.getValue(), services);
                page = ConfigurationManager.getProperty(Page.PAGE_CATALOGUE.getValue());
            } else {
                request.getSession().setAttribute(Attribute.EMPTY_LIST.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_LIST.getValue()));
                switch (role) {
                    case "admin":
                        page = ConfigurationManager.getProperty(Page.PAGE_MAIN_ADMIN.getValue());
                        break;
                    case "client":
                        page = ConfigurationManager.getProperty(Page.PAGE_MAIN_CLIENT.getValue());
                        break;
                    case "cleaner":
                        page = ConfigurationManager.getProperty(Page.PAGE_MAIN_CLEANER.getValue());
                        break;
                    default:
                        page = ConfigurationManager.getProperty(Page.PAGE_LOGIN.getValue());
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while finding catalogue was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}