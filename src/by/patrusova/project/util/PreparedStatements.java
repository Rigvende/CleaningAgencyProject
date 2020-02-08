package by.patrusova.project.util;

import by.patrusova.project.exception.DaoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class PreparedStatements {

    private final static String SQL_SHOW_CLEANER = "SELECT users.name, users.lastname, users.phone " +
            "FROM cleaners JOIN users ON cleaners.id_user = users.id_user WHERE id_cleaner = ?";
    private final static String SQL_SHOW_CLIENT = "SELECT users.name, users.lastname, users.phone, " +
            "clients.location, clients.relative FROM clients JOIN users " +
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
    private final static String SQL_ADD_USER =
            "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            statements.put("totalCost", statement1);
            statements.put("show_cleaner", statement2);
            statements.put("show_client", statement3);
            statements.put("check_login", statement4);
            statements.put("find_user_id", statement5);
            statements.put("add_user", statement6);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return statements;
    }
}