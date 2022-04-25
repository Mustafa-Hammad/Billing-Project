/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Action;

import Database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//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
/**
 *
 * @author ahmedmedhat
 */
public class CheckLogin extends HttpServlet {

    @Override
    public void init() {
        // for database connection
//        DBConnection db = new DBConnection();
//        db.getConnection();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        super.doPost(req, res);

        //for database connection and process
        // i hope that i can put it in init
        DBConnection db = new DBConnection();
        db.getConnection();
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        boolean b = db.checkLogin(userName, password);
        
        if (b) {
            req.getRequestDispatcher("login.jsp").forward(req, res);
        } else {
            res.sendError(401, "You Can not access the material"); //Non-Authoritative Information
            pw.println("<p style='color:Red'>login error</p>");
        }

    }

}
