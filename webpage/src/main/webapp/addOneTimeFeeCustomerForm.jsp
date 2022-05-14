<%-- 
    Document   : addOneTimeFeeCustomer
    Created on : May 12, 2022, 9:12:53 PM
    Author     : ahmedmedhat
--%>

<%@page import="Schema.OneTimeFee"%>
<%@page import="java.util.Vector"%>
<%@page import="Database.HandleDB"%>
<%@ include file="header.html" %>

<%
    HandleDB db = new HandleDB();
    String contractId = request.getParameter("cid");
%>

<div class="addUser-form">
    <h1><center>Add One Time Fee For Customer</center></h1>  

    <form id="addUserForm" action="addOneTimeFeeCustomer.jsp">  
        <input type="hidden" name="contractID" value="<%=contractId%>"/>
            
        <div id = "selected" class="input-form">
            <label>One Time Fee Plan:   </label>
            <select name = "buckeid">
                <%
                    Vector<OneTimeFee> oneTF = db.getAllOneTimeFeePlanWithAllDetails();
                    int i = 1;
                    String zoneName, serviceName;
                    for (OneTimeFee oTF : oneTF) {
                        int bkID = oTF.getBucketID();
                        int bb = oTF.getCost();
                        int cc = oTF.getQuota();
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
                <option value="<%=bkID+"---"+cc%>">Bucket ID: <%=bkID%>, Service: <%=serviceName%>, Zone: <%=zoneName%> ,Cost: <%=bb%>, Quota: <%=cc%></option>
                <%
                    }%>
            </select>

        </div>

        <div class="btns">
            <button type="submit" class="btnSumbit">Submit</button>   
        </div>
            
        <p id="error-login"></p>
    </form>  
</div>
<%@ include file="footerBody.html" %>
