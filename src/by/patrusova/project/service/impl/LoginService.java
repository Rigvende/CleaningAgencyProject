package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import java.sql.SQLException;

public class LoginService {

    public static User checkLogin(User user) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        try {
            UserDao dao = factory.createUserDao();
            user = (User) dao.findEntityByLoginPass(user.getLogin(), user.getPassword());
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}