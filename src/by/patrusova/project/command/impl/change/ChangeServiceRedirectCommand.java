package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ServiceInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 * Class for command to redirect to change service's info form
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ChangeServiceRedirectCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID = "id";
    private final static String SERVICE = "service";
    private final static String PAGE_CHANGE_SERVICE = "page.changeservice";
    private final static String ERROR_CHANGE_SERVICE_ID = "errorChangeServiceIdMessage";
    private final static String MESSAGE_ERROR_CHANGE_SERVICE_ID = "message.changeerrorid";
    private final static String PAGE_CATALOGUELIST = "page.cataloguelist";
    private ServiceInfoService infoService = new ServiceInfoService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String id = request.getParameter(ID);
        try {
            if (NumberValidator.isValidServiceID(id)) {
                Service service = new Service();
                service.setId(Long.parseLong(id));
                service = infoService.getService(service);
                request.getSession().setAttribute(SERVICE, service);
                return ConfigurationManager.getProperty(PAGE_CHANGE_SERVICE);
            } else {
                request.getSession().setAttribute(ERROR_CHANGE_SERVICE_ID,
                        MessageManager.getProperty(MESSAGE_ERROR_CHANGE_SERVICE_ID));
                return ConfigurationManager.getProperty(PAGE_CATALOGUELIST);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while redirecting service was processing. ", e);
            throw new CommandException(e);
        }
    }
}