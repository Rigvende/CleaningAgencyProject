package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import by.patrusova.project.util.stringholder.Pages;
import by.patrusova.project.util.stringholder.Parameters;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class SetMarkCommand implements ActionCommand {
    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        AbstractEntity entity;
        String requestId = request.getParameter(Parameters.ID.getValue());
        String requestMark = request.getParameter(Parameters.MARK.getValue());
        try {
            if (!NumberValidator.isValidOrderID(requestId) || !NumberValidator.isValidMark(requestMark)) {
                request.getSession().setAttribute(Attributes.ERROR_CHANGE_ORDER.getValue(),
                        MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_ORDER.getValue()));
                return ConfigurationManager.getProperty(Pages.PAGE_ORDERLIST.getValue());
            }
            OrderInfoService service = new OrderInfoService();
            long id = Long.parseLong(requestId);
            int mark = Integer.parseInt(requestMark);
            Client client = (Client) request.getSession().getAttribute(Attributes.CLIENT.getValue());
            long idClient = client.getId();
            entity = service.doService(id, idClient, mark);
        } catch (ServiceException | DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while changing order was processing. ", e);
            throw new CommandException(e);
        }
        if (entity == null) {
            request.getSession().setAttribute(Attributes.ERROR_CHANGE_ORDER.getValue(),
                    MessageManager.getProperty(Messages.MESSAGE_ERROR_CHANGE_ORDER.getValue()));
            return ConfigurationManager.getProperty(Pages.PAGE_ORDERLIST.getValue());
        } else {
            return ConfigurationManager.getProperty(Pages.PAGE_CONFIRM.getValue());
        }
    }
}