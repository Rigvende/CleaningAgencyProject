package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.dao.impl.ServiceDao;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.service.Serviceable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowService implements Serviceable {

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) {
        return Optional.empty();
    }

    public List<AbstractEntity> doService(String condition) throws DaoException, SQLException {
        DaoFactory factory = new DaoFactory();
        switch (condition) {
            case "guest":
            case "admin":
            case "client":
            case "cleaner":
                UserDao userDao = factory.createUserDao();
                return userDao.findUsersByRole(condition);
            case "order":
                OrderDao orderDao = factory.createOrderDao();
                return orderDao.findAll();
            case "catalogue":
                ServiceDao serviceDao = factory.createServiceDao();
                return serviceDao.findAll();
            default:
                return new ArrayList<>();
        }
    }
}