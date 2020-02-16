package by.patrusova.project.util.stringholder;

import by.patrusova.project.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class PreparedStatements {

    private final static String SQL_SHOW_CLEANER = "SELECT users.name, users.lastname, " +
            "users.phone, users.email, cleaners.commission FROM cleaners JOIN users " +
            "ON cleaners.id_user = users.id_user WHERE id_cleaner = ?";
    private final static String SQL_SHOW_CLIENT = "SELECT users.name, users.lastname, users.phone, " +
            "users.email, clients.location, clients.relative FROM clients JOIN users " +
            "ON clients.id_user = users.id_user WHERE id_client = ?";
    private final static String SQL_TOTAL_COST =
            "SELECT SUM(cost) AS total_cost FROM services, basket_position " +
                    "WHERE basket_position.id_order = ? AND basket_position.id_service = services.id_service";
    private static final String SQL_SELECT_USER_BY_LOGIN_PASS =
            "SELECT id_user, login, password, role, name, lastname, phone, " +
                    "address, email FROM users WHERE login = ? AND password = ?";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT id_user, login, password, role, name, lastname, phone, " +
                    "address, email FROM users WHERE id_user = ?";
    private static final String SQL_SELECT_CLEANER_BY_ID =
            "SELECT id_cleaner, id_user, commission, notes FROM cleaners WHERE id_user = ?";
    private static final String SQL_SELECT_CLIENT_BY_ID =
            "SELECT id_client, id_user, discount, location, relative, notes FROM clients WHERE id_user = ?";
    private final static String SQL_ADD_USER =
            "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_DELETE_USER_BY_LOGIN = "DELETE FROM users WHERE login = ?";
    private final static String SQL_DELETE_CLEANER = "DELETE FROM cleaners WHERE id_user = ?";
    private final static String SQL_DELETE_CLIENT = "DELETE FROM clients WHERE id_user = ?";
    private final static String SQL_UPDATE_ROLE_BY_ADMIN = "UPDATE users SET role = ? WHERE id_user = ?";
    private final static String SQL_CREATE_CLIENT_BY_ADMIN = "INSERT INTO clients " +
            "VALUES (0, ?, null, null, null, null)";
    private final static String SQL_CREATE_CLEANER_BY_ADMIN = "INSERT INTO cleaners " +
            "VALUES (0, ?, null, null)";
    private final static String SQL_UPDATE_USER = "UPDATE users SET name = ?, lastname = ?, phone = ?, " +
            "email = ?, address = ? WHERE id_user = ?";
    private final static String SQL_UPDATE_CLIENT_BY_USER = "UPDATE clients " +
            "SET location = ?, relative = ? WHERE id_user = ?";
    private final static String SQL_UPDATE_CLEANER_BY_ADMIN = "UPDATE cleaners " +
            "SET commission = ?, notes = ? WHERE id_user = ?";
    private final static String SQL_UPDATE_CLIENT_BY_ADMIN = "UPDATE clients " +
            "SET discount = ?, notes = ? WHERE id_user = ?";
    private static final String SQL_SELECT_BASKET_POSITION_BY_ID =
            "SELECT id_basket, id_order, id_service FROM basket_position WHERE id_basket = ?";
    private static final String SQL_ADD_POSITION = "INSERT INTO basket_position VALUES (?, ?, ?)";
    private final static String SQL_DELETE_POSITION = "DELETE FROM basket_position WHERE id_basket = ?";
    private static final String SQL_SELECT_SERVICE_BY_ID =
            "SELECT id_service, service, cost, discount FROM services WHERE id_service = ?";
    private static final String SQL_ADD_SERVICE = "INSERT INTO services VALUES (?, ?, ?, ?)";
    private final static String SQL_DELETE_SERVICE = "DELETE FROM services WHERE id_service = ?";
    private final static String SQL_UPDATE_SERVICE = "UPDATE services " +
            "SET service = ?, cost = ?, discount = ? WHERE id_service = ?";
    private static final String SQL_SELECT_ORDER_BY_ID =
            "SELECT id_order, order_time, deadline, order_status, " +
                    "mark, id_client, id_cleaner FROM orders WHERE id_order = ?";
    private static final String SQL_ADD_ORDER = "INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_DELETE_ORDER = "DELETE FROM orders WHERE id_order = ?";
    private final static String SQL_UPDATE_ORDER = "UPDATE orders " +
            "SET deadline = ?, order_status = ?, id_cleaner = ? WHERE id_order = ?";
    private final static String SQL_SET_MARK = "UPDATE orders SET mark = ? WHERE id_order = ?";
    private final static String SQL_FIND_USERS_BY_ROLE =
            "SELECT id_user, login, password, role, name, lastname, " +
                    "phone, address, email FROM users WHERE role = ?";
    private final static String SQL_FIND_CLEANERS_BY_ROLE =
            "SELECT users.id_user, users.login, users.password, users.role, users.name, " +
                    "users.lastname, users.phone, users.address, users.email, cleaners.id_cleaner, " +
                    "cleaners.id_user, cleaners.commission, cleaners.notes " +
                    "FROM cleaners JOIN users ON cleaners.id_user = users.id_user WHERE users.role = ?";
    private final static String SQL_FIND_CLIENTS_BY_ROLE =
            "SELECT users.id_user, users.login, users.password, users.role, users.name, " +
                    "users.lastname, users.phone, users.address, users.email, clients.id_client, " +
                    "clients.id_user, clients.discount, clients.location, clients.relative, clients.notes " +
                    "FROM clients JOIN users ON clients.id_user = users.id_user WHERE users.role = ?";

    public static ConcurrentHashMap<String, PreparedStatement>
                                    useStatements(Connection connection) throws DaoException {
        ConcurrentHashMap<String, PreparedStatement> statements = new ConcurrentHashMap<>();
        try {
            PreparedStatement statement1 = connection.prepareStatement(SQL_TOTAL_COST);
            PreparedStatement statement2 = connection.prepareStatement(SQL_SHOW_CLEANER);
            PreparedStatement statement3 = connection.prepareStatement(SQL_SHOW_CLIENT);
            PreparedStatement statement4 = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASS);
            PreparedStatement statement5 = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            PreparedStatement statement6 = connection.prepareStatement(SQL_ADD_USER);
            PreparedStatement statement7 = connection.prepareStatement(SQL_DELETE_USER_BY_LOGIN);
            PreparedStatement statement8 = connection.prepareStatement(SQL_UPDATE_ROLE_BY_ADMIN);
            PreparedStatement statement9 = connection.prepareStatement(SQL_CREATE_CLIENT_BY_ADMIN);
            PreparedStatement statement10= connection.prepareStatement(SQL_CREATE_CLEANER_BY_ADMIN);
            PreparedStatement statement11 = connection.prepareStatement(SQL_UPDATE_USER);
            PreparedStatement statement12 = connection.prepareStatement(SQL_UPDATE_CLIENT_BY_USER);
            PreparedStatement statement13 = connection.prepareStatement(SQL_UPDATE_CLEANER_BY_ADMIN);
            PreparedStatement statement14 = connection.prepareStatement(SQL_UPDATE_CLIENT_BY_ADMIN);
            PreparedStatement statement15 = connection.prepareStatement(SQL_DELETE_CLEANER);
            PreparedStatement statement16 = connection.prepareStatement(SQL_DELETE_CLIENT);
            PreparedStatement statement17 = connection.prepareStatement(SQL_SELECT_CLEANER_BY_ID);
            PreparedStatement statement18 = connection.prepareStatement(SQL_SELECT_CLIENT_BY_ID);
            PreparedStatement statement19 = connection.prepareStatement(SQL_SELECT_BASKET_POSITION_BY_ID);
            PreparedStatement statement20 = connection.prepareStatement(SQL_ADD_POSITION);
            PreparedStatement statement21 = connection.prepareStatement(SQL_DELETE_POSITION);
            PreparedStatement statement22 = connection.prepareStatement(SQL_SELECT_SERVICE_BY_ID);
            PreparedStatement statement23 = connection.prepareStatement(SQL_ADD_SERVICE);
            PreparedStatement statement24 = connection.prepareStatement(SQL_DELETE_SERVICE);
            PreparedStatement statement25 = connection.prepareStatement(SQL_UPDATE_SERVICE);
            PreparedStatement statement26 = connection.prepareStatement(SQL_SELECT_ORDER_BY_ID);
            PreparedStatement statement27 = connection.prepareStatement(SQL_ADD_ORDER);
            PreparedStatement statement28 = connection.prepareStatement(SQL_DELETE_ORDER);
            PreparedStatement statement29 = connection.prepareStatement(SQL_UPDATE_ORDER);
            PreparedStatement statement30 = connection.prepareStatement(SQL_SET_MARK);
            PreparedStatement statement31 = connection.prepareStatement(SQL_FIND_USERS_BY_ROLE);
            PreparedStatement statement32 = connection.prepareStatement(SQL_FIND_CLEANERS_BY_ROLE);
            PreparedStatement statement33 = connection.prepareStatement(SQL_FIND_CLIENTS_BY_ROLE);
            statements.put("total_cost", statement1);
            statements.put("show_cleaner", statement2);
            statements.put("show_client", statement3);
            statements.put("check_login", statement4);
            statements.put("find_user", statement5);
            statements.put("add_user", statement6);
            statements.put("delete_user", statement7);
            statements.put("update_role", statement8);
            statements.put("create_client", statement9);
            statements.put("create_cleaner", statement10);
            statements.put("update_user", statement11);
            statements.put("update_client_user", statement12);
            statements.put("update_cleaner_admin", statement13);
            statements.put("update_client_admin", statement14);
            statements.put("delete_cleaner", statement15);
            statements.put("delete_client", statement16);
            statements.put("find_cleaner", statement17);
            statements.put("find_client", statement18);
            statements.put("select_position", statement19);
            statements.put("add_position", statement20);
            statements.put("delete_position", statement21);
            statements.put("select_service", statement22);
            statements.put("add_service", statement23);
            statements.put("delete_service", statement24);
            statements.put("update_service", statement25);
            statements.put("find_order", statement26);
            statements.put("add_order", statement27);
            statements.put("delete_order", statement28);
            statements.put("update_order", statement29);
            statements.put("set_mark", statement30);
            statements.put("find_role", statement31);
            statements.put("find_role_cleaner", statement32);
            statements.put("find_role_client", statement33);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return statements;
    }
}