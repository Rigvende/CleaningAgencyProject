package add;

import by.patrusova.project.connection.ConnectionPool;
import by.patrusova.project.connection.ProxyConnection;
import by.patrusova.project.dao.DaoFactory;
import by.patrusova.project.dao.EntityFactory;
import by.patrusova.project.dao.impl.OrderDao;
import by.patrusova.project.entity.AbstractEntity;
import by.patrusova.project.entity.impl.BasketPosition;
import by.patrusova.project.entity.impl.Order;
import by.patrusova.project.entity.impl.Service;
import by.patrusova.project.exception.DaoException;
import by.patrusova.project.exception.ServiceException;
import by.patrusova.project.service.impl.*;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Main {

//    SQL_TOTAL_COST("SELECT SUM(cost) AS total_cost FROM services JOIN basket_position " +
//                           "ON basket_position.id_service = services.id_service WHERE id_order = ?"),
//    SQL_TOTAL_SALE("SELECT SUM(cost * sales) AS total_sale FROM services JOIN basket_position " +
//                           "ON basket_position.id_service = services.id_service WHERE id_order = ?"),
//    SQL_TOTAL_DISCOUNT("SELECT SUM(cost * discount) AS total_discount " +
//                               "FROM (services JOIN basket_position ON basket_position.id_service = services.id_service) " +
//                               "JOIN (orders JOIN clients ON orders.id_client = clients.id_client) " +
//                               "ON basket_position.id_order = orders.id_order WHERE orders.id_order = ?"),
//    SQL_AVERAGE_MARK("SELECT id_cleaner, AVG(mark) AS average_mark FROM orders, cleaners " +
//                             "WHERE id_cleaner = ? AND orders.id_cleaner = cleaners.id_cleaner"),

//    public User createEntity(Map<String, Boolean> map) {
//        User newUser = new User();
//        if (!map.containsValue(false)) {
//            newUser.setId(0);
//            newUser.setLogin("ghhhhhhhh");
//            newUser.setPassword("qwerty");
//            newUser.setRole(String.valueOf(Role.GUEST)); //пока админ не подтвердит регистрацию
//            newUser.setName("qwerty");
//            newUser.setLastname("qwerty");
//            newUser.setPhone(Long.parseLong("1111111111"));
//            newUser.setEmail("ivanov@tut.by");
//            newUser.setAddress("");
//            return newUser;
//        } else {
//            return null;
//        }
//    }

    public static void main(String[] args) throws IOException, SQLException, DaoException, ServiceException {
//        OrderInfoService infoService = new OrderInfoService();
//        System.out.println(infoService.doService(52));

//        ShowService showService = new ShowService();
//        System.out.println(showService.doService("catalogue"));

//        OrderDao dao = DaoFactory.createOrderDao();
//        List<AbstractEntity> orders = new ArrayList<>();
//        ProxyConnection connection = dao.getConnection();
//        Statement statement = null;
//
//        statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT id_order, order_time, deadline, order_status, " +
//                "mark, id_client, id_cleaner FROM orders");
//        while (resultSet.next()) {
////                Order order = EntityFactory.createOrder(resultSet);
////                orders.add(order);
//            System.out.println(resultSet.getDate("order_time"));
//            System.out.println(resultSet.getDate("deadline"));
//
//        }
//        statement.close();
    }
}

