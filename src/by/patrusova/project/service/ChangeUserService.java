package by.patrusova.project.service;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.ServiceException;
import java.sql.SQLException;

public class ChangeUserService {

    public static User getUser(User user) throws ServiceException, SQLException {
        DaoFactory factory = new DaoFactory();
//        try {
//            if (user != null) {
//                UserDao.getLoginedUsers().put(user.getId(), user);
//            }todo узнать как лучше сделать внесение изменений
//        } catch (DaoException e) {
//            throw new ServiceException(e);
//        }
        return user;
    }
}