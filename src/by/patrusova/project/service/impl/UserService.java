package by.patrusova.project.service.impl;

import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.EntityCreator;
import by.patrusova.project.service.Serviceable;
import by.patrusova.project.util.stringholder.Attributes;
import by.patrusova.project.util.stringholder.Parameters;
import by.patrusova.project.validator.RegistrationDataValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserService implements Serviceable, EntityCreator {
    private final static Logger LOGGER = LogManager.getLogger();

    //внесение изменений в юзер-инфо
    @Override
    public User doService(AbstractEntity entity) throws ServiceException, SQLException {
        DaoFactory factory = new DaoFactory();
        User user = (User) entity;
        try {
            UserDao dao = factory.createUserDao();
            if (dao.update(user)) {
                user = null;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot update user's info, exception has occurred.");
            throw new ServiceException(e);
        }
        return user;
    }

    //создание измененного юзера
    @Override
    public User createEntity(HttpServletRequest request) {
        User updatedUser = (User)request.getSession().getAttribute(Attributes.USER.getValue());
        if (!validate(request).containsValue(false)) {
            updatedUser.setName(request.getParameter(Parameters.FIRSTNAME.getValue()));
            updatedUser.setLastname(request.getParameter(Parameters.LASTNAME.getValue()));
            updatedUser.setPhone(Long.parseLong(request.getParameter(Parameters.PHONE.getValue())));
            updatedUser.setAddress(request.getParameter(Parameters.ADDRESS.getValue()));
            updatedUser.setEmail(request.getParameter(Parameters.EMAIL.getValue()));
            return updatedUser;
        } else {
            return null;
        }
    }

    @Override
    public boolean isExist(AbstractEntity entity, AbstractDao<AbstractEntity> dao) throws ServiceException {
        boolean exist;
        UserDao userDao = (UserDao) dao;
        User user = (User) entity;
        try {
            exist = userDao.findLogin(user.getLogin());
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return exist;
    }

    private Map<String, Boolean> validate(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();
        String name = request.getParameter(Parameters.FIRSTNAME.getValue());
        String lastname = request.getParameter(Parameters.LASTNAME.getValue());
        String phone = request.getParameter(Parameters.PHONE.getValue());
        String email = request.getParameter(Parameters.EMAIL.getValue());
        String address = request.getParameter(Parameters.ADDRESS.getValue());
        validationMap.put(Parameters.FIRSTNAME.getValue(),
                StringValidator.isValidStringSize(Parameters.NAME.getValue(), name));
        validationMap.put(Parameters.LASTNAME.getValue(),
                StringValidator.isValidStringSize(Parameters.LASTNAME.getValue(), lastname));
        validationMap.put(Parameters.PHONE.getValue(),
                RegistrationDataValidator.isValidPhone(phone));
        validationMap.put(Parameters.EMAIL.getValue(),
                RegistrationDataValidator.isValidEmail(email)
                        && StringValidator.isValidStringSize(Parameters.EMAIL.getValue(), email));
        validationMap.put(Parameters.ADDRESS.getValue(),
                StringValidator.isValidStringSize(Parameters.ADDRESS.getValue(), address));
        return validationMap;
    }
}