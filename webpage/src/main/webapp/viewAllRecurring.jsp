<%-- 
    Document   : viewAllRecurring
    Created on : May 14, 2022, 2:44:39 AM
    Author     : nora
--%>


<%@page import="Schema.Product"%>
<%@page import="java.util.Vector"%>
<%@page import="Schema.Customer"%>
<%@page import="Database.HandleDB"%>

<%@ include file="header.html" %>

<div class="viewUsers">
    <h1><center>All Recurring</center></h1>
    <table class="table table-hover">
        <thead>
            <tr>
                <th>ID</th>
                <th>Product</th>
                <th>Cost</th>
                <th>Number Of Months</th>

                <th>Cost Per Month</th>
            </tr>
        </thead>
        <tbody>
            <%
                HandleDB db = new HandleDB();
                Vector<Product> products = db.getAllRecurring();
                for (Product product : products) {%>
            <tr>
                <td><%=product.getId()%></td>
                <td><%=product.getProduct()%></td>
                <td><%=product.getCost()%></td>
                <td><%=product.getNumberOfMonths()%></td>
                <td><%=product.getCostPerMonth()%></td>
            </tr>                 

        <% }
        %>

        </tbody>
    </table>
</div>
<%@ include file="footer.html" %>

<%@ include file="footerBody.html" %>

