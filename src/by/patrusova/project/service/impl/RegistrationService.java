package by.patrusova.project.service.impl;

import by.patrusova.project.dao.AbstractDao;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.EntityCreator;
import by.patrusova.project.service.Serviceable;
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
import java.util.Optional;

public class RegistrationService implements EntityCreator, Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    //регистрация юзера
    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException, SQLException {
        DaoFactory factory = new DaoFactory();
        User user = (User) entity;
        try {
            UserDao dao = factory.createUserDao();
            if (!isExist(user, dao)) {
                if (dao.create(user)) {
                    user = null;
                }
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot register user, exception has occurred. ", e);
            throw new ServiceException(e);
        }
        return user != null ? Optional.of(user) : Optional.empty();
    }

    @Override
    public User createEntity(HttpServletRequest request) throws ServiceException {
        User newUser = new User();
        if (!validate(request).containsValue(false)) {
            newUser.setId(0);
            newUser.setLogin(request.getParameter(Parameters.LOGINREG.getValue()));
            newUser.setPassword(request.getParameter(Parameters.PASSWORDREG.getValue()));
            newUser.setRole(String.valueOf(Role.GUEST)); //пока админ не подтвердит регистрацию
            newUser.setName(request.getParameter(Parameters.FIRSTNAME.getValue()));
            newUser.setLastname(request.getParameter(Parameters.LASTNAME.getValue()));
            newUser.setPhone(Long.parseLong(request.getParameter(Parameters.PHONE.getValue())));
            newUser.setEmail(request.getParameter(Parameters.EMAIL.getValue()));
            newUser.setAddress(request.getParameter(Parameters.ADDRESS.getValue()));
            return newUser;
        } else {
            return null;
        }
    }

    public boolean isExist(AbstractEntity entity, AbstractDao<AbstractEntity> dao) throws ServiceException {
        boolean exist;
        UserDao userDao = (UserDao) dao;
        User user = (User) entity;
        try {
            exist = userDao.findLogin(user.getLogin());
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot check user in DB, exception has occurred. ", e);
            throw new ServiceException(e);
        }
        return exist;
    }

    public Map<String, Boolean> validate(HttpServletRequest request) throws ServiceException {
        Map<String, Boolean> validationMap = new HashMap<>();
        String login = request.getParameter(Parameters.LOGINREG.getValue());
        String password = request.getParameter(Parameters.PASSWORDREG.getValue());
        String passwordRepeated = request.getParameter(Parameters.PASSWORD_AGAIN.getValue());
        String name = request.getParameter(Parameters.FIRSTNAME.getValue());
        String lastname = request.getParameter(Parameters.LASTNAME.getValue());
        String phone = request.getParameter(Parameters.PHONE.getValue());
        String email = request.getParameter(Parameters.EMAIL.getValue());
        String address = request.getParameter(Parameters.ADDRESS.getValue());
        try {
            validationMap.put(Parameters.LOGINREG.getValue(),
                    RegistrationDataValidator.isValidLogin(login));
        } catch (DaoException | SQLException e) {
            LOGGER.log(Level.ERROR, "Cannot validate user, exception has occurred. ", e);
            throw new ServiceException(e);
        }
        validationMap.put(Parameters.PASSWORDREG.getValue(),
                (RegistrationDataValidator.isValidPassword(password)
                && RegistrationDataValidator.isPasswordRepeated(password, passwordRepeated)));
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