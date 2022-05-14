<%-- 
    Document   : addUser
    Created on : Apr 25, 2022, 5:16:49 PM
    Author     : ahmedmedhat
--%>
<%@ include file="header.html" %>

<div class="addRate-form">
    <h1><center>Add Rate Plan</center></h1>  
    <form id="addRatePlanForm">  

        <div class="input-form">
            <label for="name">Name</label>
            <input type="text" placeholder="Enter Name Rate Plan" name="name" required><br>
        </div>

        <div class="input-form">
            <label for="monthlyfee">Monthly Fee</label>
            <input type="number" placeholder="Enter Monthly Fee" name="monthlyfee" required><br>
        </div>


        <div class="input-form d-flex justify-content-between" style="gap:5px">
            <label for="fuvoiceonnet">Free Unit voice On-Net</label>
            <input type="number" placeholder="Enter free unit voice on net" name="fuvoiceonnet" required><br>
            <input type="number" placeholder="Enter External cost" name="extravoiceonnet"required ><br>
        </div>


        <div class="input-form d-flex justify-content-between" style="gap:5px">
            <label for="fuvoicecrossnet">Free Unit voice Net-Cross</label>
            <input type="number" placeholder="Enter free unit voice cross net " name="fuvoicecrossnet" ><br>
            <input type="number" placeholder="Enter External cost" name="extravoicecrossnet" ><br>
        </div>


        <div class="input-form d-flex justify-content-between" style="gap:5px">
            <label for="fuvoiceinternational ">Free Unit voice International</label>
            <input type="number" placeholder="Enter free unit voice International" name="fuvoiceinter" ><br>
            <input type="number" placeholder="Enter External cost" name="extravoiceinter" ><br>
        </div>


        <div class="input-form d-flex justify-content-between" style="gap:5px">
            <label for="fusmsonnet">Free Unit SMS On-Net</label>
            <input type="number" placeholder="Enter free SMS voice on net" name="fusmsonnet" ><br>
            <input type="number" placeholder="Enter External cost" name="extrasmsonnet" ><br>
        </div>


        <div class="input-form d-flex justify-content-between" style="gap:5px">
            <label for="fusmscrossnet">Free Unit SMS Net-Cross</label>
            <input type="number" placeholder="Enter free unit SMS cross net " name="fusmscrossnet" ><br>
            <input type="number" placeholder="Enter External cost" name="extrasmscrossnet" ><br>
        </div>


        <div class="input-form d-flex justify-content-between" style="gap:5px">
            <label for="fusmsinternational ">Free Unit SMS International</label>
            <input type="number" placeholder="Enter free unit SMS International" name="fusmsinter" ><br>
            <input type="number" placeholder="Enter External cost" name="extrasmsinter" ><br>
        </div>



        <div class="input-form d-flex justify-content-between" style="gap:5px">
            <label for="fudata ">Free Unit DATA</label>
            <input type="number" placeholder="Enter free unit DATA" name="fudata" ><br>
            <input type="number" placeholder="Enter External cost" name="extradata" ><br>
        </div>


        <div class="btns">
            <button type="submit" class="btnSumbit">Add</button>   
        </div>

        <p id="error-login"></p>
    </form>  
</div>
<%@ include file="footer.html" %>

<script>
    let form = document.getElementById("addRatePlanForm");

    const submitvalidation = (e) => {
        e.preventDefault();
        // Get all field data from the form
        let data = new FormData(form);
        // Convert to a query string
        queryString = "?" + new URLSearchParams(data).toString();
        var url = "/postbaidSystem/CheckAddRatePlan" + queryString;

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
            var res = request.responseText;
            console.log(res)
            if (request.responseText.split("___")[0] == "true") {
                document.getElementById("error-login").innerHTML = "Done add Rate Plan";
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



