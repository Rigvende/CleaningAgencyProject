package add;

import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.impl.UserDao;
import by.patrusova.project.entity.AbstractEntity;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {
        //todo провалидировать сеттеры в энтитях
        Properties property = new Properties();
        try (FileInputStream fis = new FileInputStream
                ("src/resources/connectionDB.properties")) {
            property.load(fis);
        }
        String user = property.getProperty("user");
        String pass = property.getProperty("pass");
        String url = property.getProperty("url");
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        try (Connection conn = ProxyConnection.createProxyConnection()) {
            UserDao dao = new UserDao((ProxyConnection) conn);
            List<AbstractEntity> list = dao.findAll();
            for (AbstractEntity entity : list) {
                System.out.println(entity);
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
        }
    }
}