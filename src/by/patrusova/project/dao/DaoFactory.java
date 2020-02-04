package by.patrusova.project.dao;

import by.patrusova.project.connection.ConnectionPool;
import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.impl.*;
import by.patrusova.project.exception.DaoException;
import java.sql.Connection;

public class DaoFactory {

    public Connection getConnection()
            throws DaoException {
        return ConnectionPool.getInstance().takeConnection();
    }

    public BasketDao createBasketDao(Connection connection) throws DaoException {
        if (connection != null) {
            return new BasketDao((ProxyConnection) connection);
        } else {
            throw new DaoException("Connection is null.");
        }
    }

    public CleanerDao createCleanerDao(Connection connection) throws DaoException {
        if (connection != null) {
            return new CleanerDao((ProxyConnection) connection);
        } else {
            throw new DaoException("Connection is null.");
        }
    }

    public OrderDao createOrderDao(Connection connection) throws DaoException {
        if (connection != null) {
            return new OrderDao((ProxyConnection) connection);
        } else {
            throw new DaoException("Connection is null.");
        }
    }

    public ClientDao createClientDao(Connection connection) throws DaoException {
        if (connection != null) {
            return new ClientDao((ProxyConnection) connection);
        } else {
            throw new DaoException("Connection is null.");
        }
    }

    public ServiceDao createServiceDao(Connection connection) throws DaoException {
        if (connection != null) {
            return new ServiceDao((ProxyConnection) connection);
        } else {
            throw new DaoException("Connection is null.");
        }
    }

    public UserDao createUserDao(Connection connection) throws DaoException {
        if (connection != null) {
            return new UserDao((ProxyConnection) connection);
        } else {
            throw new DaoException("Connection is null.");
        }
    }
}
