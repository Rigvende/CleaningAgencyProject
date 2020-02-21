package by.patrusova.project.validator;

import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.impl.*;
import by.patrusova.project.exception.DaoException;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberValidator {

    private final static String CHECK_DECIMAL = "^0.[\\d]{1,2}$";
    private final static String CHECK_ID = "^[\\d]{1,18}$";
    private final static String CHECK_COST = "^[\\d]{1,3}((.[\\d]{1,2})?)$";
    private final static String CHECK_MARK = "^\\d$";

    private NumberValidator() {
    }

    public static boolean isValidDecimal(String decimal) {
        Pattern pattern = Pattern.compile(CHECK_DECIMAL);
        Matcher matcher = pattern.matcher(decimal);
        return matcher.find();
    }

    public static boolean isValidCleanerID(String id) throws DaoException, SQLException {
        DaoFactory factory = new DaoFactory();
        CleanerDao dao = factory.createCleanerDao();
        boolean check = false;
        if(isValidID(id) ) {
            check = dao.findId(Long.parseLong(id));
        }
        return check;
    }

    public static boolean isValidClientID(String id) throws DaoException, SQLException {
        DaoFactory factory = new DaoFactory();
        ClientDao dao = factory.createClientDao();
        boolean check = false;
        if (isValidID(id)) {
            check = dao.findId(Long.parseLong(id));
        }
        return check;
    }

    public static boolean isValidUserID(String id) throws DaoException, SQLException {
        DaoFactory factory = new DaoFactory();
        UserDao dao = factory.createUserDao();
        boolean check = false;
        if (isValidID(id)) {
            check = dao.findId(Long.parseLong(id));
        }
        return check;
    }

    public static boolean isValidOrderID(String id) throws DaoException, SQLException {
        DaoFactory factory = new DaoFactory();
        OrderDao dao = factory.createOrderDao();
        boolean check = false;
        if(isValidID(id) ) {
            check = dao.findId(Long.parseLong(id));
        }
        return check;
    }

    public static boolean isValidServiceID(String id) throws DaoException, SQLException {
        DaoFactory factory = new DaoFactory();
        ServiceDao dao = factory.createServiceDao();
        boolean check = false;
        if(isValidID(id) ) {
            check = dao.findId(Long.parseLong(id));
        }
        return check;
    }

    public static boolean isValidBasketID(String id) throws DaoException, SQLException {
        DaoFactory factory = new DaoFactory();
        BasketDao dao = factory.createBasketDao();
        boolean check = false;
        if(isValidID(id) ) {
            check = dao.findId(Long.parseLong(id));
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