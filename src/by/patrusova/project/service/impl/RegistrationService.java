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
import by.patrusova.project.util.stringholder.Parameter;
import by.patrusova.project.validator.RegistrationDataValidator;
import by.patrusova.project.validator.StringValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RegistrationService implements EntityCreator, Serviceable {

    private final static Logger LOGGER = LogManager.getLogger();

    //register user
    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        User user = (User) entity;
        try {
            UserDao dao = DaoFactory.createUserDao();
            if (!isExist(user, dao)) {
                if (dao.create(user)) {
                    user = null;
                }
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in RegistrationService while registering user has occurred. ", e);
            throw new ServiceException(e);
        }
        return user != null ? Optional.of(user) : Optional.empty();
    }

    private boolean isExist(AbstractEntity entity, AbstractDao<AbstractEntity> dao) throws ServiceException {
        boolean exist;
        UserDao userDao = (UserDao) dao;
        User user = (User) entity;
        try {
            exist = userDao.findLogin(user.getLogin());
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot check user in DB, exception has occurred. ", e);
            throw new ServiceException(e);
        }
        return exist;
    }

    @Override
    public Optional<AbstractEntity> createEntity(HttpServletRequest request) throws ServiceException {
        User newUser = new User();
        if (!validate(request).containsValue(false)) {
            newUser.setId(0);
            newUser.setLogin(request.getParameter(Parameter.LOGINREG.getValue()));
            newUser.setPassword(request.getParameter(Parameter.PASSWORDREG.getValue()));
            newUser.setRole(String.valueOf(Role.GUEST));            //while admin does not confirm registration
            newUser.setName(request.getParameter(Parameter.FIRSTNAME.getValue()));
            newUser.setLastname(request.getParameter(Parameter.LASTNAME.getValue()));
            newUser.setPhone(Long.parseLong(request.getParameter(Parameter.PHONE.getValue())));
            newUser.setEmail(request.getParameter(Parameter.EMAIL.getValue()));
            newUser.setAddress(request.getParameter(Parameter.ADDRESS.getValue()));
            return Optional.of(newUser);
        } else {
            return Optional.empty();
        }
    }

    public Map<String, Boolean> validate(HttpServletRequest request) throws ServiceException {
        Map<String, Boolean> validationMap = new HashMap<>();
        String login = request.getParameter(Parameter.LOGINREG.getValue());
        String password = request.getParameter(Parameter.PASSWORDREG.getValue());
        String passwordRepeated = request.getParameter(Parameter.PASSWORD_AGAIN.getValue());
        String name = request.getParameter(Parameter.FIRSTNAME.getValue());
        String lastname = request.getParameter(Parameter.LASTNAME.getValue());
        String phone = request.getParameter(Parameter.PHONE.getValue());
        String email = request.getParameter(Parameter.EMAIL.getValue());
        String address = request.getParameter(Parameter.ADDRESS.getValue());
        try {
            validationMap.put(Parameter.LOGINREG.getValue(),
                    RegistrationDataValidator.isValidLogin(login));
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot validate user, exception has occurred. ", e);
            throw new ServiceException(e);
        }
        validationMap.put(Parameter.PASSWORDREG.getValue(),
                (RegistrationDataValidator.isValidPassword(password)
                && RegistrationDataValidator.isPasswordRepeated(password, passwordRepeated)));
        validationMap.put(Parameter.FIRSTNAME.getValue(),
                StringValidator.isValidStringSize(Parameter.NAME.getValue(), name));
        validationMap.put(Parameter.LASTNAME.getValue(),
                StringValidator.isValidStringSize(Parameter.LASTNAME.getValue(), lastname));
        validationMap.put(Parameter.PHONE.getValue(),
                RegistrationDataValidator.isValidPhone(phone));
        validationMap.put(Parameter.EMAIL.getValue(),
                RegistrationDataValidator.isValidEmail(email)
                && StringValidator.isValidStringSize(Parameter.EMAIL.getValue(), email));
        validationMap.put(Parameter.ADDRESS.getValue(),
                StringValidator.isValidStringSize(Parameter.ADDRESS.getValue(), address));
        return validationMap;
    }
}