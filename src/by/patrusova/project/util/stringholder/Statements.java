package by.patrusova.project.util.stringholder;

public enum Statements {

    SQL_TOTAL_COST("SELECT SUM(cost) AS total_cost FROM services, basket_position " +
                    "WHERE basket_position.id_order = ? AND basket_position.id_service = services.id_service"),
    SQL_AVERAGE_MARK("SELECT AVG(mark) AS average_mark FROM orders, cleaners " +
                    "WHERE id_cleaner = ? AND orders.id_cleaner = cleaners.id_cleaner"),
    SQL_SELECT_USER_BY_LOGIN_PASS("SELECT id_user, login, password, role, name, lastname, phone, " +
                    "address, email FROM users WHERE login = ? AND password = ?"),
    SQL_SELECT_USER_BY_ID("SELECT id_user, login, password, role, name, lastname, phone, " +
                    "address, email FROM users WHERE id_user = ?"),
    SQL_SELECT_CLEANER_BY_ID("SELECT id_cleaner, id_user, commission, notes FROM cleaners WHERE id_user = ?"),
    SQL_SELECT_CLIENT_BY_ID("SELECT id_client, id_user, discount, location, relative, " +
                    "notes FROM clients WHERE id_user = ?"),
    SQL_ADD_USER("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"),
    SQL_DELETE_USER("DELETE FROM users WHERE id_user = ?"),
    SQL_DELETE_CLEANER("DELETE FROM cleaners WHERE id_user = ?"),
    SQL_DELETE_CLIENT("DELETE FROM clients WHERE id_user = ?"),
    SQL_CREATE_CLIENT_BY_ADMIN("INSERT INTO clients VALUES (?, ?, ?, ?, ?, ?)"),
    SQL_CREATE_CLEANER_BY_ADMIN("INSERT INTO cleaners VALUES (?, ?, ?, ?)"),
    SQL_UPDATE_USER("UPDATE users SET name = ?, lastname = ?, " +
                    "role = ?, phone = ?, address = ?, email = ?  WHERE id_user = ?"),
    SQL_UPDATE_CLIENT_BY_USER("UPDATE clients SET location = ?, relative = ? WHERE id_user = ?"),
    SQL_UPDATE_CLEANER_BY_ADMIN("UPDATE cleaners SET commission = ?, notes = ? WHERE id_user = ?"),
    SQL_UPDATE_CLIENT_BY_ADMIN("UPDATE clients SET discount = ?, notes = ? WHERE id_user = ?"),
    SQL_SELECT_BASKET_POSITION_BY_ID("SELECT id_basket, id_order, id_service " +
                    "FROM basket_position WHERE id_basket = ?"),
    SQL_ADD_POSITION("INSERT INTO basket_position VALUES (?, ?, ?)"),
    SQL_DELETE_POSITION("DELETE FROM basket_position WHERE id_service = ?"),
    SQL_SELECT_SERVICE_BY_ID("SELECT id_service, service, cost, discount FROM services WHERE id_service = ?"),
    SQL_ADD_SERVICE("INSERT INTO services VALUES (?, ?, ?, ?)"),
    SQL_DELETE_SERVICE("DELETE FROM services WHERE id_service = ?"),
    SQL_UPDATE_SERVICE("UPDATE services SET service = ?, cost = ?, discount = ? WHERE id_service = ?"),
    SQL_SELECT_ORDER_BY_ID("SELECT id_order, order_time, deadline, order_status, " +
                    "mark, id_client, id_cleaner FROM orders WHERE id_order = ?"),
    SQL_ADD_ORDER("INSERT INTO orders VALUES (?, ?, ?, ?, ?, ?, ?)"),
    SQL_UPDATE_ORDER("UPDATE orders SET order_status = ?, id_cleaner = ? WHERE id_order = ?"),
    SQL_SET_MARK("UPDATE orders SET mark = ? WHERE id_order = ?"),
    SQL_FIND_USERS_BY_ROLE("SELECT id_user, login, password, role, name, lastname, " +
                    "phone, address, email FROM users WHERE role = ?"),
    SQL_FIND_CLEANERS_BY_ROLE("SELECT users.id_user, users.login, users.password, users.role, users.name, " +
                    "users.lastname, users.phone, users.address, users.email, cleaners.id_cleaner, " +
                    "cleaners.id_user, cleaners.commission, cleaners.notes " +
                    "FROM cleaners JOIN users ON cleaners.id_user = users.id_user WHERE users.role = ?"),
    SQL_FIND_CLIENTS_BY_ROLE("SELECT users.id_user, users.login, users.password, users.role, users.name, " +
                    "users.lastname, users.phone, users.address, users.email, clients.id_client, " +
                    "clients.id_user, clients.discount, clients.location, clients.relative, clients.notes " +
                    "FROM clients JOIN users ON clients.id_user = users.id_user WHERE users.role = ?"),
    SQL_FIND_ORDERS_BY_CLIENT("SELECT orders.id_order, orders.order_time, orders.deadline, " +
                    "orders.order_status, orders.mark, users.name, users.lastname, users.phone " +
                    "FROM (cleaners JOIN users ON cleaners.id_user = users.id_user) " +
                    "JOIN (orders JOIN clients ON orders.id_client = clients.id_client ) " +
                    "ON cleaners.id_cleaner = orders.id_cleaner WHERE orders.id_client = ?;"),
    SQL_FIND_ORDERS_BY_CLEANER("SELECT orders.id_order, orders.order_time, orders.deadline, " +
                    "orders.order_status, users.name, users.lastname, users.phone, " +
                    "clients.location, clients.relative, clients.notes " +
                    "FROM (clients JOIN users ON clients.id_user = users.id_user) " +
                    "JOIN (orders JOIN cleaners ON orders.id_cleaner = cleaners.id_cleaner ) " +
                    "ON clients.id_client = orders.id_client WHERE orders.id_cleaner = ?");

    private String value;

    Statements(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}