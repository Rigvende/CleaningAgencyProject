package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.BasketDao;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.dao.impl.ServiceDao;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.*;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class DeleteEntityService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        try {
            User user = new User();
            UserDao userDao = DaoFactory.createUserDao();
            BasketDao basketDao = DaoFactory.createBasketDao();
            if (entity instanceof User) {
                if (userDao.delete(entity)) {                   //delete user from DB
                    return Optional.empty();
                }
            } else if (entity instanceof Cleaner) {
                user.setId(((Cleaner) entity).getIdUser());
                user.setRole(Role.CLEANER.getValue());
                if (userDao.delete(user)) {                     //delete user and cleaner from DB (cascade)
                    return Optional.empty();
                }
            } else if (entity instanceof Client) {
                user.setId(((Client) entity).getIdUser());
                user.setRole(Role.CLIENT.getValue());
                if (userDao.delete(user)) {                     //delete user and client from DB (cascade)
                    return Optional.empty();
                }
            } else if (entity instanceof Order) {
                if (basketDao.deleteAllByOrderId(((Order) entity).getId())) {
                    OrderDao orderDao = DaoFactory.createOrderDao();
                    if (orderDao.delete(entity)) {              //delete order and basket positions from DB both
                        return Optional.empty();
                    }
                }
            } else if (entity instanceof BasketPosition) {
                if (basketDao.delete(entity)) {                 //delete basket position from DB
                    return Optional.empty();
                }
            } else {
                ServiceDao serviceDao = DaoFactory.createServiceDao();
                if (serviceDao.delete(entity)) {                //delete service from DB
                    return Optional.empty();
                }
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in DeleteEntityService while deleting entity has occurred. ", e);
            throw new ServiceException(e);
        }
        return Optional.of(entity);
    }

    public boolean doService(long idClient) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.createOrderDao();
            long id = orderDao.findNew(idClient);
            if (id != 0) {
                Order order = new Order();
                order.setId(id);
                BasketDao basketDao = DaoFactory.createBasketDao();
                if (basketDao.deleteAllByOrderId(id)) {
                    return orderDao.delete(order);
                }
                return false;
            }
            return true;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}