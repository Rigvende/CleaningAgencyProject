package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.EntityFactory;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String SQL_SELECT_USER_BY_LOGIN_PASS =
            "SELECT id_user, login, password, role, name, lastname, phone, " +
                    "address, email FROM users WHERE login = ? AND password = ?";
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT id_user, login, password, role, name, " +
                    "lastname, phone, address, email FROM users";
    private static final String SQL_SELECT_USER_BY_ID =
            "SELECT id_user, login, password, role, name, lastname, phone, " +
                    "address, email FROM users WHERE id_user = ?";
    private final static String SQL_SELECT_LOGIN =
            "SELECT login FROM users";
    private final static String SQL_ADD_USER =
            "INSERT INTO users VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public UserDao(ProxyConnection connection) {
        super(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public boolean create(AbstractEntity entity) {
        return false;
    }

    @Override
    public boolean delete(AbstractEntity entity) {
        return false;
    }

    @Override
    public AbstractEntity update(AbstractEntity entity) {
        return null;
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException {
        List<AbstractEntity> users = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                User user = EntityFactory.createUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            returnConnectionInPool();
        }
        return users;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException {
        User user = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = EntityFactory.createUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            returnConnectionInPool();
        }
        return user;
    }

    public User findEntityByLoginPass(String login, String pass) throws DaoException {
        User user = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN_PASS);
            statement.setString(1, login);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = EntityFactory.createUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            returnConnectionInPool();
        }
        return user;
    }

    public boolean findLogin(String login) throws DaoException {
        Statement statement = connection.createStatement();
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_LOGIN); //проверка при регистрации
            while (resultSet.next()) {
                if (resultSet.getString(1).equals(login)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Request to table failed.");
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            returnConnectionInPool();
        }
        return false;
    }

    public boolean addUserToDB(User user) throws DaoException {
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
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot add user. Request to table failed.");
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
        return isAdded;
    }
}