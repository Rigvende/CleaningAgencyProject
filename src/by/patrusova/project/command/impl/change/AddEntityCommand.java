package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ServiceInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class AddEntityCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        ServiceInfoService infoService = new ServiceInfoService();
        try {
            Service service = infoService.createNewEntity(request);
            if (service == null) {
                request.getSession().setAttribute(Attributes.ERROR_ADD_SERVICE.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_ADD_SERVICE.getValue()));
                page = ConfigurationManager.getProperty(Pages.PAGE_ADD_SERVICE.getValue());
                return page;
            } else {
                service = infoService.doServiceAdd(service);
                if (service != null) {
                    page = ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
                } else {
                    request.getSession().setAttribute(Attributes.ERROR_ADD_SERVICE.getValue(),
                            MessageManager.getProperty(Messages.MESSAGE_ERROR_ADD_SERVICE.getValue()));
                    page = ConfigurationManager.getProperty(Pages.PAGE_ADD_SERVICE.getValue());
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while adding service was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}