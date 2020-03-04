package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ServiceDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Service;
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
 * Class for implementation of service logic concerning service's info
 * using operations with {@link ServiceDao}
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class ServiceInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String SERVICE_CHANGED = "servicechange";
    private final static String SERVICE = "service";
    private final static String COST = "cost";
    private final static String SALES = "sales";

    //update existing service
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

    //add new service
    public Optional<AbstractEntity> doServiceAdd(AbstractEntity entity) throws ServiceException {
        Service service = (Service) entity;
        try {
            ServiceDao dao = DaoFactory.createServiceDao();
            return dao.create(service) ? Optional.empty() : Optional.of(service);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    //create instance of service with changes or create new instance of service
    @Override
    public Optional<AbstractEntity> createEntity(HttpServletRequest request) {
        Service service =
                request.getSession().getAttribute(SERVICE) == null ?
                new Service() : (Service) request.getSession().getAttribute(SERVICE);
        if (isValidData(request)) {
            service.setCost(BigDecimal.valueOf(Double.parseDouble
                    (request.getParameter(COST))));
            service.setSales(BigDecimal.valueOf(Double.parseDouble
                    (request.getParameter(SALES))));
            service.setService(request.getParameter(SERVICE_CHANGED));
            return Optional.of(service);
        } else {
            return Optional.empty();
        }
    }

    //validation
    private boolean isValidData(HttpServletRequest request) {
        String service = request.getParameter(SERVICE_CHANGED);
        String cost = request.getParameter(COST);
        String sales = request.getParameter(SALES);
        boolean a = StringValidator.isValidStringSize(SERVICE, service);
        boolean b = NumberValidator.isValidDecimal(sales);
        boolean c = NumberValidator.isValidCost(cost);
        return a && b && c;
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