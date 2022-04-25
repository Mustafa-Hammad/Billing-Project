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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public boolean checkLogin(String username, String password) {
        boolean st = false;
        try {
            PreparedStatement ps = c.prepareStatement("select * from admin where name=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
        }
        return st;
    }

    public void addUser(String username, String email,String address, int credit) {
        try {
            //Cu_Id serial, name string, address string, cridet int
            PreparedStatement ps = c.prepareStatement("insert into Customer values(default,?, ?, ?, ?);");
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, address);
            ps.setInt(4, credit);
            ps.executeQuery(); 
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
