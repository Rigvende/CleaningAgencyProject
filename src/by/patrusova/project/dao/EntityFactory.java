package by.patrusova.project.dao;

import by.patrusova.project.entity.impl.*;
import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityFactory {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID_BASKET = "id_basket";
    private final static String ID_ORDER = "id_order";
    private final static String ID_SERVICE = "id_service";
    private final static String ID_CLEANER = "id_cleaner";
    private final static String ID_USER = "id_user";
    private final static String COMMISSION = "commission";
    private final static String CLIENT_NOTES = "client_notes";
    private final static String CLEANER_NOTES = "cleaner_notes";
    private final static String ID_CLIENT = "id_client";
    private final static String DISCOUNT = "discount";
    private final static String LOCATION = "location";
    private final static String RELATIVE = "relative";
    private final static String ORDER_TIME = "order_time";
    private final static String DEADLINE = "deadline";
    private final static String ORDER_STATUS = "order_status";
    private final static String MARK = "mark";
    private final static String SERVICE = "service";
    private final static String COST = "cost";
    private final static String SALES = "sales";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String ROLE = "role";
    private final static String NAME = "name";
    private final static String LASTNAME = "lastname";
    private final static String PHONE = "phone";
    private final static String ADDRESS = "address";
    private final static String EMAIL = "email";

    public static BasketPosition createBasketPosition(ResultSet resultSet)
            throws DaoException {
        BasketPosition position = new BasketPosition();
        try {
            position.setId(resultSet.getLong(ID_BASKET));
            position.setIdOrder(resultSet.getLong(ID_ORDER));
            position.setIdService(resultSet.getLong(ID_SERVICE));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create basket position. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return position;
    }

    public static Cleaner createCleaner(ResultSet resultSet) throws DaoException {
        Cleaner cleaner = new Cleaner();
        try {
            cleaner.setId(resultSet.getLong(ID_CLEANER));
            cleaner.setIdUser(resultSet.getLong(ID_USER));
            cleaner.setCommission(resultSet.getBigDecimal(COMMISSION));
            cleaner.setNotes(resultSet.getString(CLEANER_NOTES));//fixme
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create cleaner. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return cleaner;
    }

    public static Client createClient(ResultSet resultSet) throws DaoException {
        Client client = new Client();
        try {
            client.setId(resultSet.getLong(ID_CLIENT));
            client.setIdUser(resultSet.getLong(ID_USER));
            client.setDiscount(resultSet.getBigDecimal(DISCOUNT));
            client.setLocation(resultSet.getString(LOCATION));
            client.setRelative(resultSet.getString(RELATIVE));
            client.setNotes(resultSet.getString(CLIENT_NOTES));//fixme
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create client. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return client;
    }

    public static Order createOrder(ResultSet resultSet) throws DaoException {
        Order order = new Order();
        try {
            order.setId(resultSet.getLong(ID_ORDER));
            order.setOrderTime(new java.sql.Date(resultSet.getDate
                    (ORDER_TIME).getTime()).toLocalDate());
            order.setDeadline(new java.sql.Date(resultSet.getDate
                    (DEADLINE).getTime()).toLocalDate());
            order.setOrderStatus(resultSet.getString(ORDER_STATUS));
            order.setMark(resultSet.getInt(MARK));
            order.setIdCleaner(resultSet.getLong(ID_CLEANER));
            order.setIdClient(resultSet.getLong(ID_CLIENT));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create order. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return order;
    }

    public static Service createService(ResultSet resultSet) throws DaoException {
        Service service = new Service();
        try {
            service.setId(resultSet.getLong(ID_SERVICE));
            service.setService(resultSet.getString(SERVICE));
            service.setCost(resultSet.getBigDecimal(COST));
            service.setSales(resultSet.getBigDecimal(SALES));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create service. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return service;
    }

    public static User createUser(ResultSet resultSet) throws DaoException {
        User user = new User();
        try {
            user.setId(resultSet.getLong(ID_USER));
            user.setLogin(resultSet.getString(LOGIN));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setRole(resultSet.getString(ROLE));
            user.setName(resultSet.getString(NAME));
            user.setLastname(resultSet.getString(LASTNAME));
            user.setPhone(resultSet.getLong(PHONE));
            user.setAddress(resultSet.getString(ADDRESS));
            user.setEmail(resultSet.getString(EMAIL));
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot create user. Error has occurred. ", e);
            throw new DaoException(e);
        }
        return user;
    }

    public static ComplexOrder createOrderComplex(ResultSet resultSet) throws DaoException {
        User user = createUser(resultSet);
        Client client = createClient(resultSet);
        Cleaner cleaner = createCleaner(resultSet);
        Order order = createOrder(resultSet);
        return new ComplexOrder(user, cleaner, client, order);
    }

    public static ComplexPosition createPositionComplex(ResultSet resultSet) throws DaoException {
        BasketPosition position = createBasketPosition(resultSet);
        Service service = createService(resultSet);
        return new ComplexPosition(position, service);
    }
}