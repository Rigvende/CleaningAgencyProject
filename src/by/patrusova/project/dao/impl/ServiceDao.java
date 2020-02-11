package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.EntityFactory;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.util.PreparedStatements;
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

    public ServiceDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException {
        Service service = (Service)entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("add_service");
            preparedStatement.setLong(1, 0);
            preparedStatement.setString(2, service.getService());
            preparedStatement.setBigDecimal(3, service.getCost());
            preparedStatement.setBigDecimal(4, service.getDiscount());
            isAdded = preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot add service. Request to table failed.");
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
        return isAdded;
    }

    @Override
    public boolean delete(AbstractEntity entity) throws DaoException {
        Service service = (Service)entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("delete_service");
            preparedStatement.setLong(1, service.getId());
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
        Service service = (Service)entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("update_service");
            preparedStatement.setString(1, service.getService());
            preparedStatement.setBigDecimal(2, service.getCost());
            preparedStatement.setBigDecimal(3, service.getDiscount());
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
        List<AbstractEntity> services = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_SERVICES);
            while (resultSet.next()) {
                Service service = EntityFactory.createService(resultSet);
                services.add(service);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
            returnConnectionInPool();
        }
        return services;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException {
        Service service;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = PreparedStatements.useStatements(connection).get("select_service");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            service = EntityFactory.createService(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "DAO exception (request or table failed): ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
            returnConnectionInPool();
        }
        return service;
    }
}
