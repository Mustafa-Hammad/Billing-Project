<%-- 
    Document   : addOneTimeFeeFormat
    Created on : May 12, 2022, 7:30:06 PM
    Author     : ahmedmedhat
--%>


<%@ include file="header.html" %>



<div class="addUser-form">
    <h1><center>Add One Time Fee</center></h1>  
    <form id="addUserForm" action="addOneTimeFee.jsp">  

        <div id = "selected" class="input-form">
              <label>Service Type:   </label>
                <select name="service">
                    <option value="1">Voice</option>
                    <option value="2">SMS</option>
                    <option value="3">Mobile Data</option>
                </select>
            
        </div>

        <div id = "size" class="input-form">
              <label>Zone :   </label>
                <select name="zone">
                    <option value="1">On Net</option>
                    <option value="2">Cross Net</option>
                    <option value="3">International</option>
                </select>
            
        </div>

        <div class="input-form">
            <label for="name">Quota: </label>
            <input type="text" name = "quota" placeholder="Enter Number" required pattern="[0-9]+"><br>
        </div>
        
        <div class="input-form">
            <label for="name">Cost: </label>
            <input type="text" name = "cost" placeholder="Enter Number" required pattern="[0-9]+"><br>
        </div>

        <div class="btns">
            <button type="submit" class="btnSumbit">Add</button>   
        </div>

        <p id="error-login"></p>
    </form>  
</div>

<script>
    $(function() {
    $("#selected").change(function() {
    if(( $('option:selected', this).text() =='Mobile Data' )){
          $("#size").html("<option value="1">On Net</option>");
        }else{
            $("#size").html("<option value="1">On Net</option><option value="2">Cross Net</option><option value="3">International</option>");
        }
    });
});
</script>
<%@ include file="footer.html" %>
<%@ include file="footerBody.html" %>

