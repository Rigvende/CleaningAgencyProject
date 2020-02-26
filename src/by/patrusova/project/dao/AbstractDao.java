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

    public AbstractDao(ProxyConnection connection) {
        this.connection = connection;
    }

    public ProxyConnection getConnection() {
        return connection;
    }

    public abstract boolean create(T entity) throws DaoException;
    public abstract boolean delete(T entity) throws DaoException;
    public abstract boolean update(T entity) throws DaoException;
    public abstract List<T> findAll() throws DaoException;
    public abstract T findEntityById(long id) throws DaoException;

    public void closeStatement(Statement statement) throws DaoException {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Statements closing failed. ", e);
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

    private void returnConnectionInPool() throws DaoException {
//        ConnectionPool.getInstance().releaseConnection(connection);
        connection.close(); //releasing ProxyConnection, not closing
    }
}