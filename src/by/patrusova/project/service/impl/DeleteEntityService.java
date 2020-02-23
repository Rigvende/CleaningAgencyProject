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
import java.util.Optional;

public class DeleteEntityService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        try {
            User user = new User();
            UserDao userDao = factory.createUserDao();
            if (entity instanceof User) {
                if (userDao.delete(entity)) {
                    return Optional.empty();
                }
            } else if (entity instanceof Cleaner) {
                user.setId(((Cleaner) entity).getIdUser());
                user.setRole(Attributes.CLEANER.getValue());
                if (userDao.delete(user)) {                     //в бд удаляется и юзер, и клинер (каскадом)
                    return Optional.empty();
                }
            } else if (entity instanceof Client) {
                user.setId(((Client) entity).getIdUser());
                user.setRole(Attributes.CLIENT.getValue());
                if (userDao.delete(user)) {                     //в бд удаляется и юзер, и клиент (каскадом)
                    return Optional.empty();
                }
            } else {
                ServiceDao serviceDao = factory.createServiceDao();
                if (serviceDao.delete(entity)) {
                    return Optional.empty();
                }
            }
        } catch (SQLException | DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot delete entity. Error has occurred. ", e);
            throw new ServiceException(e);
        }
        return Optional.of(entity);
    }
}