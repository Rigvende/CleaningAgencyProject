package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.BasketDao;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

/**
 * Class for implementation of service logic concerning cancelling order
 * using operations with {@link OrderDao}
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class CancelOrderService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        Order order;
        order = (Order) entity;
        try {
            long idClient = order.getIdClient();
            OrderDao dao = DaoFactory.createOrderDao();
            order = (Order) dao.findEntityById(order.getId());
            if ((order != null) && (order.getIdClient() == idClient)
                    && (order.getOrderStatus().equals(Order.Status.REGISTERED.getValue()))) {
                return dao.cancelOrder(order) ? Optional.of(order) : Optional.empty();
            } else {
                return Optional.empty();
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in CancelOrderService while cancelling order has occurred. ", e);
            throw new ServiceException(e);
        }
    }
}
