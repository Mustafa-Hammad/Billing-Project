<%-- 
    Document   : viewOneTimeFee
    Created on : May 12, 2022, 5:05:19 PM
    Author     : ahmedmedhat
--%>

<%@page import="Schema.OneTimeFee"%>
<%@page import="java.util.Vector"%>
<%@page import="Database.HandleDB"%>
<%@ include file="header.html" %>

<%
    HandleDB db = new HandleDB();
%>

<div class="viewDetailsUser">
    <h1>
        <center>One Time Fee plans</center>
    </h1>

    <div class="cardrp">
        <%
            Vector<OneTimeFee> oneTF = db.getAllOneTimeFeePlanWithAllDetails();
            for (OneTimeFee oTF : oneTF) {
                String zoneName, serviceName;
                if (oTF.getZoneID() == 1) {
                    zoneName = "On Net";
                } else if (oTF.getZoneID() == 2) {
                    zoneName = "Cross Net";
                } else {
                    zoneName = "International";
                }
                if (oTF.getServiceID() == 1) {
                    serviceName = "Voice";
                } else if (oTF.getServiceID() == 2) {
                    serviceName = "SMS";
                } else {
                    serviceName = "Mobile Data";
                }
        %>
        <div class="card">
            <div class="card-header">
                <p> One Time Fee Plan ID : <%=oTF.getBucketID()%></p> 
                <p> Service : <%=serviceName%></p> 
                <p> Zone : <%=zoneName%></p> 
            </div>

            <div class="card-body">
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <td>External Free Unit</td>
                            <td><%=oTF.getQuota()%></td>                           
                        </tr>
                        <tr>
                            <td> Monthly Fee : </td>
                            <td><%=oTF.getCost()%></td>
                        </tr>
                    </tbody>
                </table>
            </div> 

            <div class="card-footer d-flex justify-content-around">

            </div>
        </div>
        <% }%>
    </div>
    <%@ include file="footer.html" %>
    <%@ include file="footerBody.html" %>
