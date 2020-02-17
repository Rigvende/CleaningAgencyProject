package by.patrusova.project.dao;

import by.patrusova.project.connection.ConnectionPool;
import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class AbstractDao<T extends AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    protected ProxyConnection connection;

    public AbstractDao() {}
    public AbstractDao(ProxyConnection connection) {
        this.connection = connection;
    }

    public ProxyConnection getConnection() {
        return connection;
    }

    public abstract boolean create(T entity) throws DaoException, SQLException;
    public abstract boolean delete(T entity) throws DaoException, SQLException;
    public abstract boolean update(T entity) throws DaoException, SQLException;
    public abstract List<T> findAll() throws DaoException, SQLException;
    public abstract T findEntityById(long id) throws DaoException, SQLException;

    public void closeStatement(Statement statement) throws DaoException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Statement closing failed. ", e);
                throw new DaoException(e);
            } finally {
                if (connection != null) {
                    returnConnectionInPool();
                } else {
                    LOGGER.log(Level.WARN, "Connection is null. ");
                }
            }
        }
    }

    public void returnConnectionInPool() throws DaoException {
        ConnectionPool.getInstance().releaseConnection(connection);
    }
}