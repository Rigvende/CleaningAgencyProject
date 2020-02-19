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

public class OrderInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Order doService(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        Order order = (Order) entity;
        try {
            OrderDao dao = factory.createOrderDao();
            return dao.update(order) ? null : order;
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while updating service has occurred. ", e);
            throw new ServiceException(e);
        }
    }

    //создание экземпляра услуги с внесенными изменениями
    @Override
    public Order createEntity(HttpServletRequest request) throws ServiceException {
        Order updatedOrder = (Order) request.getSession()
                             .getAttribute(Attributes.ORDER.getValue());
        try {
            if (!validate(request).containsValue(false)) {
                updatedOrder.setIdCleaner(Long.parseLong(request
                            .getParameter(Parameters.ID_CLEANER.getValue())));
                updatedOrder.setOrderStatus(request
                            .getParameter(Parameters.STATUS.getValue()));
                return updatedOrder;
            }
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot create entity(order).");
            throw new ServiceException(e);
        }
        return null;
    }

    //валидация введенных данных
    private Map<String, Boolean> validate(HttpServletRequest request) throws SQLException, DaoException {
        Map<String, Boolean> validationMap = new HashMap<>();
        String id = request.getParameter(Parameters.ID_CLEANER.getValue());
        String status = request.getParameter(Parameters.STATUS.getValue());
        validationMap.put(Parameters.ID_CLEANER.getValue(), NumberValidator.isValidExistedID(id));
        validationMap.put(Parameters.STATUS.getValue(), StringValidator.isValidStatus(status));
        return validationMap;
    }

    //находим заказ в бд и возвращаем
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