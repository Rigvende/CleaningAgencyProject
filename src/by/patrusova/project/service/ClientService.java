package by.patrusova.project.service;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.ClientDao;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.Role;
import by.patrusova.project.entity.impl.Client;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.validator.RegistrationDataValidator;
import by.patrusova.project.validator.StringValidator;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ClientService {

    public static Client getClient(Client client) throws ServiceException {
        DaoFactory factory = new DaoFactory();
        try {
            ClientDao dao = factory.createClientDao();
            client = (Client) dao.findEntityById(client.getIdUser());
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e);
        }
        return client;
    }

//    public static Client createNewClient(HttpServletRequest request) {
//        Client newClient = new Client();
//        if (!validate(request).containsValue(false)) {
//            newClient.setId(0);
//            newClient.setLogin(request.getParameter(PARAM_NAME_LOGIN));
//            newClient.setPassword(request.getParameter(PARAM_NAME_PASSWORD));
//            newClient.setRole(String.valueOf(Role.GUEST)); //пока админ не подтвердит регистрацию
//            newClient.setName(request.getParameter(PARAM_NAME_NAME));
//            newClient.setLastname(request.getParameter(PARAM_NAME_LASTNAME));
//            newClient.setPhone(Long.parseLong(request.getParameter(PARAM_NAME_PHONE)));
//            newClient.setAddress(request.getParameter(PARAM_NAME_ADDRESS));
//            newClient.setEmail(request.getParameter(PARAM_NAME_EMAIL));
//            return newClient;
//        } else {
//            return null;
//        }
//    }
//
//    private static boolean isExist(Client client, ClientDao dao) throws ServiceException {
//        try {
//            return dao.findEntityById(client.getIdUser()) != null;
//        } catch (DaoException | SQLException e) {
//            throw new ServiceException(e);
//        }
//    }
//    private static Map<String, Boolean> validate(HttpServletRequest request) {
//        Map<String, Boolean> validationMap = new HashMap<>();
//        String login = request.getParameter(PARAM_NAME_LOGIN);
//        String password = request.getParameter(PARAM_NAME_PASSWORD);
//        String passwordRepeated = request.getParameter(PARAM_NAME_PASSWORD_AGAIN);
//        String name = request.getParameter(PARAM_NAME_NAME);
//        String lastname = request.getParameter(PARAM_NAME_LASTNAME);
//        String phone = request.getParameter(PARAM_NAME_PHONE);
//        String email = request.getParameter(PARAM_NAME_EMAIL);
//        String address = request.getParameter(PARAM_NAME_ADDRESS);
//        validationMap.put(PARAM_NAME_LOGIN, RegistrationDataValidator.isValidLogin(login));
//        validationMap.put(PARAM_NAME_PASSWORD, (RegistrationDataValidator.isValidPassword(password)
//                && RegistrationDataValidator.isPasswordRepeated(password, passwordRepeated)));
//        validationMap.put(PARAM_NAME_NAME, StringValidator.isValidStringSize(PARAM_NAME_NAME2, name));
//        validationMap.put(PARAM_NAME_LASTNAME, StringValidator.isValidStringSize(PARAM_NAME_LASTNAME, lastname));
//        validationMap.put(PARAM_NAME_PHONE, RegistrationDataValidator.isValidPhone(phone));
//        validationMap.put(PARAM_NAME_EMAIL, RegistrationDataValidator.isValidEmail(email)
//                && StringValidator.isValidStringSize(PARAM_NAME_EMAIL, email));
//        validationMap.put(PARAM_NAME_ADDRESS, StringValidator.isValidStringSize(PARAM_NAME_ADDRESS, address));
//        return validationMap;
//    }
}