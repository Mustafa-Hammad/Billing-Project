/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.billing.rating;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author nora
 */
public class DBHandler {

    private static String dataBaseUrl = "jdbc:postgresql://blo19szvs1jhscgzh9x6-postgresql.services.clever-cloud.com:5432/blo19szvs1jhscgzh9x6";
    private static String userName = "uixcvchkhlu70fcqjtsr";
    private static String dataBasePassword = "W04cefxfqlELk9bCnzbZ";
    private static Connection connection = null;

    public static void startConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection(DBHandler.dataBaseUrl, DBHandler.userName, DBHandler.dataBasePassword);
        System.out.println("successfully connected");
    }

    public static void closeConnection() throws SQLException {
        DBHandler.connection.close();
        DBHandler.connection = null;
    }

    public static Connection getConnection() {
        return connection;
    }

}
