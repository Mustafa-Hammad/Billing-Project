/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Action;

import Database.HandleDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nora
 */
public class CheckAddCustomerRecurring extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HandleDB db = new HandleDB();

        String cu_id = request.getParameter("cuid");
        String ru_id = request.getParameter("ruid");
        String[] ru_Remaining = ru_id.split("---", -2);

//        String recurring = request.getParameter("recurring");
//        System.out.println(Integer.parseInt(ru_Remaining[0]) + "   : " + Integer.parseInt(ru_Remaining[1])+ " : "+cu_id);

        String resDB =  db.addRecurringToUser(Integer.parseInt(cu_id), Integer.parseInt(ru_Remaining[0]), Integer.parseInt(ru_Remaining[1]));
        response.getWriter().println(resDB);
    }

}
