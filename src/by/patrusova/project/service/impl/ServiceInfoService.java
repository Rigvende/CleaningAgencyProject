package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ServiceDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Service;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ServiceInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        Service service = (Service) entity;
        try {
            ServiceDao dao = DaoFactory.createServiceDao();
            return dao.update(service) ? Optional.empty() : Optional.of(service);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in ServiceInfoService while updating service has occurred. ", e);
            throw new ServiceException(e);
        }
    }

    //add service
    public Optional<AbstractEntity> doServiceAdd(AbstractEntity entity) throws ServiceException {
        Service service = (Service) entity;
        try {
            ServiceDao dao = DaoFactory.createServiceDao();
            return dao.create(service) ? Optional.empty() : Optional.of(service);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    //create instance of service with changes
    @Override
    public Optional<AbstractEntity> createEntity(HttpServletRequest request) {
        Service updatedService = (Service) request.getSession()
                .getAttribute(Attribute.SERVICE.getValue());
        if (!validate(request).containsValue(false)) {
            updatedService.setCost(BigDecimal.valueOf(Double.parseDouble
                    (request.getParameter(Parameter.COST.getValue()))));
            updatedService.setSales(BigDecimal.valueOf(Double.parseDouble
                    (request.getParameter(Parameter.SALES.getValue()))));
            updatedService.setService(request.getParameter(Parameter.SERVICECHANGE.getValue()));
            return Optional.of(updatedService);
        } else {
            return Optional.empty();
        }
    }

    //create new service in DB
    public Optional<AbstractEntity> createNewEntity(HttpServletRequest request) {
        Service newService = new Service();
        if (!validate(request).containsValue(false)) {
            newService.setId(0);
            newService.setCost(BigDecimal.valueOf(Double.parseDouble
                    (request.getParameter(Parameter.COST.getValue()))));
            newService.setSales(BigDecimal.valueOf(Double.parseDouble
                    (request.getParameter(Parameter.SALES.getValue()))));
            newService.setService(request.getParameter(Parameter.SERVICECHANGE.getValue()));
            return Optional.of(newService);
        } else {
            return Optional.empty();
        }
    }

    //validation
    private Map<String, Boolean> validate(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();
        String service = request.getParameter(Parameter.SERVICECHANGE.getValue());
        String cost = request.getParameter(Parameter.COST.getValue());
        String sales = request.getParameter(Parameter.SALES.getValue());
        validationMap.put(Parameter.SERVICECHANGE.getValue(),
                StringValidator.isValidStringSize(Parameter.SERVICE.getValue(), service));
        validationMap.put(Parameter.SALES.getValue(),
                NumberValidator.isValidDecimal(sales));
        validationMap.put(Parameter.COST.getValue(),
                NumberValidator.isValidCost(cost));
        return validationMap;
    }

    //get existing service from DB
    public Service getService(AbstractEntity entity) throws ServiceException {
        Service service = (Service) entity;
        try {
            ServiceDao dao = DaoFactory.createServiceDao();
            service = (Service) dao.findEntityById(service.getId());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Exception while finding service has occurred. ", e);
            throw new ServiceException(e);
        }
        return service;
    }
}