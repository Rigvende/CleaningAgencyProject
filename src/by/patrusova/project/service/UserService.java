package by.patrusova.project.service;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.validator.RegistrationDataValidator;
import by.patrusova.project.validator.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static final String PARAM_NAME_LOGIN = "loginreg";
    private static final String PARAM_NAME_PASSWORD = "passwordreg";
    private static final String PARAM_NAME_PASSWORD_AGAIN = "passwordagain";
    private static final String PARAM_NAME_NAME2 = "name";
    private static final String PARAM_NAME_NAME = "firstname";
    private static final String PARAM_NAME_LASTNAME = "lastname";
    private static final String PARAM_NAME_PHONE = "phone";
    private static final String PARAM_NAME_ADDRESS = "address";
    private static final String PARAM_NAME_EMAIL = "email";

    private UserService() {
    }

    public static User registerUser(User user) throws ServiceException, SQLException {
        DaoFactory factory = new DaoFactory();
        try {
            UserDao dao = factory.createUserDao();
            if (!isExist(user, dao)) {
                if (dao.create(user)) {
                    user = null;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    public static User createNewUser(HttpServletRequest request) {
        User newUser = new User();
        if (!validate(request).containsValue(false)) {
            newUser.setId(0);
            newUser.setLogin(request.getParameter(PARAM_NAME_LOGIN));
            newUser.setPassword(request.getParameter(PARAM_NAME_PASSWORD));
            newUser.setRole(String.valueOf(Role.GUEST)); //пока админ не подтвердит регистрацию
            newUser.setName(request.getParameter(PARAM_NAME_NAME));
            newUser.setLastname(request.getParameter(PARAM_NAME_LASTNAME));
            newUser.setPhone(Long.parseLong(request.getParameter(PARAM_NAME_PHONE)));
            newUser.setAddress(request.getParameter(PARAM_NAME_ADDRESS));
            newUser.setEmail(request.getParameter(PARAM_NAME_EMAIL));
            return newUser;
        } else {
            return null;
        }
    }

    private static boolean isExist(User user, UserDao dao) throws ServiceException {
        boolean exist;
        try {
            exist = dao.findLogin(user.getLogin());
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return exist;
    }
    private static Map<String, Boolean> validate(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        String passwordRepeated = request.getParameter(PARAM_NAME_PASSWORD_AGAIN);
        String name = request.getParameter(PARAM_NAME_NAME);
        String lastname = request.getParameter(PARAM_NAME_LASTNAME);
        String phone = request.getParameter(PARAM_NAME_PHONE);
        String email = request.getParameter(PARAM_NAME_EMAIL);
        String address = request.getParameter(PARAM_NAME_ADDRESS);
        validationMap.put(PARAM_NAME_LOGIN, RegistrationDataValidator.isValidLogin(login));
        validationMap.put(PARAM_NAME_PASSWORD, (RegistrationDataValidator.isValidPassword(password)
                && RegistrationDataValidator.isPasswordRepeated(password, passwordRepeated)));
        validationMap.put(PARAM_NAME_NAME, StringValidator.isValidStringSize(PARAM_NAME_NAME2, name));
        validationMap.put(PARAM_NAME_LASTNAME, StringValidator.isValidStringSize(PARAM_NAME_LASTNAME, lastname));
        validationMap.put(PARAM_NAME_PHONE, RegistrationDataValidator.isValidPhone(phone));
        validationMap.put(PARAM_NAME_EMAIL, RegistrationDataValidator.isValidEmail(email)
                && StringValidator.isValidStringSize(PARAM_NAME_EMAIL, email));
        validationMap.put(PARAM_NAME_ADDRESS, StringValidator.isValidStringSize(PARAM_NAME_ADDRESS, address));
        return validationMap;
    }
}