<%-- 
    Document   : deleteUser
    Created on : Apr 30, 2022, 2:35:55 AM
    Author     : ahmedmedhat
--%>

<%@page import="Database.HandleDB"%>
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
            String cuid = request.getParameter("id");
//            out.print(cuid);
            db.delete(Integer.parseInt(cuid));
            response.sendRedirect("viewUser.jsp");

        %>      </body>
</html>
