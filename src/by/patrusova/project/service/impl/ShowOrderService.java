package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.ComplexOrder;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class ShowOrderService {

    private final static Logger LOGGER = LogManager.getLogger();

    public List<ComplexOrder> doService(String role, AbstractEntity entity) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.createOrderDao();
            switch (role) {
                case "client":
                    Client client = (Client) entity;                        //find orders for client
                    return orderDao.findOrdersById(role, client.getId());
                case "cleaner":
                    Cleaner cleaner = (Cleaner) entity;                     //find orders for cleaner
                    return orderDao.findOrdersById(role, cleaner.getId());
                default:
                    return new ArrayList<>();
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in ShowOrderService while finding orders has occurred. ", e);
            throw new ServiceException(e);
        }
    }
}
