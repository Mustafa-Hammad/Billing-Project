/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CDRParser;

import com.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Mustafa Raed
 */
public class CreateCDR {

    public static void createNewCDR(String from, String to, LocalDate date, LocalTime time, int rateplan_id, int service_id, float consumption, int externalCharge, boolean isRating) {
        String path = System.getProperty("user.dir");
        Path p = Paths.get(path);
        Path newCdr = Paths.get(p.getParent() + "/newCdr/");

        File file = new File(newCdr.toString()+"/"+date.toString()+"-"+ time.toString()+".csv");
        System.out.println(file);
        try {
            FileWriter outputfile = new FileWriter(file);
            String rateplan = Integer.toString(rateplan_id);
            String service = Integer.toString(service_id);
            String consum = Float.toString(consumption);
            String external = Integer.toString(externalCharge);
            String Rating = Boolean.toString(isRating);
            try ( CSVWriter writer = new CSVWriter(outputfile)) {
                String[] header = {from, to, date.format(DateTimeFormatter.ISO_DATE), time.toString(), rateplan, service, consum, external, Rating};
                writer.writeNext(header);
            }
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        LocalTime time = LocalTime.now();
        
        String from ;
        String to ;
        LocalDate date = LocalDate.now();
        int rateplan_id ;
        int service_id ;
        float consumption = 0;
        int externalCharge ;
        boolean isRating = false;
        Scanner sc = new Scanner(System.in); 
        System.out.print("From- ");
        from = sc.next();
        System.out.print("To- ");
        to = sc.next();
        System.out.print("rateplan id- ");
        rateplan_id = sc.nextInt();
        System.out.print("service id- ");
        service_id = sc.nextInt();
        System.out.print("externalCharge- ");
        externalCharge = sc.nextInt();
        
        

        createNewCDR(from, to, date, time, rateplan_id, service_id, consumption, externalCharge, isRating);
    }
}
