/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.CDRParser;

import java.io.BufferedReader;
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
import java.util.Locale;

/**
 *
 * @author a7med
 */
public class CDRParser {

    private static boolean newCDR = false;

    private static boolean checkNewCdr(String dir_name) {
        File directory = new File(dir_name);
        File[] files_arr = directory.listFiles();
        if (files_arr.length == 0) {
            return false;
        } else {
            return true;
        }
    }

    private static File getCDR(String dir_name) {
        File directory = new File(dir_name);
        File[] files_arr = directory.listFiles();
        if (files_arr.length == 1) {
            return files_arr[0];
        } else {
            return null;
        }
    }

    public static void main(final String[] args) throws FileNotFoundException, IOException, ParseException {
        String path = System.getProperty("user.dir");
        Path p = Paths.get(path);
        Path newCdr = Paths.get(p.getParent() + "/newCdr/");
        System.out.println(newCdr.toString());
        Path dest_folder = Paths.get(p.getParent() + "/oldCdr/");
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy", Locale.ENGLISH);

        while (true) {
            boolean check = checkNewCdr(newCdr.toString());
            if (check == true) {
                newCDR = true;
                if (newCDR == true) {
                    System.out.println("New CDR Founded");
                    File my_cdr = getCDR(newCdr.toString());

                    if (my_cdr != null) {
                        String[] columns;
                        CDRDBIns data = new CDRDBIns();
                        BufferedReader in = new BufferedReader(new FileReader(my_cdr));
                        String line;
                        while ((line = in.readLine()) != null) {
                            columns = line.split(",");

                            int cdr_id = Integer.parseInt(columns[0]);
                            Date date = formatter.parse(columns[3]);
                            int rateplan_id = Integer.parseInt(columns[5]);
                            int service_id = Integer.parseInt(columns[6]);
                            float consumption = Float.parseFloat(columns[7]);
                            int externalCharge = Integer.parseInt(columns[8]);
                            boolean isRating = Boolean.parseBoolean(columns[9]);

                            CDR cdr = new CDR(cdr_id, columns[1], columns[2], date, columns[4], rateplan_id, service_id, consumption, externalCharge, isRating);
                            data.insertCDR(cdr);
                            System.out.println("Database Updated Successfully");
                        }

                        in.close();
                        Path origin_folder = Paths.get(newCdr.toString(), my_cdr.getName());
                        Files.move(origin_folder, dest_folder.resolve(my_cdr.getName()), StandardCopyOption.REPLACE_EXISTING);

                    } else {
                        System.out.println("Please archive unneeded CDRs first");
                        break;

                    }
                }
            }

        }

    }
}
