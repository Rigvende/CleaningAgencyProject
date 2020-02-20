package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.entity.impl.*;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.util.stringholder.Statements;
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

    @Override
    public boolean create(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        Order order = (Order) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_ADD_ORDER.getValue());
            preparedStatement.setLong(1, 0);
            preparedStatement.setDate(2, Date.valueOf(order.getOrderTime()));
            preparedStatement.setDate(3, Date.valueOf(order.getDeadline()));
            preparedStatement.setString(4, "registered");
            preparedStatement.setString(5, null);
            preparedStatement.setLong(6, order.getIdClient());
            preparedStatement.setLong(6, 0);
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot add order. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isAdded;
    }

    @Override
    public boolean delete(AbstractEntity entity) {
        return false;
    }

    @Override
    public boolean update(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        boolean isUpdated;
        Order order = (Order) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_UPDATE_ORDER.getValue());
            preparedStatement.setString(1, order.getOrderStatus());
            preparedStatement.setLong(2, order.getIdCleaner());
            preparedStatement.setLong(3, order.getId());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot update order. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException, SQLException {
        connection.setAutoCommit(false);
        List<AbstractEntity> orders = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ORDERS);
            while (resultSet.next()) {
                Order order = EntityFactory.createOrder(resultSet);
                orders.add(order);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot find all orders. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return orders;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        Order order;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_SELECT_ORDER_BY_ID.getValue());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            order = EntityFactory.createOrder(resultSet);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot find order by ID. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return order;
    }

    public List<OrderComplex> findOrdersById(String role, long id) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = null;
        List<OrderComplex> result = new ArrayList<>();
        try {
            switch (role) {
                case "cleaner":
                    preparedStatement = connection.prepareStatement
                            (Statements.SQL_FIND_ORDERS_BY_CLEANER.getValue());
                    preparedStatement.setLong(1, id);
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    while (resultSet1.next()) {
                        result.add(EntityFactory.createOrderComplex(resultSet1));
                    }
                    break;
                case "client":
                    preparedStatement = connection.prepareStatement
                            (Statements.SQL_FIND_ORDERS_BY_CLIENT.getValue());
                    preparedStatement.setLong(1, id);
                    ResultSet resultSet2 = preparedStatement.executeQuery();
                    while (resultSet2.next()) {
                        result.add(EntityFactory.createOrderComplex(resultSet2));
                    }
                    break;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot find all orders. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return result;
    }
}