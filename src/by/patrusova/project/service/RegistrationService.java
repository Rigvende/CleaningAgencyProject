package by.patrusova.project.service;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import java.sql.SQLException;

public class RegistrationService {

    private static boolean isExist(User user, UserDao dao) throws ServiceException {
        boolean exist;
        try {
            exist = dao.findLogin(user.getLogin());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return exist;
    }

    public static User registerUser(User user) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        try {
            ProxyConnection connection = (ProxyConnection) factory.getConnection();
            connection.setAutoCommit(false);
            UserDao dao = factory.createUserDao(connection);
            if (!isExist(user, dao)) {
                if (dao.addUserToDB(user)) {
                    user = null;
                }
            }
            connection.commit();
            connection.close();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return user;
    }
}