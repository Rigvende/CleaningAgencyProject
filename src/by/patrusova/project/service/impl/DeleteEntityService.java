package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ServiceDao;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import by.patrusova.project.util.stringholder.Attributes;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;

public class DeleteEntityService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public AbstractEntity doService(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        try {
            User user = new User();
            UserDao userDao = factory.createUserDao();
            if (entity instanceof User) {
                if (userDao.delete(entity)) {
                    return null;
                }
            } else if (entity instanceof Cleaner) {
                user.setId(((Cleaner) entity).getIdUser());
                user.setRole(Attributes.CLEANER.getValue());
                if (userDao.delete(user)) {                     //в бд удаляется и юзер, и клинер (каскадом)
                    return null;
                }
            } else if (entity instanceof Client) {
                user.setId(((Client) entity).getIdUser());
                user.setRole(Attributes.CLIENT.getValue());
                if (userDao.delete(user)) {                     //в бд удаляется и юзер, и клиент (каскадом)
                    return null;
                }
            } else {
                ServiceDao serviceDao = factory.createServiceDao();
                if (serviceDao.delete(entity)) {
                    return null;
                }
            }
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot delete entity. Error has occurred. ", e);
            throw new ServiceException(e);
        }
        return entity;
    }
}