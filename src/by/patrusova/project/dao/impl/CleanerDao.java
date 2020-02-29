package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for actions mostly with {@link Cleaner} using connections, statements and queries
 * according DAO and database data
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class CleanerDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String SQL_CREATE_CLEANER_BY_ADMIN =
            "INSERT INTO cleaners VALUES (?, ?, ?, ?);";
    private static final String SQL_DELETE_CLEANER =
            "DELETE FROM cleaners WHERE id_user = ?;";
    private static final String SQL_UPDATE_CLEANER_BY_ADMIN =
            "UPDATE cleaners SET commission = ?, notes = ? WHERE id_user = ?;";
    private static final String SQL_SELECT_CLEANER_BY_ID =
            "SELECT id_cleaner, id_user, commission, notes AS cleaner_notes " +
                    "FROM cleaners WHERE id_user = ?;";
    private static final String SQL_SELECT_ALL_CLEANERS =
            "SELECT id_cleaner, id_user, commission FROM cleaners;";
    private final static String SQL_SELECT_ID =
            "SELECT id_cleaner FROM cleaners;";

    public CleanerDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in CleanerDao create method. ", e);
            throw new DaoException(e);
        }
        Cleaner cleaner = (Cleaner) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CREATE_CLEANER_BY_ADMIN);
            preparedStatement.setLong(1, 0);
            preparedStatement.setLong(2, cleaner.getIdUser());
            preparedStatement.setString(3, null);
            preparedStatement.setString(4, null);
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in CleanerDao create method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot add cleaner. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in CleanerDao delete method. ", e);
            throw new DaoException(e);
        }
        boolean isDeleted;
        Cleaner cleaner = (Cleaner) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_CLEANER);
            preparedStatement.setLong(1, cleaner.getIdUser());
            isDeleted = preparedStatement.execute();
            if (!findId(cleaner.getIdUser())) {
                isDeleted = true;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in CleanerDao delete method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot delete cleaner. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in CleanerDao update method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        Cleaner cleaner = (Cleaner) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_CLEANER_BY_ADMIN);
            preparedStatement.setBigDecimal(1, cleaner.getCommission());
            preparedStatement.setString(2, cleaner.getNotes());
            preparedStatement.setLong(3, cleaner.getIdUser());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in CleanerDao update method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot update cleaner. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in CleanerDao findAll method. ", e);
            throw new DaoException(e);
        }
        List<AbstractEntity> cleaners = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLEANERS);
            while (resultSet.next()) {
                Cleaner cleaner = EntityFactory.createCleaner(resultSet);
                cleaners.add(cleaner);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in CleanerDao findAll method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find all cleaners. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return cleaners;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in CleanerDao findEntityById method. ", e);
            throw new DaoException(e);
        }
        Cleaner cleaner;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_CLEANER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            cleaner = resultSet.next() ? EntityFactory.createCleaner(resultSet) : null;
            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot find cleaner by ID. Request to table failed. ", e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in CleanerDao findEntityById method. ", e);
                    throw new DaoException(e);
                }
            }
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return cleaner;
    }

    public boolean findId(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in CleanerDao findId method. ", e);
            throw new DaoException(e);
        }
        Statement statement = connection.createStatement();
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ID); //проверка при регистрации
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
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in CleanerDao findId method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find id_cleaner in DB. Request to table failed.", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return false;
    }
}