/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author ahmedmedhat
 */
public class DBConnection {

    public Connection c;
    private final String url = "jdbc:postgresql://localhost:5432/billing";
    private final String userName = "postgres";
    private final String password = "postgres";

    public void getConnection() {
        c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, userName, password);
//            return c;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
//        return null;
    }

    
}

   
    
    
