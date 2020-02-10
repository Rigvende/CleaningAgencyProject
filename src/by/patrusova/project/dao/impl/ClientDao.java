package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.EntityFactory;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.util.PreparedStatements;
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

    public ClientDao(ProxyConnection connection) {
        super(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException {
        Client client = (Client) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("create_client");
            preparedStatement.setLong(1, 0);
            preparedStatement.setLong(2, client.getIdUser());
            preparedStatement.setString(3, null);
            preparedStatement.setString(4, null);
            preparedStatement.setString(5, null);
            preparedStatement.setString(6, null);
            isAdded = preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot add client. Request to table failed.");
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
        return isAdded;
    }

    @Override
    public boolean delete(AbstractEntity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("delete_client");
            preparedStatement.setLong(1, client.getIdUser());
            return preparedStatement.execute();
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
    }

    @Override
    public boolean update(AbstractEntity entity) throws DaoException {
        Client client = (Client) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("update_client_admin");
            preparedStatement.setBigDecimal(1, client.getDiscount());
            preparedStatement.setString(2, client.getNotes());
            preparedStatement.setLong(3, client.getIdUser());
            return preparedStatement.execute();
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
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
        Client client;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("find_client");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            client = EntityFactory.createClient(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
        return client;
    }

    public boolean updateByUser(AbstractEntity entity1, AbstractEntity entity2) throws DaoException {
        User user = (User)entity1;
        Client client = (Client)entity2;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("update_user_client");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setLong(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, client.getLocation());
            preparedStatement.setString(7, client.getRelative());
            preparedStatement.setString(8, user.getLogin());
            return preparedStatement.execute();
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
    }
}