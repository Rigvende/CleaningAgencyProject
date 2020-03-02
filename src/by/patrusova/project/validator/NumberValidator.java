package by.patrusova.project.validator;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.*;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for validating number data from requests.
 * @autor Marianna Patrusova
 * @version 1.0
 */
public class NumberValidator {

    private final static Logger LOGGER = LogManager.getLogger();
    private final static String CHECK_DECIMAL = "^0.[\\d]{1,2}$";
    private final static String CHECK_ID = "^[\\d]{1,18}$";
    private final static String CHECK_COST = "^[\\d]{1,3}((.[\\d]{1,2})?)$";
    private final static String CHECK_MARK = "^[\\d]$";

    private NumberValidator() {
    }

    public static boolean isValidDays(String days) {
        if (days.isEmpty()) {
            return false;
        } else {
            int day = Integer.parseInt(days);
            return day >= 2 && day <= 30;
        }
    }

    public static boolean isValidDecimal(String decimal) {
        Pattern pattern = Pattern.compile(CHECK_DECIMAL);
        Matcher matcher = pattern.matcher(decimal);
        return matcher.find();
    }

    public static boolean isValidCleanerID(String id) throws ServiceException {
        boolean check = false;
        try {
            CleanerDao dao = DaoFactory.createCleanerDao();
            if (isValidID(id)) {
                check = dao.findId(Long.parseLong(id));
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while validating cleaner ID was processing. ", e);
            throw new ServiceException(e);
        }
        return check;
    }

    public static boolean isValidClientID(String id) throws ServiceException {
        boolean check = false;
        try {
            ClientDao dao = DaoFactory.createClientDao();
            if (isValidID(id)) {
                check = dao.findId(Long.parseLong(id));
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while validating client ID was processing. ", e);
            throw new ServiceException(e);
        }
        return check;
    }

    public static boolean isValidUserID(String id) throws ServiceException {
        boolean check = false;
        try {
            UserDao dao = DaoFactory.createUserDao();
            if (isValidID(id)) {
                check = dao.findId(Long.parseLong(id));
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while validating user ID was processing. ", e);
            throw new ServiceException(e);
        }
        return check;
    }

    public static boolean isValidOrderID(String id) throws ServiceException {
        boolean check = false;
        try {
            OrderDao dao = DaoFactory.createOrderDao();
            if (isValidID(id)) {
                check = dao.findId(Long.parseLong(id));
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while validating order ID was processing. ", e);
            throw new ServiceException(e);
        }
        return check;
    }

    public static boolean isValidServiceID(String id) throws ServiceException {
        boolean check = false;
        try {
            ServiceDao dao = DaoFactory.createServiceDao();
            if (isValidID(id)) {
                check = dao.findId(Long.parseLong(id));
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while validating service ID was processing. ", e);
            throw new ServiceException(e);
        }
        return check;
    }

    public static boolean isValidBasketID(String id) throws ServiceException {
        boolean check = false;
        try {
            BasketDao dao = DaoFactory.createBasketDao();
            if (isValidID(id)) {
                check = dao.findId(Long.parseLong(id));
            }
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR,
                    "Exception has occurred while validating basket ID was processing. ", e);
            throw new ServiceException(e);
        }
        return check;
    }

    public static boolean isValidID(String id) {
        Pattern pattern = Pattern.compile(CHECK_ID);
        Matcher matcher = pattern.matcher(id);
        return matcher.find();
    }

    public static boolean isValidCost(String id) {
        Pattern pattern = Pattern.compile(CHECK_COST);
        Matcher matcher = pattern.matcher(id);
        return matcher.find();
    }

    public static boolean isValidMark(String mark) {
        Pattern pattern = Pattern.compile(CHECK_MARK);
        Matcher matcher = pattern.matcher(mark);
        return matcher.find();
    }
}