package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.EntityFactory;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.DaoException;
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
    private static final String SQL_SELECT_CLEANER_BY_ID =
                    "SELECT id_cleaner, id_user, commission " +
                    "FROM cleaners WHERE id_cleaner = ?";

    public CleanerDao(ProxyConnection connection) {
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
        List<AbstractEntity> cleaners = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CLEANERS);
            while (resultSet.next()) {
                Cleaner cleaner = EntityFactory.createCleaner(resultSet);
                cleaners.add(cleaner);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            returnConnectionInPool();
        }
        return cleaners;
    }
    @Override
    public Cleaner findEntityById(long id) throws DaoException {
        Cleaner cleaner = new Cleaner();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_CLEANER_BY_ID);
            statement.setDouble(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            cleaner = EntityFactory.createCleaner(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            returnConnectionInPool();
        }
        return cleaner;
    }
}