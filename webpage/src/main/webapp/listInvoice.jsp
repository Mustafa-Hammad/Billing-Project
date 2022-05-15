<%-- 
    Document   : viewUser
    Created on : Apr 26, 2022, 10:48:58 PM
    Author     : ahmedmedhat
--%>


<%@page import="java.io.File"%>
<%@page import="java.util.Vector"%>
<%@page import="Schema.Customer"%>
<%@page import="Database.HandleDB"%>

<%@ include file="header.html" %>

<div class="viewUsers">
    <h1><center>All PDFs</center></h1>

    <input class="form-control" id="myInput" type="text" placeholder="Search..">



    <table class="table table-hover">
        <thead>
            <tr>
                <th>Name</th>
                <th>Date</th>
                <th>MSISDN</th>
                <th>pdf</th>
            </tr>
        </thead>
        <tbody id="myTable">
            <%
                String file = application.getRealPath("/pdf");
                File f = new File(file);
                String[] fileNames = f.list();
                File[] fileObjects = f.listFiles();
                for (int i = 0; i < fileObjects.length; i++) {
                    if (!fileObjects[i].isDirectory()) {
                        String fname = file + fileNames[i];
                        String[] splitFileName = fileNames[i].split("_");
            %>
            <tr>
                <td><%=splitFileName[0]%></td>
                <td><%=splitFileName[1]%></td>
                <td><%=splitFileName[2]%></td>
                <td>  <a href="pdf/<%=fileNames[i]%>" target="pdf-frame" class="btn btn-primary" role="button">Show PDF</a></td>

            </tr> 
            <% }
                }
            %>

        </tbody>
    </table>
</div>
<%@ include file="footer.html" %>

<script>
    $(document).ready(function () {
        $("#myInput").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#myTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });
    });
</script>

<%@ include file="footerBody.html" %>
