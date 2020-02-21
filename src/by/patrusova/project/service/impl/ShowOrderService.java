package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.*;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.OrderComplex;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;
import java.util.List;

public class ShowOrderService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public AbstractEntity doService(AbstractEntity entity) {
        return null;
    }

    public List<OrderComplex> doService(String role, AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        try {
            OrderDao orderDao = factory.createOrderDao();
            switch (role) {
                case "client":
                    Client client = (Client) entity;
                    return orderDao.findOrdersById(role, client.getId());
                case "cleaner":
                    Cleaner cleaner = (Cleaner) entity;
                    return orderDao.findOrdersById(role, cleaner.getId());
                default:
                    return null;
            }
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "Exception has occurred while finding orders was processing. ", e);
            throw new ServiceException(e);
        }
    }
}