/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.iti.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author a7med
 */
public class DatabaseConnection {
    private  final String url="jdbc:postgresql://btxnhqatyjxgjkhmqgvg-postgresql.services.clever-cloud.com:5432/btxnhqatyjxgjkhmqgvg";
    private  final String userName="ual1kyfaaahzvalnqmv6";
    private  final String pass="s4ZDx5MEiWCrXYlDUx1A";
    
    private Connection connection ;
    private PreparedStatement preStm = null;
    private static  DatabaseConnection databaseInstance = new DatabaseConnection();

   public DatabaseConnection () {

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

   
   
    
    public void connectToDatabase(){
        
        try {
            Class.forName("org.postgresql.Driver");
            connection=DriverManager.getConnection(url,userName,pass);  
            System.out.println("Connection is made successfully");

        } catch (ClassNotFoundException | SQLException ex) {
            
            System.out.println("DatabaseConnection.DatabaseConnection.connect()error");
        }
          
    }
    public void closeDatabase() throws SQLException{
     connection.close();
   }
    
}
