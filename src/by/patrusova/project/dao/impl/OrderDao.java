package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.entity.impl.*;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.util.column.OrderColumn;
import by.patrusova.project.util.stringholder.Statement;
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
    private final static String SQL_SELECT_ID = "SELECT id_order FROM orders";

    public OrderDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in OrderDao create method. ", e);
            throw new DaoException(e);
        }
        Order order = (Order) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statement.SQL_ADD_ORDER.getValue());
            preparedStatement.setLong(1, 0);
            preparedStatement.setDate(2, Date.valueOf(order.getOrderTime()));
            preparedStatement.setDate(3, Date.valueOf(order.getDeadline()));
            preparedStatement.setString(4, "registered");
            preparedStatement.setString(5, null);
            preparedStatement.setLong(6, order.getIdClient());
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in OrderDao create method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot add order. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isAdded;
    }

    public boolean createNew(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in OrderDao createNew method. ", e);
            throw new DaoException(e);
        }
        Order order = (Order) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statement.SQL_ADD_ORDER.getValue());
            preparedStatement.setLong(1, 0);
            preparedStatement.setDate(2, null);
            preparedStatement.setDate(3, null);
            preparedStatement.setString(4, "new");
            preparedStatement.setString(5, null);
            preparedStatement.setLong(6, order.getIdClient());
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in OrderDao createNew method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot add order. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isAdded;
    }

    @Override
    public boolean delete(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in OrderDao delete method. ", e);
            throw new DaoException(e);
        }
        boolean isDeleted;
        Order order = (Order)entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statement.SQL_DELETE_ORDER.getValue());
            preparedStatement.setLong(1, order.getId());
            isDeleted = preparedStatement.execute();
            if (!findId(order.getId())) {
                isDeleted = true;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in OrderDao delete method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot delete service. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isDeleted;
    }

    @Override
    public boolean update(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in OrderDao update method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        Order order = (Order) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statement.SQL_UPDATE_ORDER.getValue());
            preparedStatement.setString(1, order.getOrderStatus());
            preparedStatement.setLong(2, order.getIdCleaner());
            preparedStatement.setLong(3, order.getId());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in OrderDao update method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot update order. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in OrderDao findAll method. ", e);
            throw new DaoException(e);
        }
        List<AbstractEntity> orders = new ArrayList<>();
        java.sql.Statement statement = null;
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
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in OrderDao findAll method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find all orders. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return orders;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in OrderDao findEntityById method. ", e);
            throw new DaoException(e);
        }
        Order order;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statement.SQL_SELECT_ORDER_BY_ID.getValue());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            order = resultSet.next() ? EntityFactory.createOrder(resultSet) : null;
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in OrderDao findEntityById method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find order by ID. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return order;
    }

    public List<OrderComplex> findOrdersById(String role, long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in OrderDao findOrdersById method. ", e);
            throw new DaoException(e);
        }
        PreparedStatement preparedStatement = null;
        List<OrderComplex> result = new ArrayList<>();
        try {
            switch (role) {
                case "cleaner":
                    preparedStatement = connection.prepareStatement
                            (Statement.SQL_FIND_ORDERS_BY_CLEANER.getValue());
                    preparedStatement.setLong(1, id);
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    while (resultSet1.next()) {
                        result.add(EntityFactory.createOrderComplex(resultSet1));
                    }
                    break;
                case "client":
                    preparedStatement = connection.prepareStatement
                            (Statement.SQL_FIND_ORDERS_BY_CLIENT.getValue());
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
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in OrderDao findOrdersById method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find all orders. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return result;
    }

    public boolean cancelOrder(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in OrderDao cancelOrder method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        Order order = (Order) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statement.SQL_CANCEL_ORDER.getValue());
            preparedStatement.setLong(1, order.getId());
            isUpdated = preparedStatement.execute();
            Order order1 = (Order) findEntityById(order.getId());
            if (order1.getOrderStatus().equals(Order.Status.DECLINED.getValue())) {
                isUpdated = true;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in OrderDao cancelOrder method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot update order. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }

    public boolean findId(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in OrderDao findId method. ", e);
            throw new DaoException(e);
        }
        java.sql.Statement statement = connection.createStatement();
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ID);
            while (resultSet.next()) {
                if (Long.parseLong(resultSet.getString(1)) == id) {
                    connection.commit();
                    return true;
                }
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in OrderDao findId method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find id_order in DB. Request to table failed.", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return false;
    }

    public boolean setMark(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in OrderDao setMark method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        Order order = (Order) entity;
        int mark = order.getMark();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statement.SQL_SET_MARK.getValue());
            preparedStatement.setInt(1, order.getMark());
            preparedStatement.setLong(2, order.getId());
            preparedStatement.setLong(3, order.getIdClient());
            isUpdated = preparedStatement.execute();
            Order order1 = (Order) findEntityById(order.getId());
            if (order1.getMark() == mark) {
                isUpdated = true;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in OrderDao setMark method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot update order. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }

    public AbstractEntity findNew(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in OrderDao findNew method. ", e);
            throw new DaoException(e);
        }
        Order order = (Order) entity;
        PreparedStatement preparedStatement = null;
        try {
            if (order.getOrderStatus().equals(Order.Status.NEW.getValue())) {
                preparedStatement = connection.prepareStatement
                        (Statement.SQL_FIND_NEW_ORDER.getValue());
                preparedStatement.setLong(1, order.getIdClient());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    order.setId(resultSet.getLong(OrderColumn.ID_ORDER.getValue()));
                }
                connection.commit();
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in OrderDao findNew method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find order by ID. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return order;
    }
}