<%-- 
    Document   : reatePlanAction
    Created on : Apr 29, 2022, 4:14:24 PM
    Author     : ahmedmedhat
--%>
<%@page import="java.util.List"%>
<%@page import="Database.DBConnection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rate Plan</title>
    </head>
    <body>
        <jsp:include page="ratePlan.jsp"></jsp:include>
        <%
            //Connection with database
            DBConnection db = new DBConnection();
            db.getConnection();
            
            String id = request.getParameter("id");
            String ratePlan = request.getParameter("rateplan");
            String msisdn = request.getParameter("msisdn");

            //Check if phone number for user used in a contract or not
            int checkID = db.checkID(id, msisdn, ratePlan);
            


        %>
    </body>
</html>
