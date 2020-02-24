package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ClientInfoService;
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

public class ChangeBurialCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        ClientInfoService clientInfoService = new ClientInfoService();
        try {
            Optional<AbstractEntity> opt = clientInfoService.createEntity(request);
            if (opt.isEmpty()) {
                request.getSession().setAttribute(Attribute.ERROR_CHANGE_BURIAL.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_BURIAL.getValue()));
                page = ConfigurationManager.getProperty(Page.PAGE_CHANGE_BURIAL.getValue());
                return page;
            } else {
                Client client = (Client)opt.get();
                Optional<AbstractEntity> optional = clientInfoService.doService(client);
                if (optional.isPresent()) {
                    page = ConfigurationManager.getProperty(Page.PAGE_PROFILE.getValue());
                } else {
                    request.getSession().setAttribute(Attribute.ERROR_CHANGE_BURIAL.getValue(),
                            MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_BURIAL.getValue()));
                    page = ConfigurationManager.getProperty(Page.PAGE_CHANGE_BURIAL.getValue());
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while changing client info was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}