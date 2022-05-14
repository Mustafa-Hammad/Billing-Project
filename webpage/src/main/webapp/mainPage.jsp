<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.html" %>
<div class="main-page">

    <h1><center>Administration Home Page</center></h1>
    <form id="searchBYNationalId">  

        <div class="input-form">
            <label for="nationalID">National ID</label>
            <input type="number" placeholder="Enter National ID" name="cuid" required><br>
        </div>

        <div class="btns">
            <button type="submit" class="btnSumbit">Search</button>   
        </div>

        <p id="error-login"></p>

    </form>  

</div>
<%@ include file="footer.html" %>


<script>
    let form = document.getElementById("searchBYNationalId");

    const submitvalidation = (e) => {
        e.preventDefault();
        // Get all field data from the form
        let data = new FormData(form);
        // Convert to a query string
        queryString = "?" + new URLSearchParams(data).toString();

        var url = "/postbaidSystem/CheckNationalId" + queryString;

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

                var url = "http://localhost:8080/postbaidSystem/detailsUser.jsp?id=" +
                        request.responseText.split("___")[1] + "&name=" + request.responseText.split("___")[2]
                        + "&email=" + request.responseText.split("___")[3];
                window.location.replace(url);

            } else
            {
                document.getElementById("error-login").innerHTML = request.responseText.split("___")[1];
                document.getElementById("error-login").style.color = "red";

            }

        }
    }
    form.addEventListener("submit", submitvalidation);


</script>


<%@ include file="footerBody.html" %>

