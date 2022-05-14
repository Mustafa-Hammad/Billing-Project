<%-- 
    Document   : addUser
    Created on : Apr 25, 2022, 5:16:49 PM
    Author     : ahmedmedhat
--%>
<%@ include file="header.html" %>

<div class="addUser-form">
    <h1><center>Add New User</center></h1>  
    <form id="addUserForm">  

        <div class="input-form">
            <label for="nationalID">National ID</label>
            <input type="text" placeholder="Enter National ID" name="cuid" required><br>
        </div>

        <div class="input-form">
            <label for="name">User Name</label>
            <input type="text" placeholder="Enter Username" name="name" required><br>
        </div>

        <div class="input-form">
            <label for="name">Email</label>
            <input type="email" name="email" placeholder="Enter Email" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-z]{2,}$"><br>
        </div>

        <div class="input-form">
            <label for="password">Password</label>
            <input type="password" placeholder="Enter Password" name="pass" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" required>  
        </div>

        <div class="btns">
            <button type="submit" class="btnSumbit">Add new user</button>   
        </div>

        <p id="error-login"></p>
    </form>  
</div>
<%@ include file="footer.html" %>

<script>
    let form = document.getElementById("addUserForm");

    const submitvalidation = (e) => {
        e.preventDefault();
        // Get all field data from the form
        let data = new FormData(form);
        // Convert to a query string
        queryString = "?" + new URLSearchParams(data).toString();

        var url = "/postbaidSystem/CheckAddUser" + queryString;

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
                document.getElementById("error-login").innerHTML = "Done add user";
                document.getElementById("error-login").style.color=  "green";
            } else {
                document.getElementById("error-login").innerHTML = request.responseText.split("___")[1];
                                document.getElementById("error-login").style.color=  "red";

            }
        }
    }
    form.addEventListener("submit", submitvalidation);


</script>

<%@ include file="footerBody.html" %>



