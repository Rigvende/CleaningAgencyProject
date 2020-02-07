package by.patrusova.project.service;

import by.patrusova.project.connection.ProxyConnection;
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
            ProxyConnection connection = (ProxyConnection) factory.getConnection();
            connection.setAutoCommit(false);
            UserDao dao = factory.createUserDao(connection);
            user = dao.findEntityByLoginPass(user.getLogin(), user.getPassword());
            if (user != null) {
                UserDao.getLoginedUsers().put(user.getId(), user);
            }
            connection.commit();
            connection.close();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}