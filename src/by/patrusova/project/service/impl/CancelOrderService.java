package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;

public class CancelOrderService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public AbstractEntity doService(AbstractEntity entity) throws ServiceException {
        Order order;
        order = (Order) entity;
        DaoFactory factory = new DaoFactory();
        try {
            OrderDao dao = factory.createOrderDao();
            order = (Order) dao.findEntityById(order.getId());
            if (order != null && order.getOrderStatus().equals(Order.Status.REGISTERED.getValue())) {
                return dao.cancelOrder(order) ? order : null;
            } else {
                return null;
            }
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "Exception while cancelling order has occurred. ", e);
            throw new ServiceException(e);
        }
    }
}
