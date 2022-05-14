/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Action;

import Database.HandleDB;
import Schema.Customer;
//import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nora
 */
public class GetAllUsers extends HttpServlet {
 @Override
    public void init() {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        HandleDB db = new HandleDB();
        PrintWriter pw = res.getWriter();

        Vector<Customer> customers = db.getAllUsers();
//        Gson gson = new Gson();
//        String allCustomerS = gson.toJson(customers);
//        pw.println(allCustomerS);
    }
}
