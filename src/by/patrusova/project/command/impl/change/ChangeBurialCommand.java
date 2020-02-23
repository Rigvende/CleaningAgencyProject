package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ClientInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
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
                request.getSession().setAttribute(Attributes.ERROR_CHANGE_BURIAL.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_BURIAL.getValue()));
                page = ConfigurationManager.getProperty(Pages.PAGE_CHANGE_BURIAL.getValue());
                return page;
            } else {
                Client client = (Client)opt.get();
                Optional<AbstractEntity> optional = clientInfoService.doService(client);
                if (optional.isPresent()) {
                    page = ConfigurationManager.getProperty(Pages.PAGE_PROFILE.getValue());
                } else {
                    request.getSession().setAttribute(Attributes.ERROR_CHANGE_BURIAL.getValue(),
                            MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_BURIAL.getValue()));
                    page = ConfigurationManager.getProperty(Pages.PAGE_CHANGE_BURIAL.getValue());
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while changing client info was processing. ", e);
            throw new CommandException(e);
        }
        return page;
    }
}