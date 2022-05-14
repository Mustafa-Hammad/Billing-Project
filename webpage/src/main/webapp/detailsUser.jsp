<%@page import="Schema.OneTimeFeeForCst"%>
<%@page import="Schema.OneTimeFee"%>
<%@page import="Schema.RatePlan"%>
<%@page import="Schema.Contract"%>
<%@page import="java.util.Vector"%>
<%@page import="Schema.Customer"%>
<%@page import="Database.HandleDB"%>

<%@ include file="header.html" %>

<%
    HandleDB db = new HandleDB();
%>

<div class="viewDetailsUser">
    <h1>
        <center>Customer Details
            <!-- Button to Open the Modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addContract">
                New Contract
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
                    <form id="addNewContract">  
                        <input type="hidden" name="cuid" value="<%=request.getParameter("id")%>"><br>

                        <div class="input-form">
                            <label for="msisdn">MSISDN</label>
                            <input type="text" placeholder="Enter MSISDN" name="msisdn" required><br>
                        </div>

                        <select class="form-control" id="rpid" name="rpid">
                            <%
                                Vector<RatePlan> rateplans = db.getAllRatePlan();
                                for (RatePlan rateplan : rateplans) {%>             
                            <option value="<%=rateplan.getId()%>"><%= rateplan.getName() + " , MonthlyFee : " + rateplan.getMonthlyfee()%></option>
                            <% }%>
                        </select>

                        <div class="btns">
                            <button type="submit" class="btn btn-info m-2">Add new contract</button>   
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
        <div class="p-2 bg-dark text-white">Email : <%=request.getParameter("email")%></div>
    </div>

    <div class="card-deck">
        <%
            String uID = request.getParameter("id");
            Vector<Contract> contracts = db.getAllContracts(Integer.parseInt(uID));
            for (Contract contract : contracts) {%>
        <div class="card">
            <div class="card-header">
                <p> Contract ID :  <%=contract.getId()%></p>
                <p> MSISDN :  <%=contract.getMsisdn()%></p>
                <p> Rate Plan ID : <%=contract.getRpId()%></p> </div>
            <div class="card-body">
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <td>FU VOICE On Net</td>
                            <td><%=contract.getFuVOnNet()%></td>
                        </tr>
                        <tr>
                            <td>FU VOICE Cross Net</td>
                            <td><%=contract.getFuVOnCross()%></td>

                        </tr>
                        <tr>
                            <td>FU VOICE International</td>
                            <td><%=contract.getFvVInter()%></td>
                        </tr>
                        <tr>
                            <td>FU SMS On Net</td>
                            <td><%=contract.getFuSOnNet()%></td>
                        </tr>
                        <tr>
                            <td>FU SMS Cross Net</td>
                            <td><%=contract.getFuSOnCross()%></td>
                        </tr>
                        <tr>
                            <td>FU SMS International</td>
                            <td><%=contract.getFvSInter()%></td>
                        </tr>
                        <tr>
                            <td>FU Data</td>
                            <td><%=contract.getFvData()%></td>
                        </tr>
                    </tbody>
                </table>
            </div> 
            <div class="card-footer d-flex justify-content-around">
                <a href="#" class="btn btn-info" role="button">Change Rate Plan</a>
                <a href="addOneTimeFeeCustomerForm.jsp?cid=<%=contract.getId()%>" class="btn btn-info" role="button">Add One time fee</a>
            </div>
        </div>
        <% }%>
        
        <div class="card" >
        <%
        Vector<OneTimeFeeForCst> oneTF = db.getOneTimeFeePlanWithAllDetailsForCustomer(uID);
        for (OneTimeFeeForCst oTF : oneTF) {%>
        
            <div class="card-header">
                <p> Contract of One Fee Time :  <%=oTF.getContractOTFID()%></p>
                <p> Contract of Customer :  <%=oTF.getContractID()%></p>
            </div>    
            <div class="card-body">
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <td>Bucket ID:  </td>
                            <td><%=oTF.getBucketID()%></td>

                        </tr>
                        <tr>
                            <td>Quota: </td>
                            <td><%=oTF.getConsumtion()%></td>
                        </tr>
                   
                    </tbody>
                </table>
            </div> 
        
        <% }%>
   </div>
</div>


        
<%@ include file="footer.html" %>


<script>
    let form = document.getElementById("addNewContract");

    const submitvalidation = (e) => {
        e.preventDefault();
        // Get all field data from the form
        let data = new FormData(form);
        // Convert to a query string
        queryString = "?" + new URLSearchParams(data).toString();
        console.log(queryString)

        var url = "/postbaidSystem/CheckAddContract" + queryString;

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
                document.getElementById("error-login").innerHTML = "Done add user";
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
