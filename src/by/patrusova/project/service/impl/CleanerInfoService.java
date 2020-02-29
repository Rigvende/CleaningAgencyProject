package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.BasketDao;
import by.patrusova.project.dao.impl.CleanerDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Cleaner;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Class for implementation of service logic concerning cleaner's info
 * using operations with {@link CleanerDao}
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class CleanerInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String COMMISSION = "commission";
    private final static String NOTES = "notes";

    //update cleaner in DB
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        Cleaner cleaner = (Cleaner) entity;
        try {
            CleanerDao dao = DaoFactory.createCleanerDao();
            if (dao.update(cleaner)) {
                cleaner = null;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in CleanerInfoService while updating cleaner has occurred. ", e);
            throw new ServiceException(e);
        }
        return cleaner != null ? Optional.of(cleaner) : Optional.empty();
    }

    //create instance of cleaner with admin's changes
    @Override
    public Optional<AbstractEntity> createEntity(HttpServletRequest request) {
        Cleaner updatedCleaner = (Cleaner) request.getSession()
                .getAttribute(Role.CLEANER.getValue());
        if (!validate(request).containsValue(false)) {
            String commission = request.getParameter(COMMISSION);
            updatedCleaner.setCommission(BigDecimal.valueOf(Double.parseDouble(commission)));
            updatedCleaner.setNotes(request.getParameter(NOTES));
            return Optional.of(updatedCleaner);
        } else {
            return Optional.empty();
        }
    }

    //validation
    private Map<String, Boolean> validate(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();
        String commission = request.getParameter(COMMISSION);
        String notes = request.getParameter(NOTES);
        validationMap.put(COMMISSION, NumberValidator.isValidDecimal(commission));
        validationMap.put(NOTES, StringValidator.isValidStringSize(NOTES, notes));
        return validationMap;
    }

    //get cleaner from DB
    public Cleaner getCleaner(AbstractEntity entity) throws ServiceException {
        Cleaner cleaner = (Cleaner) entity;
        try {
            CleanerDao dao = DaoFactory.createCleanerDao();
            cleaner = (Cleaner) dao.findEntityById(cleaner.getIdUser());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Exception while finding cleaner has occurred. ", e);
            throw new ServiceException(e);
        }
        return cleaner;
    }
}