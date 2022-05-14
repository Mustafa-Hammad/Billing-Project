/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.billing.invoice;

import com.billibg.database.DatabaseConnection;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;
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
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author nora
 */
public class Invoice {

    private final DatabaseConnection db = DatabaseConnection.getDatabaseInstance();
    public static HashMap hm = new HashMap();
    LocalDate date = LocalDate.now();
    public static String fileNameJrxml = "Bill.jrxml";
    public static String fileNamePdf, to;

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
                to = rs.getString(8);
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

    public void creatPDF() {
        try {

            JasperDesign jasperDesign = JRXmlLoader.load(fileNameJrxml);
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperReport, hm, new JREmptyDataSource());
            JasperExportManager.exportReportToPdfFile(jprint, fileNamePdf);
            sendemail();
        } catch (JRException e) {
            System.out.print("Exception:" + e);
        }
    }

    public void getMsisdn() throws SQLException {
        PreparedStatement ps = db.getConnection().prepareStatement("select msisdn from  billingcontract ");

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {

            new Invoice().getAllContractsData(rs.getString(1));
        }

    }
    
    public void sendemail(){

        // Sender's email ID needs to be mentioned
        String from = "project.billingiti@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass 
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("project.billingiti@gmail.com", "123456789billing");

            }

        });
        //session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Vodafone Monthly Bill");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                attachmentPart.attachFile(fileNamePdf);
                textPart.setText("We would like to inform you that it is time to pay the monthly bill, please find the file attached to the mail. For more information, please contact us.");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {
            }

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
        }
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
