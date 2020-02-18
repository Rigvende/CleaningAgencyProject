package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.util.stringholder.Statements;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BasketDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_BASKET_POSITIONS =
                    "SELECT id_basket, id_order, id_service FROM basket_position";

    public BasketDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        BasketPosition position = (BasketPosition) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_ADD_POSITION.getValue());
            preparedStatement.setLong(1, 0);
            preparedStatement.setLong(2, position.getIdOrder());
            preparedStatement.setLong(3, position.getIdService());
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR,
                    "Cannot add position to the basket. Request to table failed. ", e);
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
        BasketPosition position = (BasketPosition) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(Statements.SQL_DELETE_POSITION.getValue());
            preparedStatement.setLong(1, position.getIdService());
            isDeleted = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR, "Cannot delete position to the basket. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isDeleted;
    }

    @Override
    public boolean update(AbstractEntity entity) {
        return false;
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException, SQLException {
        connection.setAutoCommit(false);
        List<AbstractEntity> positions = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_BASKET_POSITIONS);
            while (resultSet.next()) {
                BasketPosition position = EntityFactory.createBasketPosition(resultSet);
                positions.add(position);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find all positions of the basket. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return positions;
    }

    @Override
    public AbstractEntity findEntityById(long id) throws DaoException, SQLException {
        connection.setAutoCommit(false);
        BasketPosition position;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement
                    (Statements.SQL_SELECT_BASKET_POSITION_BY_ID.getValue());
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            position = EntityFactory.createBasketPosition(resultSet);
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find basket position by ID. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return position;
    }
}