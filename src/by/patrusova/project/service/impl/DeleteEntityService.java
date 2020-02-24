package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.BasketDao;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.dao.impl.ServiceDao;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import by.patrusova.project.util.stringholder.Attributes;
import java.sql.SQLException;
import java.util.Optional;

public class DeleteEntityService implements Serviceable {

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        try {
            User user = new User();
            UserDao userDao = factory.createUserDao();
            if (entity instanceof User) {
                if (userDao.delete(entity)) {                   //delete user from DB
                    return Optional.empty();
                }
            } else if (entity instanceof Cleaner) {
                user.setId(((Cleaner) entity).getIdUser());
                user.setRole(Attributes.CLEANER.getValue());
                if (userDao.delete(user)) {                     //delete user and cleaner from DB (cascade)
                    return Optional.empty();
                }
            } else if (entity instanceof Client) {
                user.setId(((Client) entity).getIdUser());
                user.setRole(Attributes.CLIENT.getValue());
                if (userDao.delete(user)) {                     //delete user and client from DB (cascade)
                    return Optional.empty();
                }
            } else if (entity instanceof Order) {
                    BasketDao basketDao = factory.createBasketDao();
                    if (basketDao.deleteAllByOrderId(((Order) entity).getId())) {
                        OrderDao orderDao = factory.createOrderDao();
                        if (orderDao.delete(entity)) {           //delete order and basket positions from DB both
                            return Optional.empty();
                    }
                }
            } else {
                ServiceDao serviceDao = factory.createServiceDao();
                if (serviceDao.delete(entity)) {                //delete service from DB
                    return Optional.empty();
                }
            }
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
        return Optional.of(entity);
    }
}