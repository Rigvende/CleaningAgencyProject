package by.patrusova.project.dao.impl;

import by.patrusova.project.util.PreparedStatements;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT id_user, login, password, role, name, " +
                    "lastname, phone, address, email FROM users";
    private final static String SQL_SELECT_LOGIN =
            "SELECT login FROM users";

    public UserDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException {
        User user = (User) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("add_user");
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
//            returnConnectionInPool();todo конекшн возвращается в пул вызывающим методом логинсервис
        }
        return isAdded;
    }

    @Override
    public boolean delete(AbstractEntity entity) throws DaoException {
        User user = (User) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("delete_user");
            preparedStatement.setString(1, user.getLogin());
            return preparedStatement.execute();
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
    }

    @Override
    public boolean update(AbstractEntity entity) throws DaoException {
        User user = (User) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("update_user");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setLong(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getAddress());
            preparedStatement.setString(6, user.getLogin());
            return preparedStatement.execute();
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
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
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("find_user");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = EntityFactory.createUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
        return user;
    }

    public AbstractEntity findEntityByLoginPass(String login, String pass) throws DaoException {
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("check_login");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = EntityFactory.createUser(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
//            returnConnectionInPool(); todo конекшн возвращается в пул вызывающим методом логинсервис
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
//            returnConnectionInPool();todo конекшн возвращается в пул вызывающим методом логинсервис
        }
        return false;
    }
}