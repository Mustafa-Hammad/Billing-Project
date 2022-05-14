/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.billing.invoice;

import com.billibg.database.DatabaseConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author nora
 */
public class Invoice {

    private final DatabaseConnection db = DatabaseConnection.getDatabaseInstance();
    public static HashMap hm = new HashMap();
    LocalDate date = LocalDate.now();
    public static String fileNameJrxml = "Bill.jrxml";
    public static String fileNamePdf;

    private int getCustomerData(int id) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select * from  billingcustomer where cu_id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println("Start Invoice----------------------------------------------------------------");
                int cuid = rs.getInt(2);
                String name = rs.getString(3);
                int numberOfContract = rs.getInt(4);
                int costRecurring = rs.getInt(5);
                float priceAfterTax = rs.getFloat(6);
                float priceBeforeTax = rs.getFloat(7);
                hm.put("c_name", name);
                fileNamePdf = "./pdf/" + name + "_" + date + ".pdf";
                hm.put("c_id", cuid);
                hm.put("no_con", numberOfContract);
                hm.put("recurring", costRecurring);
                hm.put("taxes", priceBeforeTax);
                hm.put("total", priceAfterTax);
                System.out.println(cuid + " : " + name + " : " + numberOfContract + " : " + costRecurring + " : " + priceAfterTax + " : " + priceBeforeTax);
                creatPDF();
            }
        } catch (SQLException e) {
            System.out.println("error - at getting customer data from db : " + e);
        }
        return 1;
    }

    private int getAllContractsData(String ms) {
        try {
            PreparedStatement ps = db.getConnection().prepareStatement("select * from  billingcontract where msisdn = ?");
            ps.setString(1, ms);

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
                hm.put("MSISDN", msisdn);
                hm.put("RP", nameRatePlan);
                hm.put("RPC", costRatePlan);
                hm.put("v_onnet", fuVoiceOnNet);
                hm.put("v_onnet_used", usedFuVoiceOnNet);
                hm.put("v_cross", fuVoiceCrossNet);
                hm.put("v_cross_used", usedFuVoiceCrossNet);
                hm.put("v_inter", fuVoiceInternational);
                hm.put("v_inter_used", usedFuVoiceInternational);
                hm.put("sms_onnet", fuSMSOnNet);
                hm.put("sms_onnet_used", usedFuSMSOnNet);
                hm.put("sms_cross", fuSMSCrossNet);
                hm.put("sms_cross_used", usedFuSMSCrossNet);
                hm.put("sms_inter", fuSMSInternational);
                hm.put("sms_inter_used", usedFuSMSInternational);
                hm.put("data", fuData);
                hm.put("data_used", usedFuData);
                hm.put("cost_ext", costExternalCharge);
                hm.put("cost_one_time", costOneTimeFee);
                getCustomerData(rs.getInt(2));

            }
            System.out.println("End Invoice----------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("error - at getting contract data from db : " + e);
        }
        return 0;
    }
public void creatPDF(){
    try {

                JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperReport, hm, new JREmptyDataSource());
                JasperExportManager.exportReportToPdfFile(jprint, fileNamePdf);

            } catch (JRException e) {
                System.out.print("Exception:" + e);
            }
}
public  void getMsisdn() throws SQLException{
    PreparedStatement ps = db.getConnection().prepareStatement("select msisdn from  billingcontract ");
            

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

    new Invoice().getAllContractsData(rs.getString(1));}
}
    public static void main(String[] args) {
        try {
            DatabaseConnection.getDatabaseInstance().connectToDatabase();
            new Invoice().getMsisdn();
            
        } catch (SQLException e) {
            Logger.getLogger(Invoice.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("error - rating connection to db : " + e);
        }
    }

}
