package by.patrusova.project.dao.impl;

import by.patrusova.project.util.column.CleanerColumn;
import by.patrusova.project.util.column.ClientColumn;
import by.patrusova.project.util.column.UserColumn;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.util.stringholder.Statement;
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
    private static final String SQL_SELECT_ALL_USERS =
            "SELECT id_user, login, password, role, name, " +
                    "lastname, phone, address, email FROM users";
    private final static String SQL_SELECT_LOGIN =
            "SELECT login FROM users";
    private final static String SQL_SELECT_ID = "SELECT id_user FROM users";

    public UserDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in UserDao create method. ", e);
            throw new DaoException(e);
        }
        User user = (User) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statement.SQL_ADD_USER.getValue());
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
            LOGGER.log(Level.ERROR, "Cannot add user. Request to table failed. ", e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,"Cannot do rollback in UserDao create method. ", e);
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
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in UserDao delete method. ", e);
            throw new DaoException(e);
        }
        boolean isDeleted;
        User user = (User) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (by.patrusova.project.util.stringholder.Statement.SQL_DELETE_USER.getValue());
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
                    LOGGER.log(Level.ERROR,"Cannot do rollback in UserDao delete method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot delete user. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in UserDao update method. ", e);
            throw new DaoException(e);
        }
        boolean isUpdated;
        User user = (User) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (by.patrusova.project.util.stringholder.Statement.SQL_UPDATE_USER.getValue());
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
                    LOGGER.log(Level.ERROR,"Cannot do rollback in UserDao update method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot update user. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in UserDao findAll method. ", e);
            throw new DaoException(e);
        }
        List<AbstractEntity> users = new ArrayList<>();
        java.sql.Statement statement = null;
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
                    LOGGER.log(Level.ERROR,"Cannot do rollback in UserDao findAll method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find all users. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in UserDao findEntityById method. ", e);
            throw new DaoException(e);
        }
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (by.patrusova.project.util.stringholder.Statement.SQL_SELECT_USER_BY_ID.getValue());
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
                    LOGGER.log(Level.ERROR,"Cannot do rollback in UserDao findEntityById method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find user by ID. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in UserDao findEntityByLoginPass method. ", e);
            throw new DaoException(e);
        }
        User user = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (by.patrusova.project.util.stringholder.Statement.SQL_SELECT_USER_BY_LOGIN_PASS.getValue());
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
                    LOGGER.log(Level.ERROR,"Cannot do rollback in UserDao findEntityByLoginPass method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find user by login/password. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in UserDao findLogin method. ", e);
            throw new DaoException(e);
        }
        java.sql.Statement statement = connection.createStatement();
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
                    LOGGER.log(Level.ERROR,"Cannot do rollback in UserDao findLogin method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find login in DB. Request to table failed.", e);
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
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in UserDao findUsersByRole method. ", e);
            throw new DaoException(e);
        }
        List<AbstractEntity> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            switch (role) {
                case "cleaner":
                    preparedStatement = connection.prepareStatement
                            (by.patrusova.project.util.stringholder.Statement.SQL_FIND_CLEANERS_BY_ROLE.getValue());
                    preparedStatement.setString(1, role);
                    ResultSet resultSet1 = preparedStatement.executeQuery();
                    while (resultSet1.next()) {
                        User user = new User(resultSet1.getLong(UserColumn.ID_USER.getValue()),
                                resultSet1.getString(UserColumn.LOGIN.getValue()),
                                resultSet1.getString(UserColumn.PASSWORD.getValue()),
                                resultSet1.getString(UserColumn.ROLE.getValue()),
                                resultSet1.getString(UserColumn.NAME.getValue()),
                                resultSet1.getString(UserColumn.LASTNAME.getValue()),
                                resultSet1.getLong(UserColumn.PHONE.getValue()),
                                resultSet1.getString(UserColumn.ADDRESS.getValue()),
                                resultSet1.getString(UserColumn.EMAIL.getValue()),
                                new Cleaner(resultSet1.getLong(CleanerColumn.ID_CLEANER.getValue()),
                                        resultSet1.getBigDecimal(CleanerColumn.COMMISSION.getValue()),
                                        resultSet1.getString(CleanerColumn.NOTES.getValue()),
                                        resultSet1.getLong(CleanerColumn.ID_USER.getValue())));
                        users.add(user);
                    }
                    break;
                case "client":
                    preparedStatement = connection.prepareStatement
                            (by.patrusova.project.util.stringholder.Statement.SQL_FIND_CLIENTS_BY_ROLE.getValue());
                    preparedStatement.setString(1, role);
                    ResultSet resultSet2 = preparedStatement.executeQuery();
                    while (resultSet2.next()) {
                        User user = new User(resultSet2.getLong(UserColumn.ID_USER.getValue()),
                                resultSet2.getString(UserColumn.LOGIN.getValue()),
                                resultSet2.getString(UserColumn.PASSWORD.getValue()),
                                resultSet2.getString(UserColumn.ROLE.getValue()),
                                resultSet2.getString(UserColumn.NAME.getValue()),
                                resultSet2.getString(UserColumn.LASTNAME.getValue()),
                                resultSet2.getLong(UserColumn.PHONE.getValue()),
                                resultSet2.getString(UserColumn.ADDRESS.getValue()),
                                resultSet2.getString(UserColumn.EMAIL.getValue()),
                                new Client(resultSet2.getLong(ClientColumn.ID_CLIENT.getValue()),
                                        resultSet2.getLong(ClientColumn.ID_USER.getValue()),
                                        resultSet2.getBigDecimal(ClientColumn.DISCOUNT.getValue()),
                                        resultSet2.getString(ClientColumn.LOCATION.getValue()),
                                        resultSet2.getString(ClientColumn.RELATIVE.getValue()),
                                        resultSet2.getString(ClientColumn.NOTES.getValue())));
                        users.add(user);
                    }
                    break;
                case "guest":
                case "admin":
                default:
                    preparedStatement = connection.prepareStatement
                            (by.patrusova.project.util.stringholder.Statement.SQL_FIND_USERS_BY_ROLE.getValue());
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
                    LOGGER.log(Level.ERROR,"Cannot do rollback in UserDao findUsersByRole method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find users by role. Request to table failed. ", e);
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
            LOGGER.log(Level.ERROR,"Cannot set autocommit false in UserDao findId method. ", e);
            throw new DaoException(e);
        }
        java.sql.Statement statement = connection.createStatement();
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
                    LOGGER.log(Level.ERROR,"Cannot do rollback in UserDao findId method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR, "Cannot find id_user in DB. Request to table failed.", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return false;
    }
}