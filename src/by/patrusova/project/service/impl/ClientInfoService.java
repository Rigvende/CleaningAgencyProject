package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.CleanerDao;
import by.patrusova.project.dao.impl.ClientDao;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.EntityCreator;
import by.patrusova.project.service.Serviceable;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Class for implementation of service logic concerning clients's info
 * using operations with {@link ClientDao}
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ClientInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String DISCOUNT = "discount";
    private final static String NOTES = "notes";
    private final static String ROLE = "role";
    private final static String LOCATION = "location";
    private final static String RELATIVE = "relative";

    //update client by client himself
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        Client client = (Client) entity;
        try {
            ClientDao dao = DaoFactory.createClientDao();
            if (dao.updateByUser(client)) {
                client = null;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in ClientInfoService while updating client has occurred. ", e);
            throw new ServiceException(e);
        }
        return client != null ? Optional.of(client) : Optional.empty();
    }

    //update client by admin
    public Optional<AbstractEntity> doServiceByAdmin(AbstractEntity entity) throws ServiceException {
        Client client = (Client) entity;
        try {
            ClientDao dao = DaoFactory.createClientDao();
            if (dao.update(client)) {
                client = null;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in CleanerInfoService while updating cleaner has occurred. ", e);
            throw new ServiceException(e);
        }
        return client != null ? Optional.of(client) : Optional.empty();
    }

    //update client by cleaner
    public Optional<AbstractEntity> doService(long idOrder, long idCleaner, String notes) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.createOrderDao();
            Order order = (Order) orderDao.findEntityById(idOrder);
            if (order.getIdCleaner() == idCleaner) {
                ClientDao clientDao = DaoFactory.createClientDao();
                long idClient = order.getIdClient();
                long idUser = clientDao.findIdUser(idClient);
                if (idUser != 0) {
                    Client client = (Client) clientDao.findEntityById(idUser);
                    client.setNotes(notes);
                    return clientDao.setNotes(client) ? Optional.of(client) : Optional.empty();
                }
            }
            return Optional.empty();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in CleanerInfoService while updating cleaner has occurred. ", e);
            throw new ServiceException(e);
        }
    }

    //create instance of client with changes
    @Override
    public Optional<AbstractEntity> createEntity(HttpServletRequest request) {
        if (request.getSession().getAttribute(ROLE)                         //changes made by client
                .equals(Role.CLIENT.getValue())) {
            Client updatedClient = (Client) request.getSession()
                    .getAttribute(Role.CLIENT.getValue());
            if (!validate(request).containsValue(false)) {
                updatedClient.setLocation(request.getParameter(LOCATION));
                updatedClient.setRelative(request.getParameter(RELATIVE));
                return Optional.of(updatedClient);
            } else {
                return Optional.empty();
            }
        } else {                                                            //changes made by admin
            Client updatedClient = (Client) request.getSession()
                    .getAttribute(Role.CLIENT.getValue());
            if (!validate(request).containsValue(false)) {
                String discount = request.getParameter(DISCOUNT);
                updatedClient.setDiscount(BigDecimal.valueOf(Double.parseDouble(discount)));
                updatedClient.setNotes(request.getParameter(NOTES));
                return Optional.of(updatedClient);
            } else {
                return Optional.empty();
            }
        }
    }

    //validation
    private Map<String, Boolean> validate(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();                 //changes made by client
        if (request.getSession().getAttribute(ROLE).equals(Role.CLIENT.getValue())) {
            String location = request.getParameter(LOCATION);
            String relative = request.getParameter(RELATIVE);
            validationMap.put(LOCATION,
                    StringValidator.isValidStringSize(LOCATION, location));
            validationMap.put(RELATIVE,
                    StringValidator.isValidStringSize(RELATIVE, relative));
        } else {                                                                //changes made by admin
            String discount = request.getParameter(DISCOUNT);
            String notes = request.getParameter(NOTES);
            validationMap.put(DISCOUNT, NumberValidator.isValidDecimal(discount));
            validationMap.put(NOTES, StringValidator.isValidStringSize(NOTES, notes));
        }
        return validationMap;
    }

    //get client from DB
    public Client getClient(AbstractEntity entity) throws ServiceException {
        Client client = (Client) entity;
        try {
            ClientDao dao = DaoFactory.createClientDao();
            client = (Client) dao.findEntityById(client.getIdUser());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Exception while finding client has occurred.");
            throw new ServiceException(e);
        }
        return client;
    }
}