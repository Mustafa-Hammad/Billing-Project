<%-- 
    Document   : addUser
    Created on : Apr 25, 2022, 5:16:49 PM
    Author     : ahmedmedhat
--%>

<%@page import="Database.DBConnection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style><%@include file="./style.css"%></style>
        <title>Add User</title>
    </head>
    <body>
        <h1><center>Add New User</center></h1>  
        <div id="userForm">
            <form action="addUser.jsp" method="POST">
               <table>  
                    <tr>
                        <td>User Name: </td><td>
                        <input type="text" name="name"/></td>
                    </tr>   
                    <tr>
                        <td>Email:</td>
                        <td><input type="email" name="email"
                                   pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$"></td>
                    </tr>
                    <tr>
                        <td>Password </td>
                        <td><input type="password" name="pass"
                                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"/></td>
                    </tr> 
                    <tr>
                        <td>Credit:</td>
                        <td><input type="text" name="credit"
                                   pattern = "[0-9]+"></td>
                    </tr>
               </table>
                <input type="submit">
            </form>  
        </div>
        <div id="mainR">
            <a href="mainPage.jsp">Main Page</a><br/>  
        </div>
        
       
    </body>
</html>
