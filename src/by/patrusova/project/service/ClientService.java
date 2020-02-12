package by.patrusova.project.service;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ClientDao;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import java.sql.SQLException;

public class ClientService {

    public static Client getClient(Client client) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        try {
            ClientDao dao = factory.createClientDao();
            client = (Client) dao.findEntityById(client.getIdUser());
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return client;
    }
}