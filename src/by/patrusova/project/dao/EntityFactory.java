package by.patrusova.project.dao;

import by.patrusova.project.util.column.*;
import by.patrusova.project.entity.impl.*;
import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityFactory {

    private final static Logger LOGGER = LogManager.getLogger();

    public static BasketPosition createBasketPosition(ResultSet resultSet)
            throws DaoException {
        BasketPosition position = new BasketPosition();
        try {
            position.setId(resultSet.getLong(BasketColumn.ID_BASKET.getValue()));
            position.setIdOrder(resultSet.getLong(BasketColumn.ID_ORDER.getValue()));
            position.setIdService(resultSet.getLong(BasketColumn.ID_SERVICE.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create basket position. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return position;
    }

    public static Cleaner createCleaner(ResultSet resultSet) throws DaoException {
        Cleaner cleaner = new Cleaner();
        try {
            cleaner.setId(resultSet.getLong(CleanerColumn.ID_CLEANER.getValue()));
            cleaner.setIdUser(resultSet.getLong(CleanerColumn.ID_USER.getValue()));
            cleaner.setCommission(resultSet.getBigDecimal(CleanerColumn.COMMISSION.getValue()));
            cleaner.setNotes(resultSet.getString(CleanerColumn.CLEANER_NOTES.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create cleaner. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return cleaner;
    }

    public static Client createClient(ResultSet resultSet) throws DaoException {
        Client client = new Client();
        try {
            client.setId(resultSet.getLong(ClientColumn.ID_CLIENT.getValue()));
            client.setIdUser(resultSet.getLong(ClientColumn.ID_USER.getValue()));
            client.setDiscount(resultSet.getBigDecimal(ClientColumn.DISCOUNT.getValue()));
            client.setLocation(resultSet.getString(ClientColumn.LOCATION.getValue()));
            client.setRelative(resultSet.getString(ClientColumn.RELATIVE.getValue()));
            client.setNotes(resultSet.getString(ClientColumn.CLIENT_NOTES.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create client. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return client;
    }

    public static Order createOrder(ResultSet resultSet) throws DaoException {
        Order order = new Order();
        try {
            order.setId(resultSet.getLong(OrderColumn.ID_ORDER.getValue()));
            order.setOrderTime(new java.sql.Date(resultSet.getDate
                    (OrderColumn.ORDER_TIME.getValue()).getTime()).toLocalDate());
            order.setDeadline(new java.sql.Date(resultSet.getDate
                    (OrderColumn.DEADLINE.getValue()).getTime()).toLocalDate());
            order.setOrderStatus(resultSet.getString(OrderColumn.ORDER_STATUS.getValue()));
            order.setMark(resultSet.getInt(OrderColumn.MARK.getValue()));
            order.setIdCleaner(resultSet.getLong(OrderColumn.ID_CLEANER.getValue()));
            order.setIdClient(resultSet.getLong(OrderColumn.ID_CLIENT.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create order. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return order;
    }

    public static Service createService(ResultSet resultSet) throws DaoException {
        Service service = new Service();
        try {
            service.setId(resultSet.getLong(ServiceColumn.ID_SERVICE.getValue()));
            service.setService(resultSet.getString(ServiceColumn.SERVICE.getValue()));
            service.setCost(resultSet.getBigDecimal(ServiceColumn.COST.getValue()));
            service.setSales(resultSet.getBigDecimal(ServiceColumn.SALES.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create service. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return service;
    }

    public static User createUser(ResultSet resultSet) throws DaoException {
        User user = new User();
        try {
            user.setId(resultSet.getLong(UserColumn.ID_USER.getValue()));
            user.setLogin(resultSet.getString(UserColumn.LOGIN.getValue()));
            user.setPassword(resultSet.getString(UserColumn.PASSWORD.getValue()));
            user.setRole(resultSet.getString(UserColumn.ROLE.getValue()));
            user.setName(resultSet.getString(UserColumn.NAME.getValue()));
            user.setLastname(resultSet.getString(UserColumn.LASTNAME.getValue()));
            user.setPhone(resultSet.getLong(UserColumn.PHONE.getValue()));
            user.setAddress(resultSet.getString(UserColumn.ADDRESS.getValue()));
            user.setEmail(resultSet.getString(UserColumn.EMAIL.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create user. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return user;
    }

    public static OrderComplex createOrderComplex(ResultSet resultSet) throws DaoException {
        User user = createUser(resultSet);
        Client client = createClient(resultSet);
        Cleaner cleaner = createCleaner(resultSet);
        Order order = createOrder(resultSet);
        return new OrderComplex(user, cleaner, client, order);
    }
}