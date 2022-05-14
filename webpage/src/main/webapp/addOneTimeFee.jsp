<%-- 
    Document   : addOneTimeFee
    Created on : May 12, 2022, 7:30:06 PM
    Author     : ahmedmedhat
--%>

<%@page import="Schema.OneTimeFee"%>
<%@page import="java.util.Vector"%>
<%@page import="Database.HandleDB"%>
<%@ include file="header.html" %>

<%
    HandleDB db = new HandleDB();
%>


<%

//take parameters from form
String serviceID = request.getParameter("service");
String zoneID = request.getParameter("zone");
String qouta = request.getParameter("quota");
String cost = request.getParameter("cost");

db.addOneTimeFee(serviceID, zoneID, qouta, cost);

//at the end
response.sendRedirect("addOneTimeFeeFormat.jsp");
%>

