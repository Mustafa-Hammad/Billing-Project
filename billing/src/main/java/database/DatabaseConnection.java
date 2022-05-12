/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private final String url = "jdbc:postgresql://btxnhqatyjxgjkhmqgvg-postgresql.services.clever-cloud.com:5432/btxnhqatyjxgjkhmqgvg";
    private final String userName = "ual1kyfaaahzvalnqmv6";
    private final String pass = "s4ZDx5MEiWCrXYlDUx1A";

    private Connection connection = null;
    private PreparedStatement preStm = null;
    ResultSet rs = null;
    private static DatabaseConnection databaseInstance = new DatabaseConnection();
    HashMap<String, String> customerInfo = new HashMap<String, String>();

    public DatabaseConnection() {

    }

    public static DatabaseConnection getDatabaseInstance() {
        return databaseInstance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getPreStm() {
        return preStm;
    }

    public void setPreStm(PreparedStatement preStm) {
        this.preStm = preStm;
    }

    public void connectToDatabase() {

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, userName, pass);
            System.out.println("Connection is made successfully");

        } catch (ClassNotFoundException | SQLException ex) {

            System.out.println("DatabaseConnection.DatabaseConnection.connect()error");
        }

    }

   

//    public static void main(String[] args) {
//        DatabaseConnection db = DatabaseConnection.getDatabaseInstance();
//        db.connectToDatabase();
//        HashMap<String, String> customerInfo = new HashMap<String, String>();
//        db.getCustomerInfo("00201093693045");
//        for (String i : customerInfo.keySet()) {
//            System.out.println("key: " + i + " value: " + customerInfo.get(i));
//        }
//    }

}
