package by.patrusova.project.entity;

import by.patrusova.project.dao.column.*;
import by.patrusova.project.entity.impl.*;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.RegistrationDataValidator;
import by.patrusova.project.validator.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class EntityFactory {

    public static BasketPosition createBasketPosition(ResultSet resultSet)
            throws DaoException {
        BasketPosition position = new BasketPosition();
        try {
            position.setId(resultSet.getLong
                    (String.valueOf(BasketColumns.ID_BASKET)));
            position.setIdOrder(resultSet.getLong
                    (String.valueOf(BasketColumns.ID_ORDER)));
            position.setIdService(resultSet.getLong
                    (String.valueOf(BasketColumns.ID_SERVICE)));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return position;
    }

    public static Cleaner createCleaner(ResultSet resultSet) throws DaoException {
        Cleaner cleaner = new Cleaner();
        try {
            cleaner.setId(resultSet.getLong
                    (String.valueOf(CleanerColumns.ID_CLEANER)));
            cleaner.setIdUser(resultSet.getLong
                    (String.valueOf(CleanerColumns.ID_USER)));
            cleaner.setCommission(resultSet.getBigDecimal
                    (String.valueOf(CleanerColumns.COMMISSION)));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return cleaner;
    }

    public static Client createClient(ResultSet resultSet) throws DaoException {
        Client client = new Client();
        try {
            client.setId(resultSet.getLong
                    (String.valueOf(ClientColumns.ID_CLIENT)));
            client.setIdUser(resultSet.getLong
                    (String.valueOf(ClientColumns.ID_USER)));
            client.setDiscount(resultSet.getBigDecimal
                    (String.valueOf(ClientColumns.DISCOUNT)));
            client.setLocation(resultSet.getString
                    (String.valueOf(ClientColumns.LOCATION)));
            client.setRelative(resultSet.getString
                    (String.valueOf(ClientColumns.RELATIVE)));
            client.setNotes(resultSet.getString
                    (String.valueOf(ClientColumns.NOTES)));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return client;
    }

    public static Order createOrder(ResultSet resultSet) throws DaoException {
        Order order = new Order();
        try {
            order.setId(resultSet.getLong
                    (String.valueOf(OrderColumns.ID_ORDER)));
            order.setOrderTime(new java.sql.Date(resultSet.getDate
                    (String.valueOf(OrderColumns.ORDER_TIME)).getTime()).toLocalDate());
            order.setDeadline(new java.sql.Date(resultSet.getDate
                    (String.valueOf(OrderColumns.DEADLINE)).getTime()).toLocalDate());
            order.setOrderStatus(resultSet.getString
                    (String.valueOf(OrderColumns.ORDER_STATUS)));
            order.setMark(resultSet.getInt
                    (String.valueOf(OrderColumns.MARK)));
            order.setIdCleaner(resultSet.getLong
                    (String.valueOf(OrderColumns.ID_CLEANER)));
            order.setIdClient(resultSet.getLong
                    (String.valueOf(OrderColumns.ID_CLIENT)));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return order;
    }

    public static Service createService(ResultSet resultSet) throws DaoException {
        Service service = new Service();
        try {
            service.setId(resultSet.getLong
                    (String.valueOf(ServiceColumns.ID_SERVICE)));
            service.setService(resultSet.getString
                    (String.valueOf(ServiceColumns.SERVICE)));
            service.setCost(resultSet.getBigDecimal
                    (String.valueOf(ServiceColumns.COST)));
            service.setDiscount(resultSet.getBigDecimal
                    (String.valueOf(ServiceColumns.DISCOUNT)));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return service;
    }

    public static User createUser(ResultSet resultSet) throws DaoException {
        User user = new User();
        try {
            user.setId(resultSet.getLong
                    (String.valueOf(UserColumns.ID_USER)));
            user.setLogin(resultSet.getString
                    (String.valueOf(UserColumns.LOGIN)));
            user.setPassword(resultSet.getString
                    (String.valueOf(UserColumns.PASSWORD)));
            user.setRole(resultSet.getString
                    (String.valueOf(UserColumns.ROLE)));
            user.setName(resultSet.getString
                    (String.valueOf(UserColumns.NAME)));
            user.setLastname(resultSet.getString
                    (String.valueOf(UserColumns.LASTNAME)));
            user.setPhone(resultSet.getLong
                    (String.valueOf(UserColumns.PHONE)));
            user.setAddress(resultSet.getString
                    (String.valueOf(UserColumns.ADDRESS)));
            user.setEmail(resultSet.getString
                    (String.valueOf(UserColumns.EMAIL)));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }
}