<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>


<html>

    <head>

        <title>Hello Tourist!</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/testimony.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

        <!--        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.css'>-->
        <!--        <link rel="stylesheet" href="css/style.css">-->

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <style>            body {

                background:url("marha.jpg") no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }


            a {color: #fff;}
            a:hover {color: #cfc;}


            .card{
                margin: 25px;
                box-shadow: 8px 10px 5px #3A1800;
                .responsive {
                    width: 100%;
                    height: auto;
                }
            }
            .imago{ margin: 0px;    
                    padding: 0;
                    box-shadow: 8px 10px 5px #3A1800;
                    .responsive {
                        width: 100%;
                        height: auto;}
                    </style>


            </head>

            <body>
                <jsp:include page="./header.jsp"/>

                <c:if test="${not empty sessionScope.newEventSuccess}">

                    <div class="col-sm">
                    <div class="card text-white bg-success mb-3" style="max-width: 30rem;  margin: 15px;">
                        <div class="card-header">success</div>
                        <div class="card-body">
                            <h4 class="card-title">New event successfully added</h4>
                            <p class="card-text"><br>Well Done!<br>${username}</p>
                        </div>
                    </div>
                    <!--</a>-->
                </div>            

                <c:remove var="newEventSuccess"></c:remove>
            </c:if>

            <div id="option_picker">
                <div class="row">

                    <div class="col-sm">
                        <div  id="viewCalendar" class="card text-white bg-warning mb-3" style="max-width: 30rem;  margin: 15px;">

                            <div class="card-header"> </div>
                            <div class="card-body">
                                <h4 class="card-title">View Profile</h4>
                                <p class="card-text"><br><br></p>
                            </div>
                        </div>

                    </div>

                    <div class="col-sm">	 
                        <div id="listbooks" class="card text-white bg-info mb-3" style="max-width: 30rem; margin: 15px;">
                            <div class="card-header"></div>
                            <div class="card-body">
                                <h4 class="card-title">Manage Bookings</h4>
                                <p class="card-text"> <br><br></p>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm">
                        <div id="eventmanager" class="card text-white bg-info mb-3" style="max-width: 30rem; margin: 15px;">
                            <div class="card-header"></div>
                            <div class="card-body">
                                <h4 class="card-title">Manage Events</h4>
                                <p class="card-text"> <br><br></p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>



            <br><br>



            <!-- Currency Rates Script - EXCHANGERATEWIDGET.COM -->
            <div id="widgetmain" style="display:none; position: absolute; left: 80px;top:320px; width:240px; box-shadow:2px 2px 4px #6B2921;border:3px solid #6B2921;border-radius:5px; "><p style="background-color:#6B2921;margin:0px; height:24px;text-align:center;color:#FFFFFF; font-weight:bold;font-size:16px;padding-top:2px;"><a href="https://www.exchangeratewidget.com/" style="color:#FFFFFF;text-decoration:none;" rel="nofollow">Live Currency Rates</a></p><script type="text/javascript" src="//www.exchangeratewidget.com/converter.php?l=en&f=USD&t=USDHUF,EURHUF,GBPHUF,JPYHUF,RUBHUF,CZKHUF,PLNHUF,SEKHUF,UAHHUF,HUFUSD,HUFEUR,HUFCHF,HUFRUB,HUFHRK,HUFINR,HUFPLN,HUFRON,HUFSEK,&a=1&d=A56352&n=FFFFFF&o=000000&v=10"></script>
                <button  id="widget_button" type="button" class="btn btn-secondary btn-sm">Close</button>
            </div>
            <!-- End of Currency Rates Script -->





            <!--script src='https://code.jquery.com/jquery-2.2.4.min.js'></script-->
            <script src='https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.js'></script>
            <script src="js/index.js"></script>

            <script>

                var listbooks = document.getElementById("listbooks");

                listbooks.addEventListener('click', function (event) {
                    window.location.replace('./listbooks');
                });
                var listbooks = document.getElementById("eventmanager");

                listbooks.addEventListener('click', function (event) {
                    window.location.replace('./eventmanager');
                });

                var viewCal = document.getElementById("viewCalendar");

                viewCal.addEventListener('click', function (event) {
                    window.location.replace('./guideprofile?guidename=${requestScope.guideName}');
                });
                var currency_toggle = document.getElementById("currency_toggle");

                currency_toggle.addEventListener('click', function (event) {
                    widgetmain.style.display = "block";
                });
                var widget_button = document.getElementById("widget_button");

                widget_button.addEventListener('click', function (event) {
                    widgetmain.style.display = "none";
                });
            </script>



        </body>

    </html>

