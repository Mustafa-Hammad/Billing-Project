<%-- 
    Document   : addOneTimeFeeCustomer
    Created on : May 13, 2022, 8:44:34 PM
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
String contractId = request.getParameter("contractID");
String bkIDQuo = (String) request.getParameter("buckeid");
String[] para = bkIDQuo.split("---",2); 

//out.print(para[0]);

db.contractAddOneTimeFee(para[0], contractId, para[1]);

//at the end
response.sendRedirect("viewUser.jsp");
%>

