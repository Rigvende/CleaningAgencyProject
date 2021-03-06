package by.patrusova.project.dao.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.entity.impl.ComplexPosition;
import by.patrusova.project.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for actions mostly with {@link BasketPosition} using connections, statements and queries
 * according DAO and database data
 *
 * @version 1.0
 * @autor Marianna Patrusova
 */
public class BasketDao extends AbstractDao<AbstractEntity> {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String SQL_ADD_POSITION =
            "INSERT INTO basket_position VALUES (null, ?, ?);";
    private final static String SQL_SELECT_BASKET_POSITION =
            "SELECT id_basket, id_order, id_service FROM basket_position WHERE id_basket = ?;";
    private final static String SQL_SELECT_BASKET_POSITION_BY_ORDER_ID =
            "SELECT id_basket, id_order, id_service FROM basket_position WHERE id_order = ?;";
    private final static String SQL_DELETE_POSITION_ORDER =
            "DELETE FROM basket_position WHERE id_order = ? AND id_basket = ?;";
    private static final String SQL_SELECT_ALL_BASKET_POSITIONS =
            "SELECT id_basket, id_order, id_service FROM basket_position;";
    private final static String SQL_SELECT_ID = "SELECT id_basket FROM basket_position;";
    private final static String SQL_SELECT_POSITION_BY_ORDER_ID =
            "SELECT basket_position.id_basket, basket_position.id_order, basket_position.id_service, " +
                    "services.service, services.cost, services.sales FROM basket_position " +
                    "JOIN services ON basket_position.id_service = services.id_service\n" +
                    "WHERE basket_position.id_order = ?;";

    public BasketDao(ProxyConnection connection) {
        super(connection);
    }

    @Override
    public boolean create(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in BasketDao create method. ", e);
            throw new DaoException(e);
        }
        BasketPosition position = (BasketPosition) entity;
        boolean isAdded;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_ADD_POSITION);
            preparedStatement.setLong(1, position.getIdOrder());
            preparedStatement.setLong(2, position.getIdService());
            isAdded = preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in BasketDao create method. ", e);
                    throw new DaoException(e);
                }
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
    public boolean delete(AbstractEntity entity) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in BasketDao delete method. ", e);
            throw new DaoException(e);
        }
        boolean isDeleted;
        BasketPosition position = (BasketPosition) entity;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_POSITION_ORDER);
            preparedStatement.setLong(1, position.getIdOrder());
            preparedStatement.setLong(2, position.getId());
            preparedStatement.execute();
            isDeleted = preparedStatement.execute();
            if (!findId(position.getId())) {
                isDeleted = true;
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in BasketDao delete method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot delete position to the basket. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return isDeleted;
    }

    @Override
    public boolean update(AbstractEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AbstractEntity> findAll() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in BasketDao findAll method. ", e);
            throw new DaoException(e);
        }
        List<AbstractEntity> positions = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_BASKET_POSITIONS)) {
                while (resultSet.next()) {
                    BasketPosition position = EntityFactory.createBasketPosition(resultSet);
                    positions.add(position);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in BasketDao findAll method. ", e);
                    throw new DaoException(e);
                }
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
    public AbstractEntity findEntityById(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in BasketDao findEntityById method. ", e);
            throw new DaoException(e);
        }
        BasketPosition position;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BASKET_POSITION);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                position = EntityFactory.createBasketPosition(resultSet);
            }
            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in BasketDao findEntityById method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find basket position by ID. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return position;
    }

    public boolean findId(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in BasketDao findId method. ", e);
            throw new DaoException(e);
        }
        Statement statement = connection.createStatement();
        try (ResultSet resultSet = statement.executeQuery(SQL_SELECT_ID)) {
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
                            "Cannot do rollback in BasketDao findId method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find id_basket in DB. Request to table failed.", e);
            throw new DaoException(e);
        } finally {
            closeStatement(statement);
        }
        return false;
    }

    public List<BasketPosition> findAllByOrderId(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in BasketDao findAllByOrderId method. ", e);
            throw new DaoException(e);
        }
        List<BasketPosition> positions = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_BASKET_POSITION_BY_ORDER_ID);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    BasketPosition position = EntityFactory.createBasketPosition(resultSet);
                    positions.add(position);
                }
            }
            connection.commit();
        } catch (SQLException | DaoException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in BasketDao findAllByOrderId method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find all positions of the basket. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return positions;
    }

    public boolean deleteAllByOrderId(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in BasketDao deleteAllByOrderId method. ", e);
            throw new DaoException(e);
        }
        List<BasketPosition> list = findAllByOrderId(id);
        int count = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_DELETE_POSITION_ORDER);
            for (BasketPosition position : list) {
                preparedStatement.setLong(1, id);
                preparedStatement.setLong(2, position.getId());
                preparedStatement.execute();
                if (!findId(position.getId())) {
                    ++count;
                }
            }
            if (list.size() == count) {
                connection.commit();
                return true;
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in BasketDao deleteAllByOrderId method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot delete position to the basket. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
    }

    public List<ComplexPosition> findBasketByOrderId(long id) throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR,
                    "Cannot set autocommit false in BasketDao findAllByOrderId method. ", e);
            throw new DaoException(e);
        }
        List<ComplexPosition> positions = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_SELECT_POSITION_BY_ORDER_ID);
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ComplexPosition position = EntityFactory.createPositionComplex(resultSet);
                    positions.add(position);
                }
            }
            connection.commit();
        } catch (SQLException | DaoException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    LOGGER.log(Level.ERROR,
                            "Cannot do rollback in BasketDao findAllByOrderId method. ", e);
                    throw new DaoException(e);
                }
            }
            LOGGER.log(Level.ERROR,
                    "Cannot find all positions of the basket. Request to table failed. ", e);
            throw new DaoException(e);
        } finally {
            closeStatement(preparedStatement);
        }
        return positions;
    }
}