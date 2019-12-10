<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>


<html>

    <head>

        <title>Hello Tourist!</title>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="./assets/css/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/testimony.css">

        <meta name="viewport" content="width=device-width, initial-scale=1"> 

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

        <link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.css'>
        <link rel="stylesheet" href="./assets/css/style.css">

        <title>Hello Tourist!</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


        <style>

            body {

                background:url("background2FHD.jpg") no-repeat center center fixed; 
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

            }


        </style>


    </head>

    <body>
        <jsp:include page="./header.jsp"/>


        <div class="container">


            <h2 style="color: white;"><a href="./createbooking">Create booking for testing purposes :-)</a></h2>

            <c:if test="${not empty sessionScope.bookingSuccess}">

                <div class="col-sm">
                    <div class="card text-white bg-success mb-3" style="max-width: 30rem;  margin: 15px;">
                        <div class="card-header">success</div>
                        <div class="card-body">
                            <h4 class="card-title">Successful Booking</h4>
                            <p class="card-text"><br>Well Done!<br>${username}</p>
                        </div>
                    </div>
                    <!--</a>-->
                </div>            

                <c:remove var="bookingSuccess"></c:remove>
            </c:if>

            <c:if test="${not empty sessionScope.registrationMsg}">

                <div class="col-sm">
                    <div class="card text-white bg-warning mb-3" style="max-width: 30rem;  margin: 15px;">
                        <div class="card-header">registration info</div>
                        <div class="card-body">
                            <h4 class="card-title"> </h4>
                            <p class="card-text"><br>${registrationMsg}<br></p>
                        </div>
                    </div>
                    <!--</a>-->
                </div>            

                <c:remove var="registrationMsg"></c:remove>
            </c:if>

            <c:if test="${not empty sessionScope.logoutsuccess}">

                <div class="col-sm">
                    <div class="card text-white bg-warning mb-3" style="max-width: 30rem;  margin: 15px;">
                        <div class="card-header">success</div>
                        <div class="card-body">
                            <h4 class="card-title">You have successfully logged out</h4>
                        </div>
                    </div>
                    <!--</a>-->
                </div>            

                <c:remove var="logoutsuccess"></c:remove>
            </c:if>





            <div id="option_picker">
                <div class="row">

                    <div class="col-sm">
                        <div  id="test" class="card text-white bg-info mb-3" style="max-width: 30rem;  margin: 15px;">
                            <!--<a href="">-->
                            <div class="card-header"> </div>
                            <div class="card-body">
                                <h4 class="card-title">Search by Region</h4>
                                <p class="card-text"><br><br></p>
                            </div>
                        </div>
                        <!--</a>-->
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
                        <div id="advanced_search" class="card text-white bg-primary mb-3" style="max-width: 30rem; margin: 15px;">
                            <div class="card-header"></div>
                            <div class="card-body">
                                <h4 class="card-title">Advanced Search</h4>
                                <p class="card-text"> <br><br></p>
                            </div>
                        </div>

                    </div>
                </div>
            </div>



            <div id="region" style="display:none;">

                <div class="card  text-white  bg-info mb-3" style="max-width: 80rem;">
                    <div class="card-header">Picker</div>
                    <div class="card-body">
                        <h4 class="card-title">Please choose a Region</h4>
                    </div>
                </div>



                <div class="row">
                    <c:forEach items="${regionList}" var="item" varStatus="status">            
                        <div class="col-sm">
                            <div class="card text-white bg-success mb-3" style="max-width: 30rem; ">
                                <a href="./eventsbyregion?q=${item}">
                                    <div class="card-header"></div>
                                    <div class="card-body">
                                        <h4 class="card-title">${item}</h4>
                                        <p class="card-text"></p>
                                        <div class="imago">
                                            <div class="shadow-sm p-3 mb-5 bg-white rounded">
                                                <img src=  "${regionImage[status.index]}" height="175" width="253" class="responsive" ></div>
                                        </div></div>
                            </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
                <a href="./test">
                    <div class="card text-white bg-primary mb-3" >
                        <div class="card-header"></div>
                        <div class="card-body">
                            <h4 class="card-title">Back to options</h4>
                            <p class="card-text">----------</p>
                        </div>
                    </div>
                </a>
            </div>

            <br><br>




            <!-- Currency Rates Script - EXCHANGERATEWIDGET.COM -->
            <div id="widgetmain" style="display:none; position: absolute; left: 80px;top:80px; width:240px; box-shadow:2px 2px 4px #6B2921;border:3px solid #6B2921;border-radius:5px; "><p style="background-color:#6B2921;margin:0px; height:24px;text-align:center;color:#FFFFFF; font-weight:bold;font-size:16px;padding-top:2px;"><a href="https://www.exchangeratewidget.com/" style="color:#FFFFFF;text-decoration:none;" rel="nofollow">Live Currency Rates</a></p><script type="text/javascript" src="//www.exchangeratewidget.com/converter.php?l=en&f=USD&t=USDHUF,EURHUF,GBPHUF,JPYHUF,RUBHUF,CZKHUF,PLNHUF,SEKHUF,UAHHUF,HUFUSD,HUFEUR,HUFCHF,HUFRUB,HUFHRK,HUFINR,HUFPLN,HUFRON,HUFSEK,&a=1&d=A56352&n=FFFFFF&o=000000&v=10"></script>
                <button  id="widget_button" type="button" class="btn btn-secondary btn-sm">Close</button>
            </div>
            <!-- End of Currency Rates Script -->






            <img  style="visibility: hidden;" height="430" width="300">
        </div>






        <div class="section section-project">

            <div class="project-carousel" >
                <div class="project-strip">
                    <div class="project"><img src="http://unsplash.it/578/360/?image=26&amp;blur" alt=""/></div>
                    <!--div class="project"><img src="http://unsplash.it/578/360/?image=39&amp;blur" alt=""/></div-->
                    <div class="project"><img src="./assets/img/tihany.jpg" alt=""/></div>
                    <div class="project"><img src="./assets/img/vadlovak.jpg" alt=""/></div>
                    <div class="project"><img src="http://unsplash.it/578/360/?image=65&amp;blur" alt=""/></div>
                    <div class="project"><img src="./assets/img/tourists.jpg" alt=""/></div>
                    <div class="project"><img src="http://unsplash.it/578/360/?image=91&amp;blur" alt=""/></div>
                    <div class="project"><img src="http://unsplash.it/578/360/?image=104&amp;blur" alt=""/></div>

                </div>
                <div class="project-screen">
                    <div class="project-detail">
                        <div class="project"><img src="http://unsplash.it/578/361/?image=26" alt=""/></div>
                        <div class="project"><img src="./assets/img/tihany.jpg" alt=""/></div>
                        <div class="project"><img src="./assets/img/vadlovak.jpg" alt=""/></div>
                        <div class="project"><img src="http://unsplash.it/578/361/?image=65" alt=""/></div>
                        <div class="project"><img src="./assets/img/tourists.jpg" alt=""/></div>
                        <div class="project"><img src="http://unsplash.it/578/361/?image=91" alt=""/></div>
                        <div class="project"><img src="http://unsplash.it/578/361/?image=104" alt=""/></div>

                    </div>
                    <div class="screen-frame"></div>
                </div>
            </div>            
        </div>


        <!--                <br><br>
                        <img   height="100" width="100" alt="PICPICPIPCI">                -->
        <jsp:include page="./testimonials.jsp"/>

        <h1 style="background-color: wheat;"></h1>







        <!--script src='https://code.jquery.com/jquery-2.2.4.min.js'></script-->
        <script src='./assets/js/slick.min.js'></script>
        <script src="./assets/js/index.js"></script>

        <script>
            var div = document.getElementById("test");

            div.addEventListener('click', function (event) {
                document.getElementById("option_picker").style.display = "none";
                document.getElementById("region").style.display = "block";
            });
            var listbooks = document.getElementById("listbooks");

            listbooks.addEventListener('click', function (event) {
                window.location.replace('./listbooks');
            });
            var advanced = document.getElementById("advanced_search");

            advanced.addEventListener('click', function (event) {
                window.location.replace('./advancedsearch');
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

