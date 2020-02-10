//package by.patrusova.project.service;
//
//import by.patrusova.project.connection.ProxyConnection;
//import by.patrusova.project.dao.DaoFactory;
//import by.patrusova.project.dao.impl.ClientDao;
//import by.patrusova.project.dao.impl.UserDao;
//import by.patrusova.project.entity.impl.User;
//import by.patrusova.project.exception.DaoException;
//import by.patrusova.project.exception.ServiceException;
//
//import java.sql.SQLException;
//
//public class RoleService {
//
//    public static boolean updateRole(User user) throws ServiceException {
//        boolean check = false;
//        DaoFactory factory = new DaoFactory();
//        try {
//            ProxyConnection connection = (ProxyConnection) factory.getConnection();
//            connection.setAutoCommit(false);
//            UserDao dao = factory.createUserDao(connection);
//            if (RegistrationService.isExist(user, dao)) {
//                dao.update(user);
//                check = true;
//            }
//            connection.commit();
//            connection.close();
//        } catch (DaoException | SQLException e) {
//            throw new ServiceException(e);
//        }
//        return check;
//    }
//
//    private static boolean createClient() {
//        boolean check = false;
//        DaoFactory factory = new DaoFactory();
//        try {
//            ProxyConnection connection = (ProxyConnection) factory.getConnection();
//            connection.setAutoCommit(false);
//            ClientDao dao = factory.createClientDao(connection);
//            dao.create(user);
//            check = true;
//            connection.commit();
//            connection.close();
//        } catch (DaoException | SQLException e) {
//            throw new ServiceException(e);
//        }
//        return check;
//    }
//}
