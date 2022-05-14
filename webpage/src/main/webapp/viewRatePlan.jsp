<%-- 
    Document   : viewRatePlan
    Created on : May 12, 2022, 7:03:22 AM
    Author     : nora
--%>

<%@page import="java.util.Vector"%>
<%@page import="Schema.RatePlan"%>
<%@page import="Database.HandleDB"%>
<%@ include file="header.html" %>

<%
    HandleDB db = new HandleDB();
%>

<div class="viewDetailsUser">
    <h1>
        <center>Rate plans</center>
    </h1>

    <div class="cardrp">
        <%
            Vector<RatePlan> ratePlans = db.getAllRatePlanWithAllDetails();
            for (RatePlan ratePlan : ratePlans) {%>
        <div class="card">
            <div class="card-header">
                <p> Rate Plan ID : <%=ratePlan.getId()%></p> 
                <p> Name :  <%=ratePlan.getName()%></p>
                <p> Monthly Fee : <%=ratePlan.getMonthlyfee()%></p> </div>

            <div class="card-body">
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <td>FU VOICE On Net</td>
                            <td><%=ratePlan.getFuVOnNet()%></td>
                            <td>Extra cost <%=ratePlan.getExtraVOnNet()%></td>

                        </tr>
                        <tr>
                            <td>FU VOICE Cross Net</td>
                            <td><%=ratePlan.getFuVOnCross()%></td>
                            <td>Extra cost <%=ratePlan.getExtraVOnNet()%></td>

                        </tr>
                        <tr>
                            <td>FU VOICE International</td>
                            <td><%=ratePlan.getFvVInter()%></td>
                            <td>Extra cost <%=ratePlan.getExtraVInter()%></td>

                        </tr>
                        <tr>
                            <td>FU SMS On Net</td>
                            <td><%=ratePlan.getFuSOnNet()%></td>
                            <td>Extra cost <%=ratePlan.getExtraSOnNet()%></td>

                        </tr>
                        <tr>
                            <td>FU SMS Cross Net</td>
                            <td><%=ratePlan.getFuSOnCross()%></td>
                            <td>Extra cost <%=ratePlan.getExtraSOnCross()%></td>

                        </tr>
                        <tr>
                            <td>FU SMS International</td>
                            <td><%=ratePlan.getFvSInter()%></td>
                            <td>Extra cost <%=ratePlan.getExtraSInter()%></td>

                        </tr>
                        <tr>
                            <td>FU Data</td>
                            <td><%=ratePlan.getFvData()%></td>
                            <td>Extra cost <%=ratePlan.getExtraData()%></td>

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

