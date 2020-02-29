package by.patrusova.project.dao;

import by.patrusova.project.connection.ConnectionPool;
import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.impl.*;
import by.patrusova.project.exception.DaoException;
import java.sql.Connection;

/**
 * Class for creating instances of DAOs.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class DaoFactory {

    private DaoFactory(){}

    public static BasketDao createBasketDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new BasketDao((ProxyConnection) connection);
    }

    public static CleanerDao createCleanerDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new CleanerDao((ProxyConnection) connection);
    }

    public static OrderDao createOrderDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new OrderDao((ProxyConnection) connection);
    }

    public static ClientDao createClientDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new ClientDao((ProxyConnection) connection);
    }

    public static ServiceDao createServiceDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new ServiceDao((ProxyConnection) connection);
    }

    public static UserDao createUserDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new UserDao((ProxyConnection) connection);
    }
}