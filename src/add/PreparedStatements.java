package add;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

public class PreparedStatements {

    private final static String SHOW_CLEANER = "SELECT users.name, users.lastname, users.phone " +
            "FROM cleaners JOIN users ON cleaners.id_user = users.id_user WHERE id_cleaner = ?";

    private final static String SHOW_CLIENT = "SELECT users.name, users.lastname, users.phone, " +
            "clients.location, clients.relative FROM clients JOIN users " +
            "ON clients.id_user = users.id_user WHERE id_client = ?";

    private final static String TOTAL_COST =
            "SELECT SUM(cost) AS total_cost FROM services, basket_position " +
                    "WHERE basket_position.id_order = ? AND basket_position.id_service = services.id_service";

    public static ConcurrentHashMap<String, PreparedStatement> useStatements(Connection connection) throws SQLException {
        ConcurrentHashMap<String, PreparedStatement> statements = new ConcurrentHashMap<>();
        PreparedStatement statement1 = connection.prepareStatement(TOTAL_COST);
        PreparedStatement statement2 = connection.prepareStatement(SHOW_CLEANER);
        PreparedStatement statement3 = connection.prepareStatement(SHOW_CLIENT);
        statements.put("totalCost", statement1);
        statements.put("show_cleaner", statement2);
        statements.put("show_client", statement3);
        return statements;
    }
}