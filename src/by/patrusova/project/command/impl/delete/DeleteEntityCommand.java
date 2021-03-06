package by.patrusova.project.command.impl.delete;

import by.patrusova.project.command.ActionCommand;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.*;
import by.patrusova.project.exception.CommandException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.BasketService;
import by.patrusova.project.service.impl.DeleteEntityService;
import by.patrusova.project.util.ConfigurationManager;
import by.patrusova.project.validator.NumberValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 * Class for command to delete type of {@link by.patrusova.project.entity.AbstractEntity} object
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class DeleteEntityCommand implements ActionCommand {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ORDER_NEW = "orderNew";
    private final static String ID = "id";
    private final static String ENTITITYPE = "entitytype";
    private final static String PAGE_CONFIRMFALSE = "page.confirmfalse";
    private final static String PAGE_CONFIRM = "page.confirm";
    private DeleteEntityService entityService = new DeleteEntityService();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String idEntity = request.getParameter(ID);
        String type = request.getParameter(ENTITITYPE);
        try {
            switch (type) {
                case "admin":
                    if (!NumberValidator.isValidUserID(idEntity)) {
                        return ConfigurationManager.getProperty(PAGE_CONFIRMFALSE);
                    }
                    long id1 = Long.parseLong(idEntity);
                    User user = new User();
                    user.setId(id1);
                    user.setRole(Role.ADMIN.getValue());
                    return entityService.doService(user).isEmpty() ?
                            ConfigurationManager.getProperty(PAGE_CONFIRM) :
                            ConfigurationManager.getProperty(PAGE_CONFIRMFALSE);
                case "client":
                    if (!NumberValidator.isValidUserID(idEntity)) {
                        return ConfigurationManager.getProperty(PAGE_CONFIRMFALSE);
                    }
                    long id2 = Long.parseLong(idEntity);
                    Client client = new Client();
                    client.setIdUser(id2);
                    return entityService.doService(client).isEmpty() ?
                            ConfigurationManager.getProperty(PAGE_CONFIRM) :
                            ConfigurationManager.getProperty(PAGE_CONFIRMFALSE);
                case "cleaner":
                    if (!NumberValidator.isValidUserID(idEntity)) {
                        return ConfigurationManager.getProperty(PAGE_CONFIRMFALSE);
                    }
                    long id3 = Long.parseLong(idEntity);
                    Cleaner cleaner = new Cleaner();
                    cleaner.setIdUser(id3);
                    return entityService.doService(cleaner).isEmpty() ?
                            ConfigurationManager.getProperty(PAGE_CONFIRM) :
                            ConfigurationManager.getProperty(PAGE_CONFIRMFALSE);
                case "service":
                    if (!NumberValidator.isValidServiceID(idEntity)) {
                        return ConfigurationManager.getProperty(PAGE_CONFIRMFALSE);
                    }
                    long id4 = Long.parseLong(idEntity);
                    Service service = new Service();
                    service.setId(id4);
                    return entityService.doService(service).isEmpty() ?
                            ConfigurationManager.getProperty(PAGE_CONFIRM) :
                            ConfigurationManager.getProperty(PAGE_CONFIRMFALSE);
                case "position_basket":
                    if (!NumberValidator.isValidBasketID(idEntity)) {
                        return ConfigurationManager.getProperty(PAGE_CONFIRMFALSE);
                    }
                    long id5 = Long.parseLong(idEntity);
                    Order order = (Order) request.getSession().getAttribute(ORDER_NEW);
                    long idOrder = order.getId();
                    BasketPosition position = new BasketPosition();
                    position.setId(id5);
                    position.setIdOrder(idOrder);
                    return entityService.doService(position).isEmpty() ?
                            ConfigurationManager.getProperty(PAGE_CONFIRM) :
                            ConfigurationManager.getProperty(PAGE_CONFIRMFALSE);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Exception while deleting entity has occurred. ", e);
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(PAGE_CONFIRMFALSE);
    }
}