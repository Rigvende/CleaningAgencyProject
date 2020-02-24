package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.EntityCreator;
import by.patrusova.project.service.Serviceable;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Parameters;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OrderInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        Order order = (Order) entity;
        try {
            OrderDao dao = factory.createOrderDao();
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
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    //if we need to set mark to order
    public Optional<AbstractEntity> doService(long id, long clientId, int mark) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        try {
            OrderDao dao = factory.createOrderDao();
            Order order = (Order) dao.findEntityById(id);
            if (order.getIdClient() == clientId) {
                if (order.getOrderStatus().equals(Order.Status.DONE.getValue())) {
                    order.setMark(mark);
                    return dao.setMark(order) ? Optional.of(order) : Optional.empty();
                }
            }
            return Optional.empty();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
    }

    //create instance of order with changes
    @Override
    public Optional<AbstractEntity> createEntity(HttpServletRequest request) throws ServiceException {
        Order updatedOrder = (Order) request.getSession()
                .getAttribute(Attributes.ORDER.getValue());
        try {
            if (!validate(request).containsValue(false)) {
                updatedOrder.setIdCleaner(Long.parseLong(request
                        .getParameter(Parameters.ID_CLEANER.getValue())));
                updatedOrder.setOrderStatus(request
                        .getParameter(Parameters.STATUS.getValue()));
                return Optional.of(updatedOrder);
            }
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot create entity(order).");
            throw new ServiceException(e);
        }
        return Optional.empty();
    }

    //validation while creating
    private Map<String, Boolean> validate(HttpServletRequest request) throws SQLException, DaoException {
        Map<String, Boolean> validationMap = new HashMap<>();
        String id = request.getParameter(Parameters.ID_CLEANER.getValue());
        String status = request.getParameter(Parameters.STATUS.getValue());
        validationMap.put(Parameters.ID_CLEANER.getValue(), NumberValidator.isValidCleanerID(id));
        validationMap.put(Parameters.STATUS.getValue(), StringValidator.isValidStatus(status));
        return validationMap;
    }

    //find existing order in DB
    public Order getOrder(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        Order order = (Order) entity;
        try {
            OrderDao dao = factory.createOrderDao();
            order = (Order) dao.findEntityById(order.getId());
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while finding order has occurred. ", e);
            throw new ServiceException(e);
        }
        return order;
    }
}