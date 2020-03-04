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
import java.util.Optional;

/**
 * Class for implementation of service logic concerning registration
 * using operations with {@link UserDao}
 * @autor Marianna Patrusova
 * @version 1.0
 */
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
        if (isValidData(request)) {
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

    private boolean isValidData(HttpServletRequest request) throws ServiceException {
        String login = request.getParameter(LOGINREG);
        String password = request.getParameter(PASSWORDREG);
        String passwordRepeated = request.getParameter(PASSWORD_AGAIN);
        String name = request.getParameter(FIRSTNAME);
        String lastname = request.getParameter(LASTNAME);
        String phone = request.getParameter(PHONE);
        String email = request.getParameter(EMAIL);
        String address = request.getParameter(ADDRESS);
        boolean a;
        try {
            a = RegistrationDataValidator.isValidLogin(login);
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot validate user, exception has occurred. ", e);
            throw new ServiceException(e);
        }
        boolean b = (RegistrationDataValidator.isValidPassword(password)
                && RegistrationDataValidator.isPasswordRepeated(password, passwordRepeated));
        boolean c = RegistrationDataValidator.isValidName(name);
        boolean d = RegistrationDataValidator.isValidLastname(lastname)
                && StringValidator.isValidStringSize(LASTNAME, lastname);
        boolean e = RegistrationDataValidator.isValidPhone(phone);
        boolean f = RegistrationDataValidator.isValidEmail(email)
                && StringValidator.isValidStringSize(EMAIL, email);
        boolean g = StringValidator.isValidStringSize(ADDRESS, address);
        return (a && b && c && d && f && g);
    }
}