package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.EntityFactory;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
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
    private static final String SQL_SELECT_ORDER_BY_ID =
                    "SELECT id_order, order_time, deadline, order_status, " +
                    "mark, id_client, id_cleaner FROM orders WHERE id_client = ?";

    public OrderDao(ProxyConnection connection) {
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
        Order order = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            order = EntityFactory.createOrder(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            returnConnectionInPool();
        }
        return order;
    }
}