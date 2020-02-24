package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.util.column.ClientColumn;
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
    private final static String SQL_SELECT_ID = "SELECT id_client FROM clients";

    public ClientDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in ClientDao create method. ", e);
            throw new DaoException(e);
        }
        Client client = (Client) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (by.patrusova.project.util.stringholder.Statement.SQL_CREATE_CLIENT_BY_ADMIN.getValue());
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
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in ClientDao create method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot add client. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isAdded;
    }

    @Override
    public boolean delete(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in ClientDao delete method. ", e);
            throw new DaoException(e);
        }
        boolean isDeleted;
        Client client = (Client) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (by.patrusova.project.util.stringholder.Statement.SQL_DELETE_CLIENT.getValue());
            preparedStatement.setLong(1, client.getIdUser());
            isDeleted = preparedStatement.execute();
            if (!findId(client.getIdUser())) {
                isDeleted = true;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in ClientDao delete method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot delete client. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isDeleted;
    }

    @Override
    public boolean update(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in ClientDao update method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        Client client = (Client) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (by.patrusova.project.util.stringholder.Statement.SQL_UPDATE_CLIENT_BY_ADMIN.getValue());
            preparedStatement.setBigDecimal(1, client.getDiscount());
            preparedStatement.setString(2, client.getNotes());
            preparedStatement.setLong(3, client.getIdUser());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in ClientDao update method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot update client. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in ClientDao findAll method. ", e);
            throw new DaoException(e);
        }
        List<AbstractEntity> clients = new ArrayList<>();
        java.sql.Statement statement = null;
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
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in ClientDao findAll method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find all clients. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return clients;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in ClientDao findEntityById method. ", e);
            throw new DaoException(e);
        }
        Client client;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (by.patrusova.project.util.stringholder.Statement.SQL_SELECT_CLIENT_BY_ID.getValue());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            client = resultSet.next() ? EntityFactory.createClient(resultSet) : null;
            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot find client by ID. Request to table failed. ", e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in ClientDao findEntityById method. ", e);
                    throw new DaoException(e);
                }
            }
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return client;
    }

    public boolean updateByUser(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in ClientDao updateByUser method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        Client client = (Client) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (by.patrusova.project.util.stringholder.Statement.SQL_UPDATE_CLIENT_BY_USER.getValue());
            preparedStatement.setString(1, client.getLocation());
            preparedStatement.setString(2, client.getRelative());
            preparedStatement.setLong(3, client.getIdUser());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in ClientDao updateByUser method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot update client by user. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }

    public boolean findId(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in ClientDao findId method. ", e);
            throw new DaoException(e);
        }
        java.sql.Statement statement = connection.createStatement();
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ID);
            while (resultSet.next()) {
                if (Long.parseLong(resultSet.getString(1)) == id) {
                    connection.commit();
                    return true;
                }
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in ClientDao findId method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find id_client in DB. Request to table failed.", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return false;
    }

    public long findIdUser(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in ClientDao findIdUser method. ", e);
            throw new DaoException(e);
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (by.patrusova.project.util.stringholder.Statement.SQL_FIND_ID_USER.getValue());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                    long idUser = resultSet.getLong(ClientColumn.ID_USER.getValue());
                    connection.commit();
                    return idUser;
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in ClientDao findIdUser method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find id_client in DB. Request to table failed.", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return 0;
    }

    public boolean setNotes(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in ClientDao setNotes method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        Client client = (Client) entity;
        String notes = client.getNotes();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (by.patrusova.project.util.stringholder.Statement.SQL_SET_NOTES.getValue());
            preparedStatement.setString(1, client.getNotes());
            preparedStatement.setLong(2, client.getId());
            isUpdated = preparedStatement.execute();
            Client client1 = (Client) findEntityById(findIdUser(client.getId()));
            if (client1.getNotes().equals(notes)) {
                isUpdated = true;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in ClientDao setNotes method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot update client. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }
}