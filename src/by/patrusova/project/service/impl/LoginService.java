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
import java.sql.SQLException;

public class LoginService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    public User doService(AbstractEntity entity) throws ServiceException {
        User user = (User) entity;
        DaoFactory factory = new DaoFactory();
        try {
            UserDao dao = factory.createUserDao();
            user = (User) dao.findEntityByLoginPass(user.getLogin(), user.getPassword());
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot log in, exception has occurred. ", e);
            throw new ServiceException(e);
        }
        return user;
    }
}