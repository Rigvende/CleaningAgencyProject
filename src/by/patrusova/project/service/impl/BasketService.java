package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.BasketDao;
import by.patrusova.project.dao.impl.CleanerDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

/**
 * Class for implementation of service logic concerning creation of basket positions
 * using operations with {@link BasketDao}
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class BasketService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        BasketPosition position = (BasketPosition) entity;
        try {
            BasketDao dao = DaoFactory.createBasketDao();                          //create basket position
            return dao.create(position) ? Optional.empty() : Optional.of(position);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in BasketService while creating basket position has occurred. ", e);
            throw new ServiceException(e);
        }
    }
}