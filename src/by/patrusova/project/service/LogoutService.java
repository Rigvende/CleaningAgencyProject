package by.patrusova.project.service;

import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.impl.User;

public class LogoutService {

    public static boolean logoutUser(User user) {
        return UserDao.getUsers().remove(user);
    }
}