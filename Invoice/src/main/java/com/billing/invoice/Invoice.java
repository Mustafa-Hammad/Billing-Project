/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.billing.invoice;

import com.billibg.database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nora
 */
public class Invoice {

    private final DatabaseConnection db = DatabaseConnection.getDatabaseInstance();

    private int getCustomerData() {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select * from  billingcustomer");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Start Invoice----------------------------------------------------------------");
                int cuid = rs.getInt(2);
                String name = rs.getString(3);
                int numberOfContract = rs.getInt(4);
                int costRecurring = rs.getInt(5);
                float priceAfterTax = rs.getFloat(6);
                float priceBeforeTax = rs.getFloat(7);
                System.out.println(cuid + " : " + name + " : " + numberOfContract + " : " + costRecurring + " : " + priceAfterTax + " : " + priceBeforeTax);
                getAllContractsData(cuid);
            }
        } catch (SQLException e) {
            System.out.println("error - at getting customer data from db : " + e);
        }
        return 1;
    }

    private int getAllContractsData(int cu_id) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select * from  billingcontract where cu_id = ?");
            ps.setInt(1, cu_id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                String msisdn = rs.getString(3);
                String nameRatePlan = rs.getString(4);
                int costRatePlan = rs.getInt(5);
                float costOneTimeFee = rs.getFloat(6);
                float costExternalCharge = rs.getFloat(7);

                int usedFuVoiceOnNet = rs.getInt(8);
                int usedFuVoiceCrossNet = rs.getInt(9);
                int usedFuVoiceInternational = rs.getInt(10);

                int usedFuSMSOnNet = rs.getInt(11);
                int usedFuSMSCrossNet = rs.getInt(12);
                int usedFuSMSInternational = rs.getInt(13);

                int usedFuData = rs.getInt(14);

                int fuVoiceOnNet = rs.getInt(15);
                int fuVoiceCrossNet = rs.getInt(16);
                int fuVoiceInternational = rs.getInt(17);

                int fuSMSOnNet = rs.getInt(18);
                int fuSMSCrossNet = rs.getInt(19);
                int fuSMSInternational = rs.getInt(20);

                int fuData = rs.getInt(21);

                System.out.println(msisdn + " : " + nameRatePlan + " : " + costRatePlan + " : "
                        + costOneTimeFee + " : " + costExternalCharge + " : " + usedFuVoiceOnNet
                        + usedFuVoiceCrossNet + " : " + usedFuVoiceInternational + " : " + usedFuSMSOnNet + " : "
                        + usedFuSMSCrossNet + " : " + usedFuSMSInternational + " : " + usedFuData + " : " + fuVoiceOnNet
                        + fuVoiceCrossNet + " : " + fuVoiceInternational + " : " + fuSMSOnNet + " : "
                        + fuSMSCrossNet + " : " + fuSMSInternational + " : " + fuData);

            }
            System.out.println("End Invoice----------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("error - at getting contract data from db : " + e);
        }
        return 0;
    }

    public static void main(String[] args) {
        try {
            DatabaseConnection.getDatabaseInstance().connectToDatabase();
            new Invoice().getCustomerData();
        } catch (Exception e) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("error - rating connection to db : " + e);
        }
    }

}
