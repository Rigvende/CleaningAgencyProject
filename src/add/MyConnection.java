package add;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MyConnection {

    private final static String SQL_COMMAND_1 = "CREATE TABLE products " +
            "(Id INT PRIMARY KEY AUTO_INCREMENT, ProductName VARCHAR(20), Price INT)";
    private final static String SQL_COMMAND_2 = "DROP TABLE products";
    private final static String SQL_COMMAND_3 = "INSERT Products(ProductName, Price) " +
            "VALUES ('iPhone X', 76000), ('Galaxy S9', 45000), ('Nokia 9', 36000)";
    private final static String SQL_COMMAND_4 = "SELECT Id, ProductName, Price FROM Products";
    private final static String SQL_COMMAND_5 = "UPDATE Products SET Price = Price - 5000";
    private final static String SQL_COMMAND_6 = "DELETE FROM Products WHERE Id = 3";
    //запрос через PreparedStatement
    private final static String SQL_COMMAND_7 = "INSERT INTO Products (ProductName, Price) Values (?, ?)";

    public static void main(String[] args) {

        try { //todo ssl в url должен применяться(см.файл коннекшн.пропертис)?
            //используем пропертис для выборки текстовых значений для коннекшна
            Properties property = new Properties();
            try (FileInputStream fis = new FileInputStream
                    ("src/resources/connectionDB.properties")) {
                property.load(fis);
            }
            String user = property.getProperty("user");
            String pass = property.getProperty("pass");
            String url = property.getProperty("url");
            //используем ресурсбандл для выборки текстовых значений для коннекшна
//            Locale current = Locale.getDefault();
//            ResourceBundle bundle = ResourceBundle.getBundle("resources.connectionDB", current);
//            String user = bundle.getString("user");
//            String pass = bundle.getString("pass");
//            String url = bundle.getString("url");
//            String driver = bundle.getString("driver"); //рефлексия нежелательна
//            Class.forName(driver).getDeclaredConstructor().newInstance();
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            //запросы на изменение таблиц:
            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                //можно применять этот стэйтмент сколько хочешь раз
                Statement statement = conn.createStatement();

                // создание таблицы
                statement.executeUpdate(SQL_COMMAND_1);
                System.out.println("The table is created");

                //добавление в таблицу
                int rows = statement.executeUpdate(SQL_COMMAND_3);
                System.out.printf("Added %d rows\n", rows);

                //извлечение данных из таблицы через ResultSet
                ResultSet resultSet = statement.executeQuery(SQL_COMMAND_4);
                while (resultSet.next()) {
                    int id = resultSet.getInt(1);
                    String name = resultSet.getString(2);
                    int price = resultSet.getInt(3);
                    System.out.printf("%d. %s - %d \n", id, name, price);
                }

                //изменение таблицы
                int rows2 = statement.executeUpdate(SQL_COMMAND_5);
                System.out.printf("Updated %d rows\n", rows2);
                ResultSet resultSet2 = statement.executeQuery(SQL_COMMAND_4);
                while (resultSet2.next()) {
                    int id = resultSet2.getInt(1);
                    String name = resultSet2.getString(2);
                    int price = resultSet2.getInt(3);
                    System.out.printf("%d. %s - %d \n", id, name, price);
                }

                //удаление строки
                int rows3 = statement.executeUpdate(SQL_COMMAND_6);
                System.out.printf("%d row(s) deleted\n", rows3);
                ResultSet resultSet3 = statement.executeQuery(SQL_COMMAND_4);
                while (resultSet3.next()) {
                    int id = resultSet3.getInt(1);
                    String name = resultSet3.getString(2);
                    int price = resultSet3.getInt(3);
                    System.out.printf("%d. %s - %d \n", id, name, price);
                }

                //изменение таблицы через PreparedStatement
                Scanner scanner = new Scanner(System.in);
                System.out.print("Input product name: ");
                String name1 = scanner.nextLine();
                System.out.print("Input product price: ");
                int price1 = scanner.nextInt();
                PreparedStatement preparedStatement = conn.prepareStatement(SQL_COMMAND_7);
                preparedStatement.setString(1, name1);
                preparedStatement.setInt(2, price1);
                int rows4 = preparedStatement.executeUpdate();
                System.out.printf("%d rows added\n", rows4);
                ResultSet resultSet4 = statement.executeQuery(SQL_COMMAND_4);
                while (resultSet4.next()) {
                    int id = resultSet4.getInt(1);
                    String name = resultSet4.getString(2);
                    int price = resultSet4.getInt(3);
                    System.out.printf("%d. %s - %d \n", id, name, price);
                }

                // удаление таблицы
                statement.executeUpdate(SQL_COMMAND_2);
                System.out.println("The table is droped");
                System.out.println("Connection to Cleaning Agency DB succesfull!");
            }
        } catch (Exception ex) {
            System.out.println("Connection failed...");
        }
    }
}