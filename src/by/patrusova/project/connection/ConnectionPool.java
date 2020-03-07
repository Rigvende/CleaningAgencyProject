package by.patrusova.project.connection;

import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Class for operations with connection pool instance.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ConnectionPool {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static int MAX_POOL_SIZE = 20;
    private static BlockingQueue<ProxyConnection> pool = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
    private static BlockingQueue<ProxyConnection> usedConnections = new LinkedBlockingQueue<>();
    private static Lock lock = new ReentrantLock();
    private static AtomicBoolean flag = new AtomicBoolean(false);
    private static ConnectionPool instance;

    //private constructor for singleton instance
    private ConnectionPool() throws DaoException {
        if (instance != null) {
            LOGGER.log(Level.FATAL, "Attempt to create one more class instance. ");
            throw new RuntimeException("Cannot create another pool's instance.");
        }
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            LOGGER.log(Level.INFO, "Registering jdbc driver.");
            init();
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, "Cannot create Connection pool's instance", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    //fill connection pool by proxy connections
    private void init() throws DaoException {
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            pool.offer(ProxyConnection.createProxyConnection());
        }
    }

    /**
     * Method: get (or create and get) instance of ConnectionPool.
     * @throws DaoException object
     * @return instance of ConnectionPool
     */
    public static ConnectionPool getInstance() throws DaoException {
        if (!flag.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    LOGGER.log(Level.INFO, "Connection pool instance has been created successful.");
                    flag.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Method: take connection from connection pool.
     * @return instance of {@link ProxyConnection} (as {@link Connection})
     */
    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            connection = pool.take();
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Cannot take connection from pool. ", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Method: take connection from connection pool.
     * @throws DaoException object
     * @param connection - instance of {@link Connection} (in program's logic - {@link ProxyConnection})
     */
    public void releaseConnection(Connection connection) throws DaoException {
        if (connection instanceof ProxyConnection) {
            usedConnections.remove(connection);
            pool.offer((ProxyConnection) connection);
        } else {
            LOGGER.log(Level.WARN, "Wrong type connection. ");
            throw new DaoException();
        }
    }

    /**
     * Method: close all connections in connection pool and deregister drivers at the end of work.
     * @throws DaoException object
     */
    public void closeAllConnections() throws DaoException {
        try {
            for (int i = 0; i < MAX_POOL_SIZE; i++) {
                Connection connection = pool.take();
                closeConnection(connection);
            }
            deregisterDrivers();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Connections' closing failed. ", e);
            throw new DaoException(e);
        }
    }

    //close 1 connection from pool
    private void closeConnection(Connection connection) throws DaoException {
        if (connection != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Connection closing failed. ", e);
                throw new DaoException(e);
            }
        }
    }

    //deregister all drivers while closing connections
    private void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
                LOGGER.log(Level.INFO, String.format("Deregister of jdbc driver %s", driver));
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, String.format("Error while deregistering driver %s", driver));
            }
        });
    }

    /**
     * Method: clone object.
     * @throws CloneNotSupportedException object if trying to clone instance of ConnectionPool (singleton)
     * @return Object (in theory)
     */
    public final Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}