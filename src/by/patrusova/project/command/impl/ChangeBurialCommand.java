package by.patrusova.project.command.impl;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.ClientService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

public class ChangeBurialCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        ClientService clientService = new ClientService();
        try {
            Client client = clientService.createEntity(request);
            if (client == null) {
                request.getSession().setAttribute(Attributes.ERROR_CHANGE.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE.getValue()));
                page = ConfigurationManager.getProperty(Pages.PAGE_CHANGE_BURIAL.getValue());
                return page;
            } else {
                client = clientService.doService(client);
                if (client != null) {
                    page = ConfigurationManager.getProperty(Pages.PAGE_PROFILE.getValue());
                } else {
                    request.getSession().setAttribute(Attributes.ERROR_CHANGE.getValue(),
                            MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE.getValue()));
                    page = ConfigurationManager.getProperty(Pages.PAGE_CHANGE_BURIAL.getValue());
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while changing client info was processing.");
            throw new CommandException(e);
        }
        return page;
    }
}