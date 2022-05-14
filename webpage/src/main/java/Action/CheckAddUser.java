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
public class CheckAddUser extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HandleDB db = new HandleDB();

        String username = request.getParameter("name");
        String password = request.getParameter("pass");
        String email = request.getParameter("email");
        String cuid = request.getParameter("cuid");

        String resDB =  db.addUser(username, email, password, Integer.parseInt(cuid));
        response.getWriter().println(resDB);


    }

}
