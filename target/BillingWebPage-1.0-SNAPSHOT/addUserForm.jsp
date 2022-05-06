<%-- 
    Document   : addUser
    Created on : Apr 25, 2022, 5:16:49 PM
    Author     : ahmedmedhat
--%>

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
                        <input type="text" name="name"
                                pattern=".{5,25}"/></td>
                    </tr>   
                    <tr>
                        <td>Email:</td>
                        <td><input type="email" name="email"
                                   pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-z]{2,}$"></td>
                    </tr>
                    <tr>
                        <td>Password </td>
                        <td><input type="password" name="pass"
                                   pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}"/></td>
                    </tr> 
                    <tr>
                        <td>Credit:</td>
                        <td><input type="text" name="credit" 
                                   minlength="1" pattern = "[0-9]+"></td>
                    </tr>
               </table>
                <input type="submit" value="Check"></input>
            </form>  
        </div>
        <div id="mainR">
            <a href="mainPage.jsp">Main Page</a><br/>  
        </div>
        
       
    </body>
</html>
