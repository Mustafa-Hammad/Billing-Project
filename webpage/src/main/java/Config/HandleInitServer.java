/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Config;

import Database.DatabaseConnection;
import javax.servlet.http.HttpServlet;


/**
 *
 * @author nora
 */
public class HandleInitServer extends HttpServlet {

    @Override
    public void init() {
        try {
            DatabaseConnection.getDatabaseInstance().connectToDatabase();
        } catch (Exception e) {
            System.out.println(e + " : error to start connection");
        }
    }
}
