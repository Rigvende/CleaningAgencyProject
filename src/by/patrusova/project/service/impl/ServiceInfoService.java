package by.patrusova.project.service.impl;

import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ServiceDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Service;
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
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ServiceInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Service doService(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        Service service = (Service) entity;
        try {
            ServiceDao dao = factory.createServiceDao();
            return dao.update(service) ? null : service;
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while updating service has occurred. ", e);
            throw new ServiceException(e);
        }
    }

    public Service doServiceAdd(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        Service service = (Service) entity;
        try {
            ServiceDao dao = factory.createServiceDao();
            return dao.create(service) ? null : service;
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while updating service has occurred. ", e);
            throw new ServiceException(e);
        }
    }

    public boolean isExist(AbstractEntity entity, AbstractDao<AbstractEntity> dao)
            throws SQLException, DaoException {
        Service service = (Service) entity;
        return dao.findEntityById(service.getId()) != null;
    }

    //создание экземпляра услуги с внесенными изменениями
    @Override
    public Service createEntity(HttpServletRequest request) {
        Service updatedService = (Service) request.getSession()
                .getAttribute(Attributes.SERVICE.getValue());
        if (!validate(request).containsValue(false)) {
            updatedService.setCost(BigDecimal.valueOf(Double.parseDouble
                    (request.getParameter(Parameters.COST.getValue()))));
            updatedService.setDiscount(BigDecimal.valueOf(Double.parseDouble
                    (request.getParameter(Parameters.DISCOUNT.getValue()))));
            updatedService.setService(request.getParameter(Parameters.SERVICECHANGE.getValue()));
            return updatedService;
        } else {
            return null;
        }
    }

    //добавление новой услуги
    public Service createNewEntity(HttpServletRequest request) {
        Service newService = new Service();
        if (!validate(request).containsValue(false)) {
            newService.setId(0);
            newService.setCost(BigDecimal.valueOf(Double.parseDouble
                    (request.getParameter(Parameters.COST.getValue()))));
            newService.setDiscount(BigDecimal.valueOf(Double.parseDouble
                    (request.getParameter(Parameters.DISCOUNT.getValue()))));
            newService.setService(request.getParameter(Parameters.SERVICECHANGE.getValue()));
            return newService;
        } else {
            return null;
        }
    }

    //валидация введенных данных
    private Map<String, Boolean> validate(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();
        String service = request.getParameter(Parameters.SERVICECHANGE.getValue());
        String cost = request.getParameter(Parameters.COST.getValue());
        String discount = request.getParameter(Parameters.DISCOUNT.getValue());
        validationMap.put(Parameters.SERVICECHANGE.getValue(),
                StringValidator.isValidStringSize(Parameters.SERVICE.getValue(), service));
        validationMap.put(Parameters.DISCOUNT.getValue(),
                NumberValidator.isValidDecimal(discount));
        validationMap.put(Parameters.COST.getValue(),
                NumberValidator.isValidCost(cost));
        return validationMap;
    }

    //находим услугу в бд и возвращаем
    public Service getService(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        Service service = (Service) entity;
        try {
            ServiceDao dao = factory.createServiceDao();
            service = (Service) dao.findEntityById(service.getId());
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while finding service has occurred. ", e);
            throw new ServiceException(e);
        }
        return service;
    }
}