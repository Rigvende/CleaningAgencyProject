package by.patrusova.project.dao;

import by.patrusova.project.connection.ConnectionPool;
import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.impl.*;
import by.patrusova.project.exception.DaoException;
import java.sql.Connection;

public class DaoFactory {

    public BasketDao createBasketDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new BasketDao((ProxyConnection) connection);
    }

    public CleanerDao createCleanerDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new CleanerDao((ProxyConnection) connection);
    }

    public OrderDao createOrderDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new OrderDao((ProxyConnection) connection);
    }

    public ClientDao createClientDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new ClientDao((ProxyConnection) connection);
    }

    public ServiceDao createServiceDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new ServiceDao((ProxyConnection) connection);
    }

    public UserDao createUserDao() throws DaoException {
        Connection connection = ConnectionPool.getInstance().takeConnection();
        return new UserDao((ProxyConnection) connection);
    }
}