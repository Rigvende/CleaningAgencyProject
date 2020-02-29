package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

/**
 * Class for implementation of service logic concerning login actions
 * using operations with {@link UserDao}
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class LoginService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        User user = (User) entity;
        try {
            UserDao dao = DaoFactory.createUserDao();
            user = (User) dao.findEntityByLoginPass(user.getLogin(), user.getPassword());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in LoginService while logging in has occurred. ", e);
            throw new ServiceException(e);
        }
        return user != null ? Optional.of(user) : Optional.empty();
    }
}