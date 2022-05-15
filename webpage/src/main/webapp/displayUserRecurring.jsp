<%-- 
    Document   : displayUserRecurring
    Created on : May 14, 2022, 3:11:38 AM
    Author     : nora
--%>


<%@page import="Schema.Product"%>
<%@page import="java.util.Vector"%>
<%@page import="Schema.Customer"%>
<%@page import="Database.HandleDB"%>

<%@ include file="header.html" %>

<%
    HandleDB db = new HandleDB();
%>

<div class="viewUsers">
    <h1><center>Customers Recurring
            <!-- Button to Open the Modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addContract">
                New Recurring
            </button>
        </center>
    </h1>



    <!-- The Modal -->
    <div class="modal" id="addContract">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Add new contract</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form id="addNewRecurringCustomer">  
                        <input type="hidden" name="cuid" value="<%=request.getParameter("id")%>"><br>

                        <select class="form-control" id="rpid" name="ruid">
                            <%
                                Vector<Product> productsToShow = db.getAllRecurring();
                                for (Product product : productsToShow) {%>             
                            <option value="<%=product.getId()+"---"+product.getNumberOfMonths()%>"><%= product.getProduct()+ " , Cost per month : " + product.getCostPerMonth()+ " , Total cost : " + product.getCost() + " , Number of Months : " + product.getNumberOfMonths()%></option>
                            <% }%>
                        </select>

                        <div class="btns">
                            <button type="submit" class="btn btn-info m-2">Buy</button>   
                        </div>

                        <p id="error-login"></p>
                    </form>  
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>







    <div class="d-flex justify-content-around bg-secondary">
        <div class="p-2 bg-dark text-white">ID : <%=request.getParameter("id")%></div>
        <div class="p-2 bg-dark text-white">Name : <%=request.getParameter("name")%></div>
    </div>

    <table class="table table-hover">
        <thead>
            <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Cost Per Month</th>
                <th>Number Of Months</th>
                <th>Remaining</th>
            </tr>
        </thead>
        <tbody>
            <%
                Vector<Product> products = db.getAllRecurringForUser(Integer.parseInt(request.getParameter("id")));
                for (Product product : products) {%>
            <tr>
                <td><%=product.getId()%></td>
                <td><%=product.getProduct()%></td>
                <td><%=product.getCostPerMonth()%></td>
                <td><%=product.getNumberOfMonths()%></td>
                <td><%=product.getRemaining()%></td>

            </tr>   
            <% }
            %>

        </tbody>
    </table>
</div>
<%@ include file="footer.html" %>


<script>
    let form = document.getElementById("addNewRecurringCustomer");

    const submitvalidation = (e) => {
        e.preventDefault();
        // Get all field data from the form
        let data = new FormData(form);
        // Convert to a query string
        queryString = "?" + new URLSearchParams(data).toString();
        console.log(queryString)

        var url = "/postbaidSystem/CheckAddCustomerRecurring" + queryString;

        if (window.XMLHttpRequest) {
            request = new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            request = new ActiveXObject("Microsoft.XMLHTTP");
        }

        try {
            request.onreadystatechange = sendInfo;
            request.open("POST", url, true);
            request.send();

        } catch (e) {
            alert("Unable to connect server");
        }
    }



    function sendInfo() {
        if (request.readyState == 4) {
            if (request.responseText.split("___")[0] == "true") {
                document.getElementById("error-login").innerHTML = "Done buy Prodcut";
                document.getElementById("error-login").style.color = "green";
            } else {
                document.getElementById("error-login").innerHTML = request.responseText.split("___")[1];
                document.getElementById("error-login").style.color = "red";

            }
        }
    }
    form.addEventListener("submit", submitvalidation);


</script>


<%@ include file="footerBody.html" %>
