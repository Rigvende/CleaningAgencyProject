package by.patrusova.project.service;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ClientDao;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import java.sql.SQLException;

public class ClientService {

    public static Client getClient(Client client) throws ServiceException, SQLException {
        DaoFactory factory = new DaoFactory();
        ProxyConnection connection = null;
        try {
            connection = (ProxyConnection) factory.getConnection();
            connection.setAutoCommit(false);
            ClientDao dao = factory.createClientDao(connection);
            client = (Client) dao.findEntityById(client.getIdUser());
            connection.commit();
            connection.close();
        } catch (DaoException | SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw new ServiceException(e);
        }
        return client;
    }
}