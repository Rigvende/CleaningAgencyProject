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
    private final static String LOGINREG = "loginreg";
    private final static String PASSWORDREG = "passwordreg";
    private final static String PASSWORD_AGAIN = "passwordagain";
    private final static String FIRSTNAME = "firstname";
    private final static String LASTNAME = "lastname";
    private final static String PHONE = "phone";
    private final static String ADDRESS = "address";
    private final static String EMAIL = "email";

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
            newUser.setLogin(request.getParameter(LOGINREG));
            newUser.setPassword(request.getParameter(PASSWORDREG));
            newUser.setRole(Role.GUEST.getValue());            //while admin does not confirm registration
            newUser.setName(request.getParameter(FIRSTNAME));
            newUser.setLastname(request.getParameter(LASTNAME));
            newUser.setPhone(Long.parseLong(request.getParameter(PHONE)));
            newUser.setEmail(request.getParameter(EMAIL));
            newUser.setAddress(request.getParameter(ADDRESS));
            return Optional.of(newUser);
        } else {
            return Optional.empty();
        }
    }

    public Map<String, Boolean> validate(HttpServletRequest request) throws ServiceException {
        Map<String, Boolean> validationMap = new HashMap<>();
        String login = request.getParameter(LOGINREG);
        String password = request.getParameter(PASSWORDREG);
        String passwordRepeated = request.getParameter(PASSWORD_AGAIN);
        String name = request.getParameter(FIRSTNAME);
        String lastname = request.getParameter(LASTNAME);
        String phone = request.getParameter(PHONE);
        String email = request.getParameter(EMAIL);
        String address = request.getParameter(ADDRESS);
        try {
            validationMap.put(LOGINREG, RegistrationDataValidator.isValidLogin(login));
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot validate user, exception has occurred. ", e);
            throw new ServiceException(e);
        }
        validationMap.put(PASSWORDREG,
                (RegistrationDataValidator.isValidPassword(password)
                && RegistrationDataValidator.isPasswordRepeated(password, passwordRepeated)));
        validationMap.put(FIRSTNAME, RegistrationDataValidator.isValidName(name));
        validationMap.put(LASTNAME,
                RegistrationDataValidator.isValidLastname(lastname)
                && StringValidator.isValidStringSize(LASTNAME, lastname));
        validationMap.put(PHONE, RegistrationDataValidator.isValidPhone(phone));
        validationMap.put(EMAIL,
                RegistrationDataValidator.isValidEmail(email)
                && StringValidator.isValidStringSize(EMAIL, email));
        validationMap.put(ADDRESS, StringValidator.isValidStringSize(ADDRESS, address));
        return validationMap;
    }
}