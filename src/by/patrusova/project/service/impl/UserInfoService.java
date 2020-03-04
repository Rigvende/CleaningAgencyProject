package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
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
 * Class for implementation of service logic concerning user's info
 * using operations with {@link UserDao}
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class UserInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String USER = "user";
    private final static String FIRSTNAME = "firstname";
    private final static String LASTNAME = "lastname";
    private final static String PHONE = "phone";
    private final static String ADDRESS = "address";
    private final static String EMAIL = "email";

    //update user
    @Override
    public Optional<AbstractEntity> doService(AbstractEntity entity) throws ServiceException {
        User user = (User) entity;
        try {
            UserDao dao = DaoFactory.createUserDao();
            if (dao.update(user)) {
                user = null;
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception in UserInfoService while updating user has occurred. ", e);
            throw new ServiceException(e);
        }
        return user != null ? Optional.of(user) : Optional.empty();
    }

    //create instance of user with changes
    @Override
    public Optional<AbstractEntity> createEntity(HttpServletRequest request) {
        User updatedUser = (User) request.getSession().getAttribute(USER);
        if (isValidData(request)) {
            updatedUser.setName(request.getParameter(FIRSTNAME));
            updatedUser.setLastname(request.getParameter(LASTNAME));
            updatedUser.setRole(updatedUser.getRole());
            updatedUser.setPhone(Long.parseLong(request.getParameter(PHONE)));
            updatedUser.setAddress(request.getParameter(ADDRESS));
            updatedUser.setEmail(request.getParameter(EMAIL));
            return Optional.of(updatedUser);
        } else {
            return Optional.empty();
        }
    }

    //validation
    private boolean isValidData(HttpServletRequest request) {
        String name = request.getParameter(FIRSTNAME);
        String lastname = request.getParameter(LASTNAME);
        String phone = request.getParameter(PHONE);
        String email = request.getParameter(EMAIL);
        String address = request.getParameter(ADDRESS);
        boolean a = RegistrationDataValidator.isValidName(name);
        boolean b = RegistrationDataValidator.isValidLastname(lastname)
                    && StringValidator.isValidStringSize(LASTNAME, lastname);
        boolean c = RegistrationDataValidator.isValidPhone(phone);
        boolean d = RegistrationDataValidator.isValidEmail(email)
                    && StringValidator.isValidStringSize(EMAIL, email);
        boolean e = StringValidator.isValidStringSize(ADDRESS, address);
        return a && b && c && d && e;
    }
}