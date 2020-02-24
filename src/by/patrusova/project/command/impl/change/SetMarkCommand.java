package by.patrusova.project.command.impl.change;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.OrderInfoService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Message;
import by.patrusova.project.util.stringholder.Page;
import by.patrusova.project.util.stringholder.Parameter;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SetMarkCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        Optional<AbstractEntity> optional;
        String requestId = request.getParameter(Parameter.ID.getValue());
        String requestMark = request.getParameter(Parameter.MARK.getValue());
        try {
            if (!NumberValidator.isValidOrderID(requestId) || !NumberValidator.isValidMark(requestMark)) {
                request.getSession().setAttribute(Attribute.ERROR_CHANGE_ORDER.getValue(),
                        MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_ORDER.getValue()));
                return ConfigurationManager.getProperty(Page.PAGE_ORDERLIST.getValue());
            }
            OrderInfoService service = new OrderInfoService();
            long id = Long.parseLong(requestId);
            int mark = Integer.parseInt(requestMark);
            Client client = (Client) request.getSession().getAttribute(Attribute.CLIENT.getValue());
            long idClient = client.getId();
            optional = service.doService(id, idClient, mark);
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while changing order was processing. ", e);
            throw new CommandException(e);
        }
        if (optional.isEmpty()) {
            request.getSession().setAttribute(Attribute.ERROR_CHANGE_ORDER.getValue(),
                    MessageManager.getProperty(Message.MESSAGE_ERROR_CHANGE_ORDER.getValue()));
            return ConfigurationManager.getProperty(Page.PAGE_ORDERLIST.getValue());
        } else {
            return ConfigurationManager.getProperty(Page.PAGE_CONFIRM.getValue());
        }
    }
}