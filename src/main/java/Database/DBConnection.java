/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import User.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
    PreparedStatement ps;
    ResultSet rs;
    

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
            ps = c.prepareStatement("select * from admin where name=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            st = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
        }
        return st;
    }

    public int addUser(String username, String email, String password, int credit) {
        try {
            //Cu_Id serial, name string, address string, cridet int
            ps = c.prepareStatement("insert into Customer values(default,?, ?, ?, ?);");
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setInt(4, credit);
            ps.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    public void delete(int id) {
        try {
            ps = c.prepareStatement("delete from contract where cu_id=?;"
                    + "delete from customer where cu_id=?;");
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<String> getMSISDN(int id) {
        List<String> msisdn = new ArrayList<>();
        try {
            String query = "select msisdn from cstmsisdn"
                    + " where id=?";
            ps = c.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            int x = 0;
            while (rs.next()) {
                x++;
                //retreive data by column name
                msisdn.add(x, rs.getString("msisdn"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msisdn;
    }


 public int checkID(String cstid, String phoneNo, String rp1) {
        boolean st = false;
        try {
            ps = c.prepareStatement("select * from contract where cu_id=? and msisdn=?;");
            ps.setInt(1, Integer.parseInt(cstid));
            ps.setString(2, phoneNo);
            rs = ps.executeQuery();
            st = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception e) {
        }
        if (st) {
            RatePlan rp = new RatePlan();
            int x1 = 0;
            if (rp1.equalsIgnoreCase("X150")) {
                x1 = 1;
            } else if (rp1.equalsIgnoreCase("X250")) {
                x1 = 2;
            } else if (rp1.equalsIgnoreCase("X400")) {
                x1 = 3;
            }
            List<Integer> l1 = new ArrayList<Integer>();
            l1 = rp.ratePlan(x1);
            try {
                ps = c.prepareStatement("select credit \n"
                        + "from customer\n"
                        + "where cu_id=11;");
                rs = ps.executeQuery();
                while(rs.next()){
                    
                rs.getInt("cs_id");
                }
                ps = c.prepareStatement("update contract\n"
                        + "set fuvoiceonnet = ?,\n"
                        + "fuvoicecrossnet = ?,\n"
                        + "fuvoiceinternational = ?,\n"
                        + "fusmsonnet = ?,\n"
                        + "fusmscrossnet = ?,\n"
                        + "fudata = ?\n"
                        + "where cu_id = ?;");
                ps.setInt(1, l1.get(0));
                ps.setInt(2, l1.get(1));
                ps.setInt(3, l1.get(2));
                ps.setInt(4, l1.get(3));
                ps.setInt(5, l1.get(4));
                ps.setInt(6, l1.get(5));
                ps.setInt(7, Integer.parseInt(cstid));
                rs = ps.executeQuery();
                st = rs.next();
            } catch (SQLException ex) {
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {
            }
        }
        return 0;
    }
}
