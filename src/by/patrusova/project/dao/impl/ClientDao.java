package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.EntityFactory;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_CLIENTS =
                    "SELECT id_client, id_user, discount, location, " +
                    "relative, notes FROM clients";
    private static final String SQL_SELECT_CLIENT_BY_ID =
                    "SELECT id_client, id_user, discount, location, " +
                    "relative, notes FROM clients WHERE id_client = ?";

    public ClientDao(ProxyConnection connection) {
        super(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean create(AbstractEntity entity) {
        return false;
    }

    @Override
    public boolean delete(AbstractEntity entity) {
        return false;
    }

    @Override
    public AbstractEntity update(AbstractEntity entity) {
        return null;
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException {
        List<AbstractEntity> clients = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLIENTS);
            while (resultSet.next()) {
                Client client = EntityFactory.createClient(resultSet);
                clients.add(client);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            returnConnectionInPool();
        }
        return clients;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException {
            Client client = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CLIENT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            client = EntityFactory.createClient(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            returnConnectionInPool();
        }
        return client;
    }
}