package by.patrusova.project.dao;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Abstract class as parent for all DAO classes
 * @autor Marianna Patrusova
 * @version 1.0
 */
public abstract class AbstractDao<T extends AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    protected ProxyConnection connection;

    /**
     * Constructor: create AbstractDao instance
     * @param connection is {@link ProxyConnection} instance
     */
    public AbstractDao(ProxyConnection connection) {
        this.connection = connection;
    }

    /**
     * Method: get ProxyConnection instance
     * @return instance of {@link ProxyConnection}
     */
    public ProxyConnection getConnection() {
        return connection;
    }

    /**
     * Method: create database table's row depending on AbstractEntity instance.
     * @param entity is type of {@link AbstractEntity}
     * @return boolean
     */
    public abstract boolean create(T entity) throws DaoException;

    /**
     * Method: delete database table's row depending on AbstractEntity instance.
     * @throws DaoException object
     * @param entity is type of {@link AbstractEntity}
     * @return boolean
     */
    public abstract boolean delete(T entity) throws DaoException;

    /**
     * Method: update database table's row depending on AbstractEntity instance.
     * @throws DaoException object
     * @param entity is type of {@link AbstractEntity}
     * @return boolean
     */
    public abstract boolean update(T entity) throws DaoException;

    /**
     * Method: find all rows in database table depending on method's realization
     * and create suitable AbstractEntity object.
     * @throws DaoException object
     * @return List of found T type {@link AbstractEntity} objects
     */
    public abstract List<T> findAll() throws DaoException;

    /**
     * Method: find suitable row in database table and create AbstractEntity instance
     * @throws DaoException object
     * @param id is table's id of object
     * @return type T of {@link AbstractEntity}
     */
    public abstract T findEntityById(long id) throws DaoException;

    /**
     * Method: close not using statement
     * @throws DaoException object
     * @param statement is instance of {@link Statement}
     */
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

    private void returnConnectionInPool() {
        connection.close(); //here: releasing ProxyConnection, not closing
    }
}