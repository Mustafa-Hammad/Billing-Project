<%-- 
    Document   : ratePlan
    Created on : Apr 29, 2022, 4:06:01 PM
    Author     : ahmedmedhat
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rate Plan</title>
    </head>
    <body>
        <h1><center>Rate Plan</center></h1>
        <div>
            <form action="ratePlanAction.jsp">
                <label>ID:  </label>
                <input type="text" name="id"
                       pattern = "[0-9]+"><br>
                <label>Phone Number:  </label>
                <input type="text" name="msisdn"
                       pattern = "[0-9]+"><br>
                <label>Rate Plan:   </label>
                <select name="rateplan">
                    <option value="x150">X150</option>
                    <option value="x250">X250</option>
                    <option value="x400">X400</option>
                </select>
                <input type="submit">
            </form>
        </div>

    </body>
</html>
