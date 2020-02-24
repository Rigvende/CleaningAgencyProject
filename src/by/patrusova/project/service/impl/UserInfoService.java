package by.patrusova.project.service.impl;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.EntityCreator;
import by.patrusova.project.service.Serviceable;
import by.patrusova.project.util.stringholder.Attribute;
import by.patrusova.project.util.stringholder.Parameter;
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

public class UserInfoService implements Serviceable, EntityCreator {

    private final static Logger LOGGER = LogManager.getLogger();

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
        User updatedUser = (User)request.getSession().getAttribute(Attribute.USER.getValue());
        if (!validate(request).containsValue(false)) {
            updatedUser.setName(request.getParameter(Parameter.FIRSTNAME.getValue()));
            updatedUser.setLastname(request.getParameter(Parameter.LASTNAME.getValue()));
            updatedUser.setRole(updatedUser.getRole());
            updatedUser.setPhone(Long.parseLong(request.getParameter(Parameter.PHONE.getValue())));
            updatedUser.setAddress(request.getParameter(Parameter.ADDRESS.getValue()));
            updatedUser.setEmail(request.getParameter(Parameter.EMAIL.getValue()));
            return Optional.of(updatedUser);
        } else {
            return Optional.empty();
        }
    }

    private Map<String, Boolean> validate(HttpServletRequest request) {
        Map<String, Boolean> validationMap = new HashMap<>();
        String name = request.getParameter(Parameter.FIRSTNAME.getValue());
        String lastname = request.getParameter(Parameter.LASTNAME.getValue());
        String phone = request.getParameter(Parameter.PHONE.getValue());
        String email = request.getParameter(Parameter.EMAIL.getValue());
        String address = request.getParameter(Parameter.ADDRESS.getValue());
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