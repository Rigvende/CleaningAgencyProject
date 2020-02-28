package by.patrusova.project.dao.impl;

import by.patrusova.project.entity.impl.*;
import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID_CLIENT = "id_client";
    private final static String DISCOUNT = "discount";
    private final static String LOCATION = "location";
    private final static String RELATIVE = "relative";
    private final static String ID_CLEANER = "id_cleaner";
    private final static String ID_USER = "id_user";
    private final static String COMMISSION = "commission";
    private final static String NOTES = "notes";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String ROLE = "role";
    private final static String NAME = "name";
    private final static String LASTNAME = "lastname";
    private final static String PHONE = "phone";
    private final static String ADDRESS = "address";
    private final static String EMAIL = "email";
    private final static String SQL_ADD_USER =
            "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final static String SQL_DELETE_USER =
            "DELETE FROM users WHERE id_user = ? AND role = ?;";
    private final static String SQL_UPDATE_USER =
            "UPDATE users SET name = ?, lastname = ?, role = ?, phone = ?, " +
                    "address = ?, email = ?  WHERE id_user = ?;";
    private final static String SQL_SELECT_USER_BY_ID =
            "SELECT id_user, login, password, role, name, lastname, phone, " +
                    "address, email FROM users WHERE id_user = ?;";
    private final static String SQL_SELECT_USER_BY_LOGIN_PASS =
            "SELECT id_user, login, password, role, name, lastname, phone, " +
                    "address, email FROM users WHERE login = ? AND password = ?;";
    private final static String SQL_FIND_CLEANERS_BY_ROLE =
            "SELECT users.id_user, users.login, users.password, users.role, users.name, " +
                    "users.lastname, users.phone, users.address, users.email, cleaners.id_cleaner, " +
                    "cleaners.id_user, cleaners.commission, cleaners.notes AS cleaner_notes FROM cleaners " +
                    "JOIN users ON cleaners.id_user = users.id_user WHERE users.role = ?;";
    private final static String SQL_FIND_CLIENTS_BY_ROLE =
            "SELECT users.id_user, users.login, users.password, users.role, users.name, " +
                    "users.lastname, users.phone, users.address, users.email, clients.id_client, " +
                    "clients.id_user, clients.discount, clients.location, clients.relative, " +
                    "clients.notes AS client_notes FROM clients JOIN users ON clients.id_user = users.id_user " +
                    "WHERE users.role = ?;";
    private final static String SQL_FIND_USERS_BY_ROLE =
            "SELECT id_user, login, password, role, name, lastname, phone, address, " +
                    "email FROM users WHERE role = ?;";
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT id_user, login, password, role, name, " +
                    "lastname, phone, address, email FROM users;";
    private final static String SQL_SELECT_LOGIN =
            "SELECT login FROM users;";
    private final static String SQL_SELECT_ID =
            "SELECT id_user FROM users;";
    private final static String SQL_FIND_USER_BY_ID_CLIENT =
            "SELECT users.name, users.email FROM (users JOIN clients ON " +
                    "users.id_user = clients.id_user) JOIN orders ON " +
                    "orders.id_client = clients.id_client WHERE orders.id_client = ?;";

    public UserDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in UserDao create method. ", e);
            throw new DaoException(e);
        }
        User user = (User) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_USER);
            preparedStatement.setLong(1, 0);
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getName());
            preparedStatement.setString(6, user.getLastname());
            preparedStatement.setLong(7, user.getPhone());
            preparedStatement.setString(8, user.getAddress());
            preparedStatement.setString(9, user.getEmail());
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot add user. Request to table failed. ", e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in UserDao create method. ", e);
                    throw new DaoException(e);
                }
            }
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
                    "Cannot set autocommit false in UserDao delete method. ", e);
            throw new DaoException(e);
        }
        boolean isDeleted;
        User user = (User) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getRole());
            isDeleted = preparedStatement.execute();
            if (!findId(user.getId())) {
                isDeleted = true;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in UserDao delete method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot delete user. Request to table failed. ", e);
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
                    "Cannot set autocommit false in UserDao update method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        User user = (User) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getRole());
            preparedStatement.setLong(4, user.getPhone());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setLong(7, user.getId());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in UserDao update method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot update user. Request to table failed. ", e);
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
                    "Cannot set autocommit false in UserDao findAll method. ", e);
            throw new DaoException(e);
        }
        List<AbstractEntity> users = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = EntityFactory.createUser(resultSet);
                users.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in UserDao findAll method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find all users. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return users;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in UserDao findEntityById method. ", e);
            throw new DaoException(e);
        }
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = EntityFactory.createUser(resultSet);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in UserDao findEntityById method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find user by ID. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return user;
    }

    public AbstractEntity findEntityByLoginPass(String login, String pass) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in UserDao findEntityByLoginPass method. ", e);
            throw new DaoException(e);
        }
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASS);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = EntityFactory.createUser(resultSet);
            }
            connection.commit();
        } catch (SQLException | DaoException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in UserDao findEntityByLoginPass method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find user by login/password. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return user;
    }

    public boolean findLogin(String login) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in UserDao findLogin method. ", e);
            throw new DaoException(e);
        }
        Statement statement = connection.createStatement();
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_LOGIN); //проверка при регистрации
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(login)) {
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
                            "Cannot do rollback in UserDao findLogin method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find login in DB. Request to table failed.", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return false;
    }

    public List<AbstractEntity> findUsersByRole(String role) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in UserDao findUsersByRole method. ", e);
            throw new DaoException(e);
        }
        List<AbstractEntity> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            switch (role) {
                case "cleaner":
                    preparedStatement = connection.prepareStatement(SQL_FIND_CLEANERS_BY_ROLE);
                    preparedStatement.setString(1, role);
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    while (resultSet1.next()) {
                        ComplexCleaner cleaner = EntityFactory.createCleanerComplex(resultSet1);
                        users.add(cleaner);
                    }
                    break;
                case "client":
                    preparedStatement = connection.prepareStatement(SQL_FIND_CLIENTS_BY_ROLE);
                    preparedStatement.setString(1, role);
                    ResultSet resultSet2 = preparedStatement.executeQuery();
                    while (resultSet2.next()) {
                        ComplexClient client = EntityFactory.createClientComplex(resultSet2);
                        users.add(client);
                    }
                    break;
                case "guest":
                case "admin":
                default:
                    preparedStatement = connection.prepareStatement(SQL_FIND_USERS_BY_ROLE);
                    preparedStatement.setString(1, role);
                    ResultSet resultSet3 = preparedStatement.executeQuery();
                    while (resultSet3.next()) {
                        User user = EntityFactory.createUser(resultSet3);
                        users.add(user);
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
                            "Cannot do rollback in UserDao findUsersByRole method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find users by role. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return users;
    }

    public boolean findId(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in UserDao findId method. ", e);
            throw new DaoException(e);
        }
        Statement statement = connection.createStatement();
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
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in UserDao findId method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find id_user in DB. Request to table failed.", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return false;
    }

    public AbstractEntity findUserByIdClient(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in UserDao findUserByIdClient method. ", e);
            throw new DaoException(e);
        }
        User user = new User();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_ID_CLIENT);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setName(resultSet.getString(NAME));
                user.setEmail(resultSet.getString(EMAIL));
            } else {
                user = null;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in UserDao findUserByIdClient method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find user by login/password. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return user;
    }
}