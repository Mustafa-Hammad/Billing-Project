<%-- 
    Document   : deleteUser
    Created on : Apr 30, 2022, 2:35:55 AM
    Author     : ahmedmedhat
--%>

<%@page import="Database.HandleDB"%>
<jsp:useBean id="u" class="User.User"></jsp:useBean>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete User</title>
    </head>
    <body>
        <%

            HandleDB db = new HandleDB();

            String queryString = request.getQueryString();          // d=789
            StringBuilder url = new StringBuilder();

            url.append("?").append(queryString);

//            out.print(url);
            String str3 = new String(url);
            String delims = "[?=]+";
            String[] arr3 = str3.split(delims);
            out.print(arr3[2]);

            db.delete(Integer.parseInt(String.valueOf(arr3[2])));
            response.sendRedirect("viewUser.jsp");

        %>      </body>
</html>
