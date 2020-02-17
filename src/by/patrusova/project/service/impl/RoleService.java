package by.patrusova.project.service.impl;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.CleanerDao;
import by.patrusova.project.dao.impl.ClientDao;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import by.patrusova.project.util.MessageManager;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Messages;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class RoleService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public AbstractEntity doService(AbstractEntity entity) throws ServiceException {
        return entity;
    }

    public AbstractEntity doService(long id, String role) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        User user;
        try {
            UserDao userDao = factory.createUserDao();
            user = (User) userDao.findEntityById(id);
            user.setRole(role);
            if(!userDao.update(user)) {
                switch (role) {
                    case "admin":
                        return user;
                    case "client":
                        ClientDao clientDao = factory.createClientDao();
                        Client client = new Client();
                        client.setIdUser(id);
                        return clientDao.create(client) ? null : user;
                    case "cleaner":
                        CleanerDao cleanerDao = factory.createCleanerDao();
                        Cleaner cleaner = new Cleaner();
                        cleaner.setIdUser(id);
                        return cleanerDao.create(cleaner) ? null : user;
                    default:
                        return null;
                }
            }
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while setting role has occurred.");
            throw new ServiceException(e);
        }
        return user;
    }
}