package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ServiceInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ChangeServiceRedirectCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        ServiceInfoService infoService = new ServiceInfoService();
        Service service = new Service();
        String id = request.getParameter(Attributes.ID.getValue());
        try {
            if (NumberValidator.isValidServiceID(id)) {
                service.setId(Long.parseLong(id));
                service = infoService.getService(service);
                request.getSession().setAttribute(Attributes.SERVICE.getValue(), service);
                return ConfigurationManager.getProperty(Pages.PAGE_CHANGE_SERVICE.getValue());
            } else {
                request.getSession().setAttribute(Attributes.ERROR_CHANGE_SERVICE_ID.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_SERVICE_ID.getValue()));
                return ConfigurationManager.getProperty(Pages.PAGE_CATALOGUELIST.getValue());
            }
        } catch (ServiceException | DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while redirecting service was processing. ", e);
            throw new CommandException(e);
        }
    }
}
