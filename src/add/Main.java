package add;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.User;
import by.patrusova.project.service.LoginService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {



//        File f = new File("src/resources/connectionDB.properties");
//        System.out.println(f.getAbsolutePath());
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        try (ProxyConnection conn = ProxyConnection.createProxyConnection()) {
            UserDao dao = new UserDao(conn);
            User user = new User();
            user.setLogin("123");
            user.setPassword("123");
            user = LoginService.checkLogin(user);
            System.out.println(user);
//            List<AbstractEntity> list = dao.findAll();
//            for (AbstractEntity entity : list) {
//                System.out.println(entity);
//            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
        }
    }
}