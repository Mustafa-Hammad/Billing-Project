/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Action;

import Database.DatabaseConnection;
import Database.HandleDB;
import Schema.RatePlan;
import Schema.ServiceT;
import Schema.ServiceType;
import Schema.Zone;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nora
 */
public class CheckAddRatePlan extends HttpServlet implements Zone, ServiceType {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            HandleDB db = new HandleDB();

            RatePlan rp = new RatePlan(request.getParameter("name"), Integer.parseInt(request.getParameter("monthlyfee")));

            ServiceT vonnet = new ServiceT("Voice On Net", CALL, ONNET, request.getParameter("fuvoiceonnet"), request.getParameter("extravoiceonnet"));
            ServiceT vcrossnet = new ServiceT("Voice NetCross", CALL, CROSSNET, request.getParameter("fuvoicecrossnet"), request.getParameter("extravoicecrossnet"));
            ServiceT vinter = new ServiceT("Voice International", CALL, INTERNATIONAL, request.getParameter("fuvoiceinter"), request.getParameter("extravoiceinter"));

            ServiceT sonnet = new ServiceT("SMS On Net", SMS, ONNET, request.getParameter("fusmsonnet"), request.getParameter("extrasmsonnet"));
            ServiceT scrossnet = new ServiceT("SMS Cross Net", SMS, CROSSNET, request.getParameter("fusmscrossnet"), request.getParameter("extrasmscrossnet"));
            ServiceT sinter = new ServiceT("SMS International", SMS, INTERNATIONAL, request.getParameter("fusmsinter"), request.getParameter("extrasmsinter"));

            ServiceT data = new ServiceT("Data", DATA, DATAZONE, request.getParameter("fudata"), request.getParameter("extradata"));

            List<ServiceT> services = new ArrayList<ServiceT>();
            services.add(vonnet);
            services.add(vcrossnet);
            services.add(vinter);
            services.add(sonnet);
            services.add(scrossnet);
            services.add(sinter);
            services.add(data);
//        RatePlan rp = new RatePlan(request.getParameter("name"), Integer.parseInt(request.getParameter("monthlyfee")),
//                Integer.parseInt(request.getParameter("fuvoiceonnet")), Integer.parseInt(request.getParameter("fuvoicecrossnet")),
//                Integer.parseInt(request.getParameter("fuvoiceinter")),
//                Integer.parseInt(request.getParameter("fusmsonnet")), Integer.parseInt(request.getParameter("fusmscrossnet")),
//                Integer.parseInt(request.getParameter("fusmsinter")),
//                Integer.parseInt(request.getParameter("fudata")),
//                Integer.parseInt(request.getParameter("extravoiceonnet")),
//                Integer.parseInt(request.getParameter("extravoicecrossnet")),
//                Integer.parseInt(request.getParameter("extravoiceinter")), Integer.parseInt(request.getParameter("extrasmsonnet")),
//                Integer.parseInt(request.getParameter("extrasmscrossnet")),
//                Integer.parseInt(request.getParameter("extrasmsinter")), Integer.parseInt(request.getParameter("extradata")));
//
            DatabaseConnection.getDatabaseInstance().getConnection().setAutoCommit(false);
            String resDB = db.addNewRatePlan(rp, services);
            System.out.println("test1");
            System.out.println(resDB);

            DatabaseConnection.getDatabaseInstance().getConnection().setAutoCommit(true);
            response.getWriter().println(resDB);
        } catch (SQLException e) {
            System.out.println("error: " +e );
        }
    }
}
