/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Config;

import Database.DatabaseConnection;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author nora
 */
public class HandleListenerContext implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("the app is deployed");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println("the app is undeployed");

        try {
            DatabaseConnection.getDatabaseInstance().closeDatabase();

        } catch (Exception e) {
            System.out.println(e + " : error to start connection");
        }

    }

}
