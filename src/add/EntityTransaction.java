package add;

import by.patrusova.project.connection.ProxyConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EntityTransaction {

//    private ProxyConnection connection;
//    private EntityTransaction transaction;
//
//    public synchronized EntityTransaction getInstance() {
//        if (transaction == null) {
//            transaction = new EntityTransaction();
//            transaction.getConnection();
//        }
//        return transaction;
//    }
//
//    private Connection getConnection() {
//        Connection connectionFrom = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connectionFrom = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/cleaning_agency", "root", "root");
//            connectionFrom.setAutoCommit(false);
//        } catch (SQLException e) {
//            System.err.println("SQLException: " + e.getMessage()
//                    + "SQLState: " + e.getSQLState());
//        } catch (ClassNotFoundException ex) {
//            System.out.println("Driver not found");
//        }
//        return connectionFrom;
//    }
//
//    public void transfer(String amount) throws SQLException {
//        Statement stFrom = null;
//        Statement stTo = null;
//        try {
//            int sum = Integer.parseInt(amount);
//            if (sum <= 0) {
//                throw new NumberFormatException("less or equals zero");
//            }
//            stFrom = connectionFrom.createStatement();
//            stTo = connectionTo.createStatement();
//            // транзакция из 4-х запросов
//            ResultSet rsFrom =
//                    stFrom.executeQuery("SELECT balance from table_from");
//            ResultSet rsTo =
//                    stTo.executeQuery("SELECT balance from table_to");
//            int accountFrom = 0;
//            while (rsFrom.next()) {
//                accountFrom = rsFrom.getInt(1);
//            }
//            int resultFrom= 0;
//            if (accountFrom >= sum) {
//                resultFrom = accountFrom - sum;
//            }else  {
//                throw new SQLException("Invalid balance");
//            }
//            int accountTo = 0;
//            while (rsTo.next()) {
//                accountTo = rsTo.getInt(1);
//            }
//            int resultTo = accountTo + sum;
//            stFrom.executeUpdate(
//                    "UPDATE table_from SET balance=" + resultFrom);
//            stTo.executeUpdate("UPDATE table_to SET balance=" + resultTo);
//            // завершение транзакции
//            connectionFrom.commit();
//            connectionTo.commit();
//            System.out.println("remaining on :" + resultFrom + " rub");
//        } catch (SQLException e) {
//            System.err.println("SQLState: " + e.getSQLState()
//                    + "Error Message: " + e.getMessage());
//            // откат транзакции при ошибке
//            connectionFrom.rollback();
//            connectionTo.rollback();
//        } catch (NumberFormatException e) {
//            System.err.println("Invalid sum: " + amount);
//        } finally {
//            if (stFrom != null) {
//                try {
//                    stFrom.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//            if (stTo != null) {
//                try {
//                    stTo.close();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//            }
//        }
//    }
}