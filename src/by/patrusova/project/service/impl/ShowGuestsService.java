package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.service.Serviceable;
import java.sql.SQLException;
import java.util.List;

public class ShowGuestsService implements Serviceable {

    @Override
    public AbstractEntity doService(AbstractEntity entity) {
        return null;
    }

    public List<User> doService() throws DaoException, SQLException {
        DaoFactory factory = new DaoFactory();
        UserDao dao = factory.createUserDao();
        return dao.findGuests();
    }
}