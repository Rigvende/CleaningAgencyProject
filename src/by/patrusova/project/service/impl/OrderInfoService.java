package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.EntityCreator;
import by.patrusova.project.service.Serviceable;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Parameter;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();

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

    //create instance of order with changes
    @Override
    public Optional<AbstractEntity> createEntity(HttpServletRequest request) throws ServiceException {
        Order updatedOrder = (Order) request.getSession()
                .getAttribute(Attribute.ORDER.getValue());
        if (!validate(request).containsValue(false)) {
            updatedOrder.setIdCleaner(Long.parseLong(request
                    .getParameter(Parameter.ID_CLEANER.getValue())));
            updatedOrder.setOrderStatus(request
                    .getParameter(Parameter.STATUS.getValue()));
            return Optional.of(updatedOrder);
        }
        return Optional.empty();
    }

    //validation while creating
    private Map<String, Boolean> validate(HttpServletRequest request) throws ServiceException {
        Map<String, Boolean> validationMap = new HashMap<>();
        String id = request.getParameter(Parameter.ID_CLEANER.getValue());
        String status = request.getParameter(Parameter.STATUS.getValue());
        validationMap.put(Parameter.ID_CLEANER.getValue(), NumberValidator.isValidCleanerID(id));
        validationMap.put(Parameter.STATUS.getValue(), StringValidator.isValidStatus(status));
        return validationMap;
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
}