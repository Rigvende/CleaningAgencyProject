package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.EntityFactory;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.util.PreparedStatements;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_ORDERS =
                    "SELECT id_order, order_time, deadline, order_status, " +
                    "mark, id_client, id_cleaner FROM orders";

    public OrderDao(ProxyConnection connection) {
        super(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException {
        Order order = (Order)entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("add_order");
            preparedStatement.setLong(1, 0);
            preparedStatement.setDate(2, Date.valueOf(order.getOrderTime()));
            preparedStatement.setDate(3, Date.valueOf(order.getDeadline()));
            preparedStatement.setString(4, "registered");
            preparedStatement.setString(5, null);
            preparedStatement.setLong(6, order.getIdClient());
            preparedStatement.setLong(6, 0);
            isAdded = preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot add order. Request to table failed.");
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
        return isAdded;
    }

    @Override
    public boolean delete(AbstractEntity entity) throws DaoException {
        Order order = (Order)entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("delete_order");
            preparedStatement.setLong(1, order.getId());
            return preparedStatement.execute();
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
    }

    @Override
    public boolean update(AbstractEntity entity) throws DaoException {
        Order order = (Order)entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("update_order");
            preparedStatement.setDate(1, Date.valueOf(order.getDeadline()));
            preparedStatement.setString(2, order.getOrderStatus());
            preparedStatement.setLong(3, order.getIdCleaner());
            preparedStatement.setLong(4, order.getId());
            return preparedStatement.execute();
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException {
        List<AbstractEntity> orders = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ORDERS);
            while (resultSet.next()) {
                Order order = EntityFactory.createOrder(resultSet);
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            returnConnectionInPool();
        }
        return orders;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException {
        Order order;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("find_order");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            order = EntityFactory.createOrder(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
        return order;
    }
}