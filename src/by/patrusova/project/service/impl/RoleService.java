package by.patrusova.project.service.impl;

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
import by.patrusova.project.util.stringholder.Attributes;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Optional;

public class RoleService implements Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) {
        return Optional.empty();
    }

    public Optional<AbstractEntity> doService(long id, String role) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        User user;
        try {
            UserDao userDao = factory.createUserDao();
            user = (User) userDao.findEntityById(id);
            if (user.getRole().equals(Attributes.GUEST.getValue())) {
                user.setRole(role);
                if (!userDao.update(user)) {
                    switch (role) {
                        case "admin":
                            return Optional.of(user);
                        case "client":
                            ClientDao clientDao = factory.createClientDao();
                            Client client = new Client();
                            client.setIdUser(id);
                            return clientDao.create(client) ? Optional.empty() : Optional.of(user);
                        case "cleaner":
                            CleanerDao cleanerDao = factory.createCleanerDao();
                            Cleaner cleaner = new Cleaner();
                            cleaner.setIdUser(id);
                            return cleanerDao.create(cleaner) ? Optional.empty() : Optional.of(user);
                        default:
                            return Optional.empty();
                    }
                }
            } else {
                return Optional.empty();
            }
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Exception while setting role has occurred. ", e);
            throw new ServiceException(e);
        }
        return Optional.of(user);
    }
}