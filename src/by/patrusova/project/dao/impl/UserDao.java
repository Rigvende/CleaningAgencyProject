package by.patrusova.project.dao.impl;

import by.patrusova.project.util.stringholder.PreparedStatements;
import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
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
    private final static String CREATE = "add_user";
    private final static String DELETE = "delete_user";
    private final static String UPDATE = "update_user";
    private final static String FIND_ENTITY = "find_user";
    private final static String CHECK_LOGIN = "check_login";
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT id_user, login, password, role, name, " +
                    "lastname, phone, address, email FROM users";
    private final static String SQL_SELECT_LOGIN =
            "SELECT login FROM users";

    public UserDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        User user = (User) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get(CREATE);
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
            LOGGER.log(Level.ERROR, "Cannot add user. Request to table failed.");
            if (connection != null) {
                connection.rollback();
            }
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isAdded;
    }

    @Override
    public boolean delete(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        boolean isDeleted;
        User user = (User) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get(DELETE);
            preparedStatement.setString(1, user.getLogin());
            isDeleted = preparedStatement.execute();
            connection.commit();
        } catch (SQLException | DaoException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isDeleted;
    }

    @Override
    public boolean update(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        boolean isUpdated;
        User user = (User) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get(UPDATE);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setLong(3, user.getPhone());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(6, user.getId());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException | DaoException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException, SQLException {
        connection.setAutoCommit(false);
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
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return users;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get(FIND_ENTITY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = EntityFactory.createUser(resultSet);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return user;
    }

    public AbstractEntity findEntityByLoginPass(String login, String pass) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get(CHECK_LOGIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = EntityFactory.createUser(resultSet);
            }
            connection.commit();
        } catch (SQLException | DaoException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return user;
    }

    public boolean findLogin(String login) throws DaoException, SQLException {
        connection.setAutoCommit(false);
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
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Request to table failed.");
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return false;
    }
}