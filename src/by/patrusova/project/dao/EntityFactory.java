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
            position.setId(resultSet.getLong(BasketColumns.ID_BASKET.getValue()));
            position.setIdOrder(resultSet.getLong(BasketColumns.ID_ORDER.getValue()));
            position.setIdService(resultSet.getLong(BasketColumns.ID_SERVICE.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create basket position. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return position;
    }

    public static Cleaner createCleaner(ResultSet resultSet) throws DaoException {
        Cleaner cleaner = new Cleaner();
        try {
            cleaner.setId(resultSet.getLong(CleanerColumns.ID_CLEANER.getValue()));
            cleaner.setIdUser(resultSet.getLong(CleanerColumns.ID_USER.getValue()));
            cleaner.setCommission(resultSet.getBigDecimal(CleanerColumns.COMMISSION.getValue()));
            cleaner.setNotes(resultSet.getString(CleanerColumns.NOTES.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create cleaner. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return cleaner;
    }

    public static Client createClient(ResultSet resultSet) throws DaoException {
        Client client = new Client();
        try {
            client.setId(resultSet.getLong(ClientColumns.ID_CLIENT.getValue()));
            client.setIdUser(resultSet.getLong(ClientColumns.ID_USER.getValue()));
            client.setDiscount(resultSet.getBigDecimal(ClientColumns.DISCOUNT.getValue()));
            client.setLocation(resultSet.getString(ClientColumns.LOCATION.getValue()));
            client.setRelative(resultSet.getString(ClientColumns.RELATIVE.getValue()));
            client.setNotes(resultSet.getString(ClientColumns.NOTES.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create client. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return client;
    }

    public static Order createOrder(ResultSet resultSet) throws DaoException {
        Order order = new Order();
        try {
            order.setId(resultSet.getLong(OrderColumns.ID_ORDER.getValue()));
            order.setOrderTime(new java.sql.Date(resultSet.getDate
                    (OrderColumns.ORDER_TIME.getValue()).getTime()).toLocalDate());
            order.setDeadline(new java.sql.Date(resultSet.getDate
                    (OrderColumns.DEADLINE.getValue()).getTime()).toLocalDate());
            order.setOrderStatus(resultSet.getString(OrderColumns.ORDER_STATUS.getValue()));
            order.setMark(resultSet.getInt(OrderColumns.MARK.getValue()));
            order.setIdCleaner(resultSet.getLong(OrderColumns.ID_CLEANER.getValue()));
            order.setIdClient(resultSet.getLong(OrderColumns.ID_CLIENT.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create order. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return order;
    }

    public static Service createService(ResultSet resultSet) throws DaoException {
        Service service = new Service();
        try {
            service.setId(resultSet.getLong(ServiceColumns.ID_SERVICE.getValue()));
            service.setService(resultSet.getString(ServiceColumns.SERVICE.getValue()));
            service.setCost(resultSet.getBigDecimal(ServiceColumns.COST.getValue()));
            service.setDiscount(resultSet.getBigDecimal(ServiceColumns.DISCOUNT.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create service. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return service;
    }

    public static User createUser(ResultSet resultSet) throws DaoException {
        User user = new User();
        try {
            user.setId(resultSet.getLong(UserColumns.ID_USER.getValue()));
            user.setLogin(resultSet.getString(UserColumns.LOGIN.getValue()));
            user.setPassword(resultSet.getString(UserColumns.PASSWORD.getValue()));
            user.setRole(resultSet.getString(UserColumns.ROLE.getValue()));
            user.setName(resultSet.getString(UserColumns.NAME.getValue()));
            user.setLastname(resultSet.getString(UserColumns.LASTNAME.getValue()));
            user.setPhone(resultSet.getLong(UserColumns.PHONE.getValue()));
            user.setEmail(resultSet.getString(UserColumns.EMAIL.getValue()));
            user.setAddress(resultSet.getString(UserColumns.ADDRESS.getValue()));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create user. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return user;
    }
}