package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.Serviceable;
import java.sql.SQLException;
import java.util.Optional;

public class LoginService implements Serviceable {

    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        User user = (User) entity;
        DaoFactory factory = new DaoFactory();
        try {
            UserDao dao = factory.createUserDao();
            user = (User) dao.findEntityByLoginPass(user.getLogin(), user.getPassword());
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
        return user != null ? Optional.of(user) : Optional.empty();
    }
}