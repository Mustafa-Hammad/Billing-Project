/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.billing.CDR;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

/**
 *
 * @author ahmedmedhat
 */
public class CDRGenerator {
    //to generate CDR file, first we need to create a file using File class
    //then using CSVWriter to update on it

    //Step1: Create a New File
    public static void main(String args[]) {
        FileWriter outputfile = null;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
//            String filename = dtf.format(now);
            File file = new File("./CDR/file.csv");
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + file.getName());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException ex) {
                Logger.getLogger(CDRGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
            // create FileWriter object with file as parameter
            outputfile = new FileWriter(file);
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
            // add data to csv
            //Dial A, Dial B, Service ID, Duration/message/Volume, Start time, External charges
            String[] data1 = { "DiaalA", "DialB", "ServiceID", "Duration", "StartTime", "External Charges"};
            writer.writeNext(data1);
            // closing writer connection
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(CDRGenerator.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                outputfile.close();
            } catch (IOException ex) {
                Logger.getLogger(CDRGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
