package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.EntityCreator;
import by.patrusova.project.service.Serviceable;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Class for implementation of service logic concerning order's info
 * using operations with {@link OrderDao}
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class OrderInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String ID_CLEANER = "id_cleaner";
    private final static String ORDER = "order";
    private final static String STATUS = "status";

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        Order order = (Order) entity;
        try {
            OrderDao dao = DaoFactory.createOrderDao();
            if (order.getOrderStatus().equals(Order.Status.NEW.getValue())) {       //create order in DB
                if (!dao.createNew(order)) {
                    order = (Order) dao.findNew(order);
                    return Optional.of(order);
                } else {
                    return Optional.empty();
                }
            } else {
                return dao.update(order) ? Optional.empty() : Optional.of(order);   //update order in DB
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in OrderInfoService while operating with order has occurred. ", e);
            throw new ServiceException(e);
        }
    }

    //set mark to order
    public Optional<AbstractEntity> doService(long id, long clientId, int mark) throws ServiceException {
        try {
            OrderDao dao = DaoFactory.createOrderDao();
            Order order = (Order) dao.findEntityById(id);
            if (order.getIdClient() == clientId) {
                if (order.getOrderStatus().equals(Order.Status.DONE.getValue())) {
                    order.setMark(mark);
                    return dao.setMark(order) ? Optional.of(order) : Optional.empty();
                }
            }
            return Optional.empty();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in OrderInfoService while setting mark has occurred. ", e);
            throw new ServiceException(e);
        }
    }

    //count total cost for order
    public BigDecimal doService(long id) throws ServiceException {
        try {
            OrderDao dao = DaoFactory.createOrderDao();
            return dao.findCost(id);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in OrderInfoService while counting total cost has occurred. ", e);
            throw new ServiceException(e);
        }
    }

    //create instance of order with changes
    @Override
    public Optional<AbstractEntity> createEntity(HttpServletRequest request) throws ServiceException {
        Order updatedOrder = (Order) request.getSession()
                .getAttribute(ORDER);
        if (isValidData(request)) {
            updatedOrder.setIdCleaner(Long.parseLong(request.getParameter(ID_CLEANER)));
            updatedOrder.setOrderStatus(request.getParameter(STATUS));
            return Optional.of(updatedOrder);
        }
        return Optional.empty();
    }

    //validation while creating
    private boolean isValidData(HttpServletRequest request) throws ServiceException {
        String id = request.getParameter(ID_CLEANER);
        String status = request.getParameter(STATUS);
        boolean a = NumberValidator.isValidCleanerID(id);
        boolean b = StringValidator.isValidStatus(status);
        return a && b;
    }

    //get existing order in DB
    public Order getOrder(AbstractEntity entity) throws ServiceException {
        Order order = (Order) entity;
        try {
            OrderDao dao = DaoFactory.createOrderDao();
            order = (Order) dao.findEntityById(order.getId());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Exception while finding order has occurred. ", e);
            throw new ServiceException(e);
        }
        return order;
    }

    public Optional<AbstractEntity> placeOrder(AbstractEntity entity) throws ServiceException {
        Order order = (Order) entity;
        try {
            OrderDao dao = DaoFactory.createOrderDao();
            return dao.place(order) ? Optional.empty() : Optional.of(order);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Exception while placing order has occurred. ", e);
            throw new ServiceException(e);
        }
    }
}