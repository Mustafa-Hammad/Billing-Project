<%-- 
    Document   : viewUser
    Created on : Apr 26, 2022, 10:48:58 PM
    Author     : ahmedmedhat
--%>


<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.List"%>
<%@page import="User.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="Database.DBConnection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Customers</title>
    </head>
    <body>
        <h1><center>All Customers Data</center></h1>
        <%
            //connect to database
            DBConnection db = new DBConnection();
            db.getConnection();
        %>
        <div>
            <%
                out.print("<table border=1>"
                        + "<thead id=cstdata>"
                        + "<tr>"
                        + "<th colspan=11> Data</th>"
                        + "</tr>"
                        + "</thead>"
                        + "<tr>"
                        + "<td><b>ID</b></td>"
                        + "<td><b>Name</b></td>"
                        + "<td><b>e-mail</b></td>"
                        + "<td><b>Password</b></td>"
                        + "<td><b>Credit</b></td>"
                        + "<td><b>Status</b></td>"
                        + "<td><b>Rateplan Name</b></td>"
                        + "<td><b>Edit RP</b></td>"
                        + "<td><b>Invoice</b></td>"
                        + "<td><b>Edit</b></td>"
                        + "<td><b>Delete</b></td>"
                        + "</tr>");
//                List<User> list = db.getAllRecords();
//                request.setAttribute("list", list);
            %>
            <%
                String query = "select c.*,rp.name as rpname ,contr.contractstate"
                        + " from customer c, contract contr, rateplan rp"
                        + " where c.cu_id = contr.cu_id"
                        + " and"
                        + " contr.rp_id=rp.rp_id;";
                Statement stmt = db.c.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                

                // Loop through the result set to 
                // retrieve the individual data items.
                while (rs.next()) {
                    User u = new User();
                    int cstID = rs.getInt("cu_id");
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    Integer credit = rs.getInt("credit");
                    String rpName = rs.getString("rpname");
                    String status = rs.getString("contractstate");
                    
                    u.setId(cstID);
                    u.setName(name);
                    u.setEmail(email);
                    u.setPassword(password);
                    u.setCredit(credit);
                    u.setRpName(rpName);
                    u.setContrStatus(status);
                    out.println("<tr>"
                            + "<td>" + cstID + "</td>"
                            + "<td>" + name + "</td>"
                            + "<td>" + email + "</td>"
                            + "<td>" + password + "</td>"
                            + "<td>" + credit + "</td>"
                            + "<td>" + rpName + "</td>"
                            + "<td>" + status + "</td>"
                            + "<td> <a href = 'editRPFormAdmin.jsp' > Rate Plan </a></td>"
                            + "<td> <a href = 'invoiceFormAdmin.jsp' > Invoice </a> </td>"
                            + "<td> <a href = 'editUserForm.jsp?id="+u.getId()+"' target='_blank'> Edit </a></td>"
                            + "<td> <a href = 'deleteUser.jsp?id="+u.getId()+"' target='_blank'> Delete </a></td>"
                            + "</tr>"
                            );
                }
                out.println("</table></body></html>");
            %>

        </div>
    </body>
</html>
