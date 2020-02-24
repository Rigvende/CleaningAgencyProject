package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import java.sql.SQLException;
import java.util.Optional;

public class CancelOrderService implements Serviceable {

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        Order order;
        order = (Order) entity;
        DaoFactory factory = new DaoFactory();
        try {
            long idClient = order.getIdClient();
            OrderDao dao = factory.createOrderDao();
            order = (Order) dao.findEntityById(order.getId());
            if ((order != null) && (order.getIdClient() == idClient)
                    && (order.getOrderStatus().equals(Order.Status.REGISTERED.getValue()))) {
                return dao.cancelOrder(order) ? Optional.of(order) : Optional.empty();
            } else {
                return Optional.empty();
            }
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
    }
}
