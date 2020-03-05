package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.entity.impl.*;
import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for actions mostly with {@link Order} using connections, statements and queries
 * according DAO and database data
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class OrderDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID_ORDER = "id_order";
    private final static String TOTAL_COST = "total_cost";
    private final static String TOTAL_SALE = "total_sale";
    private final static String TOTAL_DISCOUNT = "total_discount";
    private final static String SQL_ADD_ORDER =
            "INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?, null);";
    private final static String SQL_DELETE_ORDER =
            "DELETE FROM orders WHERE id_order = ?;";
    private final static String SQL_UPDATE_ORDER =
            "UPDATE orders SET order_status = ?, id_cleaner = ? WHERE id_order = ?;";
    private final static String SQL_SELECT_ORDER_BY_ID =
            "SELECT id_order, order_time, deadline, order_status, mark, id_client, " +
                    "id_cleaner FROM orders WHERE id_order = ?;";
    private final static String SQL_FIND_ORDERS_BY_CLEANER =
            "SELECT users.id_user, users.login, users.password, users.role, users.name, " +
                    "users.lastname, users.phone, users.address, users.email, " +
                    "clients.id_client, clients.id_user, clients.discount, clients.location, " +
                    "clients.relative, clients.notes AS client_notes, cleaners.id_cleaner, " +
                    "cleaners.id_user, cleaners.commission, cleaners.notes AS cleaner_notes, " +
                    "orders.id_order, orders.order_time, orders.deadline, orders.order_status, " +
                    "orders.mark, orders.id_client, orders.id_cleaner FROM (clients JOIN users " +
                    "ON clients.id_user = users.id_user) JOIN (orders JOIN cleaners " +
                    "ON orders.id_cleaner = cleaners.id_cleaner ) " +
                    "ON clients.id_client = orders.id_client WHERE orders.id_cleaner = ?;";
    private final static String SQL_FIND_ORDERS_BY_CLIENT =
            "SELECT users.id_user, users.login, users.password, users.role, users.name, users.lastname, " +
                    "users.phone, users.address, users.email, clients.id_client, clients.id_user, " +
                    "clients.discount, clients.location, clients.relative, clients.notes " +
                    "AS client_notes, cleaners.id_cleaner, cleaners.id_user, cleaners.commission, " +
                    "cleaners.notes AS cleaner_notes, orders.id_order, orders.order_time, " +
                    "orders.deadline, orders.order_status, orders.mark, orders.id_client, " +
                    "orders.id_cleaner FROM  (orders JOIN clients ON orders.id_client = clients.id_client ) " +
                    "LEFT OUTER JOIN (cleaners JOIN users ON cleaners.id_user = users.id_user) " +
                    "ON cleaners.id_cleaner = orders.id_cleaner WHERE orders.id_client = ?;";
    private final static String SQL_CANCEL_ORDER =
            "UPDATE orders SET order_status = 'declined' WHERE id_order = ?;";
    private final static String SQL_SET_MARK =
            "UPDATE orders SET mark = ? WHERE id_order = ? AND id_client = ?;";
    private final static String SQL_FIND_NEW =
            "SELECT id_order FROM orders WHERE order_status = 'new' AND id_client = ?;";
    private static final String SQL_SELECT_ALL_ORDERS =
            "SELECT id_order, order_time, deadline, order_status, " +
                    "mark, id_client, id_cleaner FROM orders;";
    private final static String SQL_SELECT_ID =
            "SELECT id_order FROM orders;";
    private final static String SQL_TOTAL_COST =
            "SELECT SUM(cost) AS total_cost FROM services JOIN basket_position " +
                    "ON basket_position.id_service = services.id_service WHERE id_order = ?;";
    private final static String SQL_TOTAL_SALE =
            "SELECT SUM(cost*sales) AS total_sale FROM services JOIN basket_position " +
                    "ON basket_position.id_service = services.id_service WHERE id_order = ?;";
    private final static String SQL_TOTAL_DISCOUNT =
            "SELECT SUM(cost*discount) AS total_discount FROM (services JOIN basket_position " +
                    "ON basket_position.id_service = services.id_service) JOIN " +
                    "(orders JOIN clients ON orders.id_client = clients.id_client) " +
                    "ON basket_position.id_order = orders.id_order WHERE orders.id_order = ?;";
    private final static String SQL_PLACE_ORDER =
            "UPDATE orders SET order_time = ?, deadline = ?, order_status = ? WHERE id_order = ?;";

    public OrderDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao create method. ", e);
            throw new DaoException(e);
        }
        Order order = (Order) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_ORDER);
            preparedStatement.setLong(1, 0);
            preparedStatement.setDate(2, Date.valueOf(order.getOrderTime()));
            preparedStatement.setDate(3, Date.valueOf(order.getDeadline()));
            preparedStatement.setString(4, Order.Status.REGISTERED.getValue());
            preparedStatement.setString(5, null);
            preparedStatement.setLong(6, order.getIdClient());
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao create method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot add order. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao createNew method. ", e);
            throw new DaoException(e);
        }
        Order order = (Order) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_ORDER);
            preparedStatement.setLong(1, 0);
            preparedStatement.setDate(2, null);
            preparedStatement.setDate(3, null);
            preparedStatement.setString(4, Order.Status.NEW.getValue());
            preparedStatement.setString(5, null);
            preparedStatement.setLong(6, order.getIdClient());
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao createNew method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot add order. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao delete method. ", e);
            throw new DaoException(e);
        }
        boolean isDeleted;
        Order order = (Order)entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_ORDER);
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
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao delete method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot delete service. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao update method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        Order order = (Order) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_ORDER);
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
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao update method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot update order. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao findAll method. ", e);
            throw new DaoException(e);
        }
        List<AbstractEntity> orders = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ORDERS)) {
                while (resultSet.next()) {
                    Order order = EntityFactory.createOrder(resultSet);
                    orders.add(order);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao findAll method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find all orders. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao findEntityById method. ", e);
            throw new DaoException(e);
        }
        Order order;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                order = resultSet.next() ? EntityFactory.createOrder(resultSet) : null;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao findEntityById method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find order by ID. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return order;
    }

    public List<ComplexOrder> findOrdersById(String role, long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao findOrdersById method. ", e);
            throw new DaoException(e);
        }
        PreparedStatement preparedStatement = null;
        List<ComplexOrder> result = new ArrayList<>();
        try {
            switch (role) {
                case "cleaner":
                    preparedStatement = connection.prepareStatement(SQL_FIND_ORDERS_BY_CLEANER);
                    preparedStatement.setLong(1, id);
                    try (ResultSet resultSet1 = preparedStatement.executeQuery()) {
                        while (resultSet1.next()) {
                            result.add(EntityFactory.createOrderComplex(resultSet1));
                        }
                    }
                    break;
                case "client":
                    preparedStatement = connection.prepareStatement(SQL_FIND_ORDERS_BY_CLIENT);
                    preparedStatement.setLong(1, id);
                    try (ResultSet resultSet2 = preparedStatement.executeQuery()) {
                        while (resultSet2.next()) {
                            result.add(EntityFactory.createOrderComplex(resultSet2));
                        }
                    }
                    break;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao findOrdersById method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find all orders. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao cancelOrder method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        Order order = (Order) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_CANCEL_ORDER);
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
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao cancelOrder method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot update order. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao findId method. ", e);
            throw new DaoException(e);
        }
        Statement statement = connection.createStatement();
        try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ID)) {
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
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao findId method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find id_order in DB. Request to table failed.", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao setMark method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        Order order = (Order) entity;
        int mark = order.getMark();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SET_MARK);
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
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao setMark method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot update order. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao findNew method. ", e);
            throw new DaoException(e);
        }
        Order order = (Order) entity;
        PreparedStatement preparedStatement = null;
        try {
            if (order.getOrderStatus().equals(Order.Status.NEW.getValue())) {
                preparedStatement = connection.prepareStatement(SQL_FIND_NEW);
                preparedStatement.setLong(1, order.getIdClient());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        order.setId(resultSet.getLong(ID_ORDER));
                    }
                }
                connection.commit();
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao findNew method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find order by ID. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return order;
    }

    public long findNew(long idClient) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao findNew method. ", e);
            throw new DaoException(e);
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_NEW);
            preparedStatement.setLong(1, idClient);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    return 0;
                } else {
                    return resultSet.getLong(ID_ORDER);
                }
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao findNew method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find id_order in DB. Request to table failed.", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
    }

    public BigDecimal findCost(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao findCost method. ", e);
            throw new DaoException(e);
        }
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        PreparedStatement preparedStatement3 = null;
        try {
            preparedStatement1 = connection.prepareStatement(SQL_TOTAL_COST);
            preparedStatement1.setLong(1, id);
            preparedStatement2 = connection.prepareStatement(SQL_TOTAL_SALE);
            preparedStatement2.setLong(1, id);
            preparedStatement3 = connection.prepareStatement(SQL_TOTAL_DISCOUNT);
            preparedStatement3.setLong(1, id);
            try (ResultSet resultSet1 = preparedStatement1.executeQuery();
                 ResultSet resultSet2 = preparedStatement2.executeQuery();
                 ResultSet resultSet3 = preparedStatement3.executeQuery()) {
                BigDecimal totalCost;
                BigDecimal totalSale;
                BigDecimal totalDiscount;
                if (!resultSet1.next()) {
                    return BigDecimal.valueOf(0.0);
                } else {
                    totalCost = resultSet1.getBigDecimal(TOTAL_COST);
                    totalSale = resultSet2.next() ?
                            resultSet2.getBigDecimal(TOTAL_SALE) : BigDecimal.valueOf(0.0);
                    totalDiscount = resultSet3.next() ?
                            resultSet3.getBigDecimal(TOTAL_DISCOUNT) : BigDecimal.valueOf(0.0);
                    return totalCost.subtract(totalSale).subtract(totalDiscount);
                }
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao findCost method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find id_order in DB. Request to table failed.", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement1);
            closeStatement(preparedStatement2);
            closeStatement(preparedStatement3);
        }
    }

    public boolean place(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in OrderDao place method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        Order order = (Order) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_PLACE_ORDER);
            preparedStatement.setDate(1, Date.valueOf(order.getOrderTime()));
            preparedStatement.setDate(2, Date.valueOf(order.getDeadline()));
            preparedStatement.setString(3, order.getOrderStatus());
            preparedStatement.setLong(4, order.getId());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in OrderDao place method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot update order. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }
}