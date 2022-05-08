/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CDRParser;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a7med
 */
public class CDRDBIns {

    Connection conn;
    Statement stmt;
    ResultSet rs;
    PreparedStatement pst;

    public CDRDBIns() {
        CDRDBConn c = new CDRDBConn();
        conn = c.getConnection();
        if (conn == null) {
            System.out.println("database connection is null");
        }
    }

    public void insertCDR(CDR cdr) {
        try {
            Date date = cdr.getStartDate();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            pst = conn.prepareStatement("INSERT INTO cdr(cdr_id,DialA,DialB,StartDate,timeStamp,rp_id,service_id,consumption,externelcharge,isRating) VALUES(?,?,?,?,?,?,?,?,?,?);");
            pst.setInt(1, cdr.getCdrId());
            pst.setString(2, cdr.getDialA());
            pst.setString(3, cdr.getDialB());
            pst.setDate(4, sqlDate);
            pst.setString(5, cdr.getTimeStamp());
            pst.setInt(6, cdr.getRatePlanId());
            pst.setInt(7, cdr.getServiceId());
            pst.setFloat(8, cdr.getConsumption());
            pst.setFloat(9, cdr.getExternelCharge());
            pst.setBoolean(10, cdr.isIsRating());
            int rows = pst.executeUpdate();
            pst.close();
            System.out.print(rows);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }

}
