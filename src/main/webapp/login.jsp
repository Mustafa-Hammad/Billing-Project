<%-- 
    Document   : try
    Created on : Apr 25, 2022, 1:45:03 PM
    Author     : ahmedmedhat
--%>


<!--import-->
<%@page import="Database.DBConnection"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.servlet.ServletException"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="loginHeader.html"%>
<!-- <%out.print("Current Login Time:\t"+java.util.Calendar.getInstance().getTime());%> -->
<%
    DBConnection db = new DBConnection();
    db.getConnection();
    
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    boolean b = db.checkLogin(username, password);
    if(b){
        out.print("login sucsseful");
    }else{
        out.print("<p style='color:Red'>Invalid Login</p>");
    }
    

%>
<%@include file="loginFooter.html"%>


