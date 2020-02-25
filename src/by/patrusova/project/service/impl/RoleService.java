package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.CleanerDao;
import by.patrusova.project.dao.impl.ClientDao;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Cleaner;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Optional;

public class RoleService {

    private final static Logger LOGGER = LogManager.getLogger();

    public Optional<AbstractEntity> doService(long id, String role) throws ServiceException {
        User user;
        try {
            UserDao userDao = DaoFactory.createUserDao();
            user = (User) userDao.findEntityById(id);
            if (user.getRole().equals(Role.GUEST.getValue())) {
                user.setRole(role);
                if (!userDao.update(user)) {
                    switch (role) {
                        case "admin":
                            return Optional.of(user);
                        case "client":
                            ClientDao clientDao = DaoFactory.createClientDao();
                            Client client = new Client();
                            client.setIdUser(id);
                            return clientDao.create(client) ? Optional.empty() : Optional.of(user);
                        case "cleaner":
                            CleanerDao cleanerDao = DaoFactory.createCleanerDao();
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
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in RoleService while changing role has occurred. ", e);
            throw new ServiceException(e);
        }
        return Optional.of(user);
    }
}