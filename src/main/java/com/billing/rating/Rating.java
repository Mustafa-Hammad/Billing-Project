/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.billing.rating;

import com.billing.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author nora
 */
public class Rating {

    DatabaseConnection db = DatabaseConnection.getDatabaseInstance();
    // get cdr from database => thread if there is cdr not rating or not 
    // 
    // set cdr to true rating 

    public void Rating() {
        try {
            db.connectToDatabase();
        } catch (Exception e) {
            System.out.println("error - rating connection to db : " + e);
        }
    }

    public void applyRating() {
        try {
         PreparedStatement ps = db.getConnection().prepareStatement("select * from cdr where rating=false");
        ResultSet res =  ps.executeQuery();
        while(res.next()){
           
        }

        } catch (Exception e) {
            System.out.println("error - at getting cdr from db : " + e);
        }
    }

//    public static void main(String[] args) {
//
//    }

}
