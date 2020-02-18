package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.util.stringholder.Statements;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CleanerDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_CLEANERS =
            "SELECT id_cleaner, id_user, commission FROM cleaners";

    public CleanerDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        Cleaner cleaner = (Cleaner) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_CREATE_CLEANER_BY_ADMIN.getValue());
            preparedStatement.setLong(1, 0);
            preparedStatement.setLong(2, cleaner.getIdUser());
            preparedStatement.setString(3, null);
            preparedStatement.setString(4, null);
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot add cleaner. Request to table failed. ", e);
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
        Cleaner cleaner = (Cleaner) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_DELETE_CLEANER.getValue());
            preparedStatement.setLong(1, cleaner.getIdUser());
            isDeleted = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot delete cleaner. Request to table failed. ", e);
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
        Cleaner cleaner = (Cleaner) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_UPDATE_CLEANER_BY_ADMIN.getValue());
            preparedStatement.setBigDecimal(1, cleaner.getCommission());
            preparedStatement.setString(2, cleaner.getNotes());
            preparedStatement.setLong(3, cleaner.getIdUser());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot update cleaner. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException, SQLException {
        connection.setAutoCommit(false);
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
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot find all cleaners. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return cleaners;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        Cleaner cleaner;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_SELECT_CLEANER_BY_ID.getValue());
            preparedStatement.setDouble(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            cleaner = EntityFactory.createCleaner(resultSet);
            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot find cleaner by ID. Request to table failed. ", e);
            if (connection != null) {
                connection.rollback();
            }
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return cleaner;
    }
}