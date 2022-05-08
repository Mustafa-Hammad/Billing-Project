/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CDRParser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author a7med
 */
public class CDRDBConn {
    
     private static Connection con;

    public CDRDBConn() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found " + e);
        }
        try {

            con = DriverManager.getConnection("jdbc:postgresql://btxnhqatyjxgjkhmqgvg-postgresql.services.clever-cloud.com:5432/btxnhqatyjxgjkhmqgvg", "ual1kyfaaahzvalnqmv6", "s4ZDx5MEiWCrXYlDUx1A");
            System.out.println("database connected");
        } catch (Exception ex) {
    
            System.out.println("exception at database connection" + ex);
        }
    }

    public static Connection getConnection() {
        return con;
    }

    public static void closeConnection() {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
