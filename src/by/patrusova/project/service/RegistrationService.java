package by.patrusova.project.service;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import java.sql.SQLException;

public class RegistrationService {

    public static boolean registerUser(User user) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        ProxyConnection connection;
        try {
            connection = (ProxyConnection) factory.getConnection();
            connection.setAutoCommit(false);
            UserDao dao = factory.createUserDao(connection);
            boolean exist = dao.findLogin(user.getLogin());
            if (!exist) {
                boolean check = dao.addUserToDB(user);
                connection.commit();
                connection.close();
                return check;
            }
            connection.commit();
            connection.close();
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return false;
    }
}