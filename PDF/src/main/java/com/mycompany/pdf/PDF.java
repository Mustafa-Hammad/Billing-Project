/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.pdf;

import java.time.LocalDate;

/**
 *
 * @author Mustafa Raed
 */
public class PDF {

    public static void main(String args[]) {
        LocalDate date = LocalDate.now();
        
           CraetePDF a = new CraetePDF();
           a.create("1234567", "mustafa", date, "01100081688", "Mustafa@gmail.com", 20, 40, 10, 5, 1800, 60);
        }
    }

