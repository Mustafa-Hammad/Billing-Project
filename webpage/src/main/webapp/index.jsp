<%-- 
    Document   : index
    Created on : Apr 30, 2022, 1:45:26 AM
    Author     : ahmedmedhat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome Page</title>
                <link rel="icon" href="https://logosvector.net/wp-content/uploads/2013/06/vodafone-plc-vector-logo-200x200.png">

    </head>
        <style>
            body, html {
                height: 100%;
                margin: 0;
            }

            .bgimg {
                background-image: url('backgroundImage.jpg');
                height: 100%;
                background-position: center;
                background-size: cover;
                position: relative;
                color: white;
                font-family: "Courier New", Courier, monospace;
                font-size: 25px;
            }

            .topleft {
                position: absolute;
                top: 0;
                left: 16px;
            }

            .bottomleft {
                position: absolute;
                bottom: 0;
                left: 16px;
            }

            .middle {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                text-align: center;
            }

            hr {
                margin: auto;
                width: 40%;
            }
        </style>
        <body>

            
            <div class="bgimg">
                <div class="topleft">
                   <!--<img src="logo.png">-->
                </div>
                <div class="middle">
                    <a href="http://localhost:8080/postbaidSystem/mainPage.jsp"><img src="logo.png"><a>
                    <hr>
                    <p id="demo" style="font-size:30px"></p>
                </div>
                <div class="bottomleft">
                    <p>welcome to our website, please feel free to monitor your usage, change your rate plan, services, show your invoice,...etc</p>
                </div>
            </div>

            <script>
            // Set the date we're counting down to
                var countDownDate = new Date("Jan 5, 2022 15:37:25").getTime();

            // Update the count down every 1 second
                var countdownfunction = setInterval(function () {

                    // Get todays date and time
                    var now = new Date().getTime();

                    // Find the distance between now an the count down date
                    var distance = countDownDate - now;

                    // Time calculations for days, hours, minutes and seconds
                    var days = Math.floor(distance / (1000 * 60 * 60 * 24));
                    var hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                    var minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
                    var seconds = Math.floor((distance % (1000 * 60)) / 1000);

                    // Output the result in an element with id="demo"
                    document.getElementById("demo").innerHTML = days + "d " + hours + "h "
                            + minutes + "m " + seconds + "s ";

                    // If the count down is over, write some text 
                    if (distance < 0) {
                        clearInterval(countdownfunction);
                        document.getElementById("demo").innerHTML = "Welcome";
                    }
                }, 1000);
            </script>

        </body>
    </html>
</body>
</html>
