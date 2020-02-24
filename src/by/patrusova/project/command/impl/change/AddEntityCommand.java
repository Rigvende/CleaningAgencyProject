package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ServiceInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AddEntityCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        ServiceInfoService infoService = new ServiceInfoService();
        try {
            Optional<AbstractEntity> optional = infoService.createNewEntity(request);
            if (optional.isEmpty()) {
                request.getSession().setAttribute(Attribute.ERROR_ADD_SERVICE.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_ADD_SERVICE.getValue()));
                page = ConfigurationManager.getProperty(Page.PAGE_ADD_SERVICE.getValue());
                return page;
            } else {
                Service service = (Service)optional.get();
                Optional<AbstractEntity> opt = infoService.doServiceAdd(service);
                if (opt.isPresent()) {
                    page = ConfigurationManager.getProperty(Page.PAGE_CONFIRM.getValue());
                } else {
                    request.getSession().setAttribute(Attribute.ERROR_ADD_SERVICE.getValue(),
                            MessageManager.getProperty(Message.MESSAGE_ERROR_ADD_SERVICE.getValue()));
                    page = ConfigurationManager.getProperty(Page.PAGE_ADD_SERVICE.getValue());
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while adding service was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}