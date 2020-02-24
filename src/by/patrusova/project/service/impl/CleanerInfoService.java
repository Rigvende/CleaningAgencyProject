package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.CleanerDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
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

public class CleanerInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();

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
                .getAttribute(Attribute.CLEANER.getValue());
        if (!validate(request).containsValue(false)) {
            String commission = request.getParameter(Parameter.COMMISSION.getValue());
            updatedCleaner.setCommission(BigDecimal.valueOf(Double.parseDouble(commission)));
            updatedCleaner.setNotes(request.getParameter(Parameter.NOTES.getValue()));
            return Optional.of(updatedCleaner);
        } else {
            return Optional.empty();
        }
    }

    //validation
    private Map<String, Boolean> validate(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();
        String commission = request.getParameter(Parameter.COMMISSION.getValue());
        String notes = request.getParameter(Parameter.NOTES.getValue());
        validationMap.put(Parameter.COMMISSION.getValue(),
                NumberValidator.isValidDecimal(commission));
        validationMap.put(Parameter.NOTES.getValue(),
                StringValidator.isValidStringSize(Parameter.NOTES.getValue(), notes));
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