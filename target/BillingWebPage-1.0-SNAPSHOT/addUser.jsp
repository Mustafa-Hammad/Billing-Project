<%-- 
    Document   : addUser
    Created on : Apr 25, 2022, 7:22:05 PM
    Author     : ahmedmedhat
--%>

<%@page import="Database.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add User</title>
    </head>
    <body>
         <%
            DBConnection db = new DBConnection();
                db.getConnection();

                String username = request.getParameter("name");
                String address = request.getParameter("address");
                String email = request.getParameter("email");
                String credit = request.getParameter("credit");
                    db.addUser(username, email, address, Integer.parseInt(credit));
                
%>
<jsp:include page="addUserForm.jsp"></jsp:include>
    </body>
</html>
