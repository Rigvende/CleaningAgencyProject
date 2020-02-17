package by.patrusova.project.service.impl;

import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ClientDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.EntityCreator;
import by.patrusova.project.service.Serviceable;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Parameters;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ClientInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();

    //внесение изменений в данные клиента
    @Override
    public Client doService(AbstractEntity entity) throws ServiceException {
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
        return client;
    }

    //создание экземпляра клиента с изменениями
    @Override
    public Client createEntity(HttpServletRequest request) {
        Client updatedClient = (Client) request.getSession().getAttribute(Attributes.CLIENT.getValue());
        if (!validate(request).containsValue(false)) {
            updatedClient.setLocation(request.getParameter(Parameters.LOCATION.getValue()));
            updatedClient.setRelative(request.getParameter(Parameters.RELATIVE.getValue()));
            return updatedClient;
        } else {
            return null;
        }
    }

    //валидация введенных данных
    private Map<String, Boolean> validate(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();
        String location = request.getParameter(Parameters.LOCATION.getValue());
        String relative = request.getParameter(Parameters.RELATIVE.getValue());
        validationMap.put(Parameters.LOCATION.getValue(),
                StringValidator.isValidStringSize(Parameters.LOCATION.getValue(), location));
        validationMap.put(Parameters.RELATIVE.getValue(),
                StringValidator.isValidStringSize(Parameters.RELATIVE.getValue(), relative));
        return validationMap;
    }

    //проверка на наличие клиента в бд
    @Override
    public boolean isExist(AbstractEntity entity, AbstractDao<AbstractEntity> dao) throws ServiceException {
        try {
            Client client = (Client) entity;
            return dao.findEntityById(client.getIdUser()) != null;
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while checking client has occurred.");
            throw new ServiceException(e);
        }
    }

    //получить клиента из бд
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