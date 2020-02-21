package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.util.stringholder.Statements;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_SERVICES =
                    "SELECT id_service, service, cost, discount FROM services";
    private final static String SQL_SELECT_ID = "SELECT id_service FROM services";

    public ServiceDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        Service service = (Service)entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_ADD_SERVICE.getValue());
            preparedStatement.setLong(1, 0);
            preparedStatement.setString(2, service.getService());
            preparedStatement.setBigDecimal(3, service.getCost());
            preparedStatement.setBigDecimal(4, service.getDiscount());
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot add service. Request to table failed. ", e);
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
        Service service = (Service)entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_DELETE_SERVICE.getValue());
            preparedStatement.setLong(1, service.getId());
            isDeleted = preparedStatement.execute();
            if (!findId(service.getId())) {
                isDeleted = true;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot delete service. Request to table failed. ", e);
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
        Service service = (Service)entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_UPDATE_SERVICE.getValue());
            preparedStatement.setString(1, service.getService());
            preparedStatement.setBigDecimal(2, service.getCost());
            preparedStatement.setBigDecimal(3, service.getDiscount());
            preparedStatement.setLong(4, service.getId());
            isUpdated = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot update service. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isUpdated;
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException, SQLException {
        connection.setAutoCommit(false);
        List<AbstractEntity> services = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_SERVICES);
            while (resultSet.next()) {
                Service service = EntityFactory.createService(resultSet);
                services.add(service);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot find all services. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return services;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        Service service;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_SELECT_SERVICE_BY_ID.getValue());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            service = EntityFactory.createService(resultSet);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot find service by ID. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return service;
    }

    public boolean findId(long id) throws DaoException, SQLException {
        connection.setAutoCommit(false);
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
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot find id_service in DB. Request to table failed.", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return false;
    }
}