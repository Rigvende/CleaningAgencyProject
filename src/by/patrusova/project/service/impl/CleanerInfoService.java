package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.CleanerDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;

public class CleanerInfoService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    public Cleaner doService(AbstractEntity entity) throws ServiceException {
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