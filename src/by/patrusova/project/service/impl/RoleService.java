package by.patrusova.project.service.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ClientDao;
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

public class RoleService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    public User doService(AbstractEntity entity) throws ServiceException {
        User user = (User) entity;
        DaoFactory factory = new DaoFactory();
        try {
            UserDao dao = factory.createUserDao();
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Exception while setting role has occurred.");
            throw new ServiceException(e);
        }
        return user;
    }
}