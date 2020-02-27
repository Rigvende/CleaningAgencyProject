package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.BasketDao;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.dao.impl.ServiceDao;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.entity.impl.ComplexPosition;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowService {

    private final static Logger LOGGER = LogManager.getLogger();

    //find entity lists in DB
    public List<AbstractEntity> doService(String condition) throws ServiceException {
        try {
            switch (condition) {
                case "guest":
                case "admin":
                case "client":
                case "cleaner":
                    UserDao userDao = DaoFactory.createUserDao();
                    return userDao.findUsersByRole(condition);
                case "orders":
                    OrderDao orderDao = DaoFactory.createOrderDao();
                    return orderDao.findAll();
                case "catalogue":
                    ServiceDao serviceDao = DaoFactory.createServiceDao();
                    return serviceDao.findAll();
                default:
                    return new ArrayList<>();
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in ShowService while finding entities has occurred. ", e);
            throw new ServiceException(e);
        }
    }

    //find all positions considering order
    public List<ComplexPosition> doService(long id) throws ServiceException {
        try {
            BasketDao basketDao = DaoFactory.createBasketDao();
            return basketDao.findBasketByOrderId(id);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in ShowService while finding positions has occurred. ", e);
            throw new ServiceException(e);
        }
    }
}