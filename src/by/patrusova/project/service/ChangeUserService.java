package by.patrusova.project.service;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;

import java.sql.SQLException;

public class ChangeUserService {

    public static User getUser(User user) throws ServiceException, SQLException {
        DaoFactory factory = new DaoFactory();
        ProxyConnection connection = null;
        try {
            connection = (ProxyConnection) factory.getConnection();
            connection.setAutoCommit(false);
            user = UserDao.getLoginedUsers().get(user.getId());
//            if (user != null) {
//                UserDao.getLoginedUsers().put(user.getId(), user);
//            }todo узнать как лучше сделать внесение изменений
            connection.commit();
            connection.close();
        } catch (DaoException | SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw new ServiceException(e);
        }
        return user;
    }
}
