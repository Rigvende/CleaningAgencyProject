package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.util.stringholder.PreparedStatements;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String CREATE = "create_client";
    private final static String DELETE = "delete_client";
    private final static String UPDATE = "update_client_admin";
    private final static String FIND_ENTITY = "find_client";
    private final static String UPDATE_CLIENT = "update_client_user";
    private static final String SQL_SELECT_ALL_CLIENTS =
            "SELECT id_client, id_user, discount, location, " +
                    "relative, notes FROM clients";

    public ClientDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        Client client = (Client) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get(CREATE);
            preparedStatement.setLong(1, 0);
            preparedStatement.setLong(2, client.getIdUser());
            preparedStatement.setString(3, null);
            preparedStatement.setString(4, null);
            preparedStatement.setString(5, null);
            preparedStatement.setString(6, null);
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot add client. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isAdded;
    }

    @Override
    public boolean delete(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        boolean isDeleted;
        Client client = (Client) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get(DELETE);
            preparedStatement.setLong(1, client.getIdUser());
            isDeleted = preparedStatement.execute();
            connection.commit();
        } catch (SQLException | DaoException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot delete client. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isDeleted;
    }

    @Override
    public boolean update(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        boolean isUpdated;
        Client client = (Client) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get(UPDATE);
            preparedStatement.setBigDecimal(1, client.getDiscount());
            preparedStatement.setString(2, client.getNotes());
            preparedStatement.setLong(3, client.getIdUser());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException | DaoException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot update client. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException, SQLException {
        connection.setAutoCommit(false);
        List<AbstractEntity> clients = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLIENTS);
            while (resultSet.next()) {
                Client client = EntityFactory.createClient(resultSet);
                clients.add(client);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot find all clients. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return clients;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        Client client;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get(FIND_ENTITY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            client = resultSet.next() ? EntityFactory.createClient(resultSet) : null;
            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot find client by ID. Request to table failed. ", e);
            if (connection != null) {
                connection.rollback();
            }
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return client;
    }

    public boolean updateByUser(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        boolean isUpdated;
        Client client = (Client) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get(UPDATE_CLIENT);
            preparedStatement.setString(1, client.getLocation());
            preparedStatement.setString(2, client.getRelative());
            preparedStatement.setLong(3, client.getIdUser());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException | DaoException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot update client by user. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }
}