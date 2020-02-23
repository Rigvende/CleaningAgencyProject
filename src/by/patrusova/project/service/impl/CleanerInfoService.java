package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.CleanerDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
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
import java.util.Optional;

public class CleanerInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();

    //находим клинера в бд и возвращаем
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        Cleaner cleaner = (Cleaner) entity;
        try {
            CleanerDao dao = factory.createCleanerDao();
            if (dao.update(cleaner)) {
                cleaner = null;
            }
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while updating client's info has occurred. ", e);
            throw new ServiceException(e);
        }
        return cleaner != null ? Optional.of(cleaner) : Optional.empty();
    }

    //создание экземпляра клинера с изменениями, внесёнными админом
    @Override
    public Cleaner createEntity(HttpServletRequest request) {
        Cleaner updatedCleaner = (Cleaner) request.getSession()
                .getAttribute(Attributes.CLEANER.getValue());
        if (!validate(request).containsValue(false)) {
            String commission = request.getParameter(Parameters.COMMISSION.getValue());
            updatedCleaner.setCommission(BigDecimal.valueOf(Double.parseDouble(commission)));
            updatedCleaner.setNotes(request.getParameter(Parameters.NOTES.getValue()));
            return updatedCleaner;
        } else {
            return null;
        }
    }

    //валидация введенных данных
    private Map<String, Boolean> validate(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();
        String commission = request.getParameter(Parameters.COMMISSION.getValue());
        String notes = request.getParameter(Parameters.NOTES.getValue());
        validationMap.put(Parameters.COMMISSION.getValue(),
                NumberValidator.isValidDecimal(commission));
        validationMap.put(Parameters.NOTES.getValue(),
                StringValidator.isValidStringSize(Parameters.NOTES.getValue(), notes));
        return validationMap;
    }

    //находим клинера в бд и возвращаем
    public Cleaner getCleaner(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        Cleaner cleaner = (Cleaner) entity;
        try {
            CleanerDao dao = factory.createCleanerDao();
            cleaner = (Cleaner) dao.findEntityById(cleaner.getIdUser());
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while finding cleaner has occurred. ", e);
            throw new ServiceException(e);
        }
        return cleaner;
    }
}