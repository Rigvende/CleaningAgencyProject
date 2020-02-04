package by.patrusova.project.service;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginService {

    public static boolean checkLogin(User user) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        try {
            ProxyConnection connection = (ProxyConnection) factory.getConnection();
            connection.setAutoCommit(false);
            UserDao dao = factory.createUserDao(connection);
            user = dao.findEntityByLoginPass(user.getLogin(), user.getPassword());
            connection.commit();
            connection.close();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return (user != null);
    }
}