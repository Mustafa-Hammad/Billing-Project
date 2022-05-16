/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CDRParser;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author a7med
 */
public class CDRParser {

    private static boolean newCDR = false;

    private static boolean checkNewCdr(String dir_name) {
        File directory = new File(dir_name);
        File[] files_arr = directory.listFiles();
        return files_arr.length != 0;
    }

    private static File readCDR(String dir_name) throws FileNotFoundException, IOException, ParseException {
        File directory = new File(dir_name);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        File[] files_arr = directory.listFiles();
        for (File file : files_arr) {
            if (file != null) {
                String[] columns;
                CDRDBIns data = new CDRDBIns();
                CSVReader in = new CSVReader(new FileReader(file));
                String[] line;
                while ((line = in.readNext()) != null) {
                    columns = line;

                    Date date = formatter.parse(columns[2]);
                    int rateplan_id = Integer.parseInt(columns[4]);
                    int service_id = Integer.parseInt(columns[5]);
                    float consumption = Float.parseFloat(columns[6]);
                    int externalCharge = Integer.parseInt(columns[7]);
                    boolean isRating = Boolean.parseBoolean(columns[8]);

                    CDR cdr = new CDR(columns[0], columns[1], date, columns[3], rateplan_id, service_id, consumption, externalCharge, isRating);
                    data.insertCDR(cdr);
                    System.out.println("Database Updated Successfully");
                    in.close();
                    return file;
                }
            } else {
                System.out.println("CDR is empty");
                break;

            }

        }
        return null;
    }

    public static void main(final String[] args) throws FileNotFoundException, IOException, ParseException {
        String path = System.getProperty("user.dir");
        Path p = Paths.get(path);
        Path newCdr = Paths.get(p.getParent() + "/newCdr/");
        Path dest_folder = Paths.get(p.getParent() + "/oldCdr/");

        while (true) {
            boolean check = checkNewCdr(newCdr.toString());
            if (check == true) {
                newCDR = true;
                if (newCDR == true) {
                    System.out.println("New CDR Founded");
                    File my_cdr = readCDR(newCdr.toString());
                    Path origin_folder = Paths.get(newCdr.toString(), my_cdr.getName());
                    Files.move(origin_folder, dest_folder.resolve(my_cdr.getName()), StandardCopyOption.REPLACE_EXISTING);
                }
            }

        }

    }
}
