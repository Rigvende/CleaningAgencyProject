package by.patrusova.project.service;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.validator.NumberValidator;
import by.patrusova.project.validator.RegistrationDataValidator;
import by.patrusova.project.validator.StringValidator;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationService {

    private static final String PARAM_NAME_LOGIN = "loginreg";
    private static final String PARAM_NAME_PASSWORD = "passwordreg";
    private static final String PARAM_NAME_NAME = "firstname";
    private static final String PARAM_NAME_LASTNAME = "lastname";
    private static final String PARAM_NAME_PHONE = "phone";
    private static final String PARAM_NAME_ADDRESS = "address";
    private static final String PARAM_NAME_EMAIL = "email";

    private RegistrationService() {
    }

    public static User registerUser(User user) throws ServiceException, SQLException {
        DaoFactory factory = new DaoFactory();
        ProxyConnection connection = null;
        try {
            connection = (ProxyConnection) factory.getConnection();
            connection.setAutoCommit(false);
            UserDao dao = factory.createUserDao(connection);
            if (!isExist(user, dao)) {
                if (dao.create(user)) {
                    user = null;
                }
            }
            connection.commit();
        } catch (DaoException | SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
            throw new ServiceException(e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return user;
    }

    public static User createNewUser(HttpServletRequest request) {
        User newUser = new User();
        if (!validateRegistration(request).containsValue(false)) {
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
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return exist;
    }
    private static Map<String, Boolean> validateRegistration(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();
        String login = request.getParameter("loginreg");
        String password = request.getParameter("passwordreg");
        String passwordRepeated = request.getParameter("passwordagain");
        String name = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        validationMap.put("loginreg", RegistrationDataValidator.isValidLogin(login));
        validationMap.put("passwordreg", (RegistrationDataValidator.isValidPassword(password)
                && RegistrationDataValidator.isPasswordRepeated(password, passwordRepeated)));
        validationMap.put("firstname", StringValidator.isValidStringSize("name", name));
        validationMap.put("lastname", StringValidator.isValidStringSize("lastname", lastname));
        validationMap.put("phone", RegistrationDataValidator.isValidPhone(phone));
        validationMap.put("email", RegistrationDataValidator.isValidEmail(email)
                && StringValidator.isValidStringSize("email", email));
        validationMap.put("address", StringValidator.isValidStringSize("address", address));
        return validationMap;
    }
}