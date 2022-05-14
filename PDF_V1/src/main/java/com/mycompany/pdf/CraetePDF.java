/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pdf;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import java.io.IOException;
import java.time.LocalDate;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

/**
 *
 * @author Mustafa Raed
 */
public class CraetePDF {

    private final static String OWNER_PASSWORD = "javatpoint";


    public void create(String PASSWORD, String user, LocalDate Date, String MSISDN, String email, long calls, long calls_cost, long SMS, long SMS_cost, long Data, long Data_cost) {
        String USER_PASSWORD = PASSWORD;
        try {
//location where PDF will be generated    
            FileOutputStream fos = new FileOutputStream("E:/Billing/" + user + "_" + Date + ".pdf");
            System.out.println("Password Protected File Generated.");
//creates an instance of PDF document  
            Document doc = new Document();
//doc writer for the PDF  
            PdfWriter writer = PdfWriter.getInstance(doc, fos);
            writer.setEncryption(USER_PASSWORD.getBytes(), OWNER_PASSWORD.getBytes(), PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);
//opens the PDF  
            doc.open();
//description the pdf
            doc.addAuthor("Mustafa");
            doc.addCreationDate();
            doc.addCreator("Mustafa");
            doc.addTitle("Billing system");
            doc.addSubject("Monthly Mobile Bill");
//adding paragraphs to the PDF  
            doc.add(new Paragraph("                                                                 BILLING SYSTEM"));
            doc.add(new Paragraph("                                       "));
            doc.add(new Paragraph("MSISDN: " + MSISDN));
            doc.add(new Paragraph("Email: " + email));
            doc.add(new Paragraph("Date: " + Date));
            doc.add(new Paragraph("--------------------------------------------------------"));
            doc.add(new Paragraph("Total Calls (min):  " + calls));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("                                             Calls Cost:           " + calls_cost));
            doc.add(new Paragraph("                                                                                          +"));
            doc.add(new Paragraph("Total SMS:           " + SMS));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("                                             SMS Cost:             " + SMS_cost));
            doc.add(new Paragraph("                                                                                          +"));
            doc.add(new Paragraph("Total Data (MB):   " + Data));
            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("                                             Data Cost:            " + Data_cost));
            doc.add(new Paragraph("---------------------------------------------------------------------------------------------"));
            doc.add(new Paragraph("                                             Total Cost:           " + (Data_cost + SMS_cost + calls_cost)));
            doc.add(new Paragraph("                                       "));
            doc.add(new Paragraph("                                       "));
            doc.add(new Paragraph("Billing Manager: Ahmed"));
            doc.add(new Paragraph("Billing Manager signature: "));
             Image img = Image.getInstance("signature.png");
            doc.add(img);
//closes the document  
            doc.close();
//closes the output stream  
            fos.close();
        } //catch the exception if any   
        catch (DocumentException | IOException e) {
//prints the occurred exception

        }

    }

}
