package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ClientDao;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.EntityCreator;
import by.patrusova.project.service.Serviceable;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Parameters;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ClientInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();

    //внесение изменений в данные клиента самим клиентом
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        Client client = (Client) entity;
        try {
            ClientDao dao = factory.createClientDao();
            if (dao.updateByUser(client)) {
                client = null;
            }
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while updating client's info has occurred. ", e);
            throw new ServiceException(e);
        }
        return client != null ? Optional.of(client) : Optional.empty();
    }

    //внесение изменений в данные клиента админом
    public Optional<AbstractEntity> doServiceByAdmin(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        Client client = (Client) entity;
        try {
            ClientDao dao = factory.createClientDao();
            if (dao.update(client)) {
                client = null;
            }
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while updating client's info has occurred. ", e);
            throw new ServiceException(e);
        }
        return client != null ? Optional.of(client) : Optional.empty();
    }

    //внесение изменений в данные клиента клинером
    public Optional<AbstractEntity> doService(long id, long idCleaner, String notes) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        try {
            OrderDao orderDao = factory.createOrderDao();
            Order order = (Order) orderDao.findEntityById(id);
            if (order.getIdCleaner() == idCleaner) {
                ClientDao clientDao = factory.createClientDao();
                long idClient = order.getIdClient();
                long idUser = clientDao.findIdUser(idClient);
                if (idUser != 0) {
                    Client client = (Client) clientDao.findEntityById(idUser);
                    client.setNotes(notes);
                    return clientDao.setNotes(client) ? Optional.of(client) : Optional.empty();
                }
            }
            return Optional.empty();
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while updating client has occurred. ", e);
            throw new ServiceException(e);
        }
    }

    //создание экземпляра клиента с изменениями
    @Override
    public Optional<AbstractEntity> createEntity(HttpServletRequest request) {
        if (request.getSession().getAttribute(Attributes.ROLE.getValue()) //сам клиент вносит изменения
                .equals(Attributes.CLIENT.getValue())) {
            Client updatedClient = (Client) request.getSession()
                    .getAttribute(Attributes.CLIENT.getValue());
            if (!validate(request).containsValue(false)) {
                updatedClient.setLocation(request.getParameter(Parameters.LOCATION.getValue()));
                updatedClient.setRelative(request.getParameter(Parameters.RELATIVE.getValue()));
                return Optional.of(updatedClient);
            } else {
                return Optional.empty();
            }
        } else {                                                            //изменения вносит админ
            Client updatedClient = (Client) request.getSession()
                    .getAttribute(Attributes.CLIENT.getValue());
            if (!validate(request).containsValue(false)) {
                String discount = request.getParameter(Parameters.DISCOUNT.getValue());
                updatedClient.setDiscount(BigDecimal.valueOf(Double.parseDouble(discount)));
                updatedClient.setNotes(request.getParameter(Parameters.NOTES.getValue()));
                return Optional.of(updatedClient);
            } else {
                return Optional.empty();
            }
        }
    }

    //валидация введенных данных
    private Map<String, Boolean> validate(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();                 //сам клиент вносит изменения
        if (request.getSession().getAttribute(Attributes.ROLE.getValue())
                .equals(Attributes.CLIENT.getValue())) {
            String location = request.getParameter(Parameters.LOCATION.getValue());
            String relative = request.getParameter(Parameters.RELATIVE.getValue());
            validationMap.put(Parameters.LOCATION.getValue(),
                    StringValidator.isValidStringSize(Parameters.LOCATION.getValue(), location));
            validationMap.put(Parameters.RELATIVE.getValue(),
                    StringValidator.isValidStringSize(Parameters.RELATIVE.getValue(), relative));
        } else {                                                                //изменения вносит админ
            String discount = request.getParameter(Parameters.DISCOUNT.getValue());
            String notes = request.getParameter(Parameters.NOTES.getValue());
            validationMap.put(Parameters.DISCOUNT.getValue(),
                    NumberValidator.isValidDecimal(discount));
            validationMap.put(Parameters.NOTES.getValue(),
                    StringValidator.isValidStringSize(Parameters.NOTES.getValue(), notes));
        }
        return validationMap;
    }

    //получить клиента из бд по данным из пришедшего экземпляра клиента
    public Client getClient(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        Client client = (Client) entity;
        try {
            ClientDao dao = factory.createClientDao();
            client = (Client) dao.findEntityById(client.getIdUser());
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while finding client has occurred.");
            throw new ServiceException(e);
        }
        return client;
    }
}