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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowOrderService implements Serviceable {

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) {
        return Optional.empty();
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
                    return new ArrayList<>();
            }
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
