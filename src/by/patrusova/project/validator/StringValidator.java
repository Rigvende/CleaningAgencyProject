package by.patrusova.project.validator;

import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.entity.impl.User;

public class StringValidator {

    private StringValidator() {}

    public static boolean isValidStringSize(String column, String data) {
        switch (column.toLowerCase()) {
            case "notes":
            case "email":
                return (data.length() <= 255);
            case "location":
            case "address":
                return (data.length() <= 200);
            case "relative":
                return (data.length() <= 80);
            case "service":
                return (data.length() <= 50);
            case "user":
            case "password":
                return (data.length() <= 45);
            case "name":
                return (data.length() <= 20);
            case "lastname":
                return (data.length() <= 40);
            default:
                return false;
        }
    }

    public static boolean isValidRole(String data) {
        boolean flag = false;
        User.Role[] roles = User.Role.values();
        for(User.Role role : roles) {
            if (role.getValue().equals(data.toLowerCase())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static boolean isValidStatus(String data) {
        boolean flag = false;
        Order.Status[] statuses = Order.Status.values();
        for(Order.Status status : statuses) {
            if (status.getValue().equals(data.toLowerCase())) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}