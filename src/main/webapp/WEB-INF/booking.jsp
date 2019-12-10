<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>


<html>

    <head>

        <title>Booking</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/testimony.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link href="./assets/dist/vanillaCalendar.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <style>

            body {

                background-image: linear-gradient(to bottom right, #886644, #446688);
            }
            a {color: #fff;}
            a:hover {color: #cfc;}


            .card{
                margin: 25px;
                box-shadow: 8px 10px 5px #3A1800;
            }
            .framedmap {
                border: 12px solid rgba(255, 215, 0, .5);
                -webkit-background-clip: padding-box; /* for Safari */
                background-clip: padding-box; /* for IE9+, Firefox 4+, Opera, Chrome */
            }

        </style>


    </head>

    <body>
        <jsp:include page="./header.jsp"/>

        <div class="card text-white bg-info mb-3" style="max-width: 35rem;">
            <div class="card-header"></div>
            <div class="card-body">
                <h4 class="card-title " style="font-size:2em;">Booking in Progress....</h4>
                <p class="card-text"><br></p>
            </div>
        </div>



        <div class="container-fluid">
            <div class="row">
                <div class="col-md-5">          




                    <c:forEach items="${eventList}" var="item">
                        <div class="card text-white bg-secondary mb-3 " style="min-width: 40rem;">
                            <div class="card-header"> </div>
                            <div class="card-body">
                                <h4 class="card-title">Guide name: ${item.guideUserName}  <a href=""> SEE PROFILE</a> </h4>
                                <table class="card-text">

                                    <tr>
                                        <th>__________________________</th>
                                        <th>__________________________</th>

                                    </tr>
                                    <tr>
                                        <td>Category</td>
                                        <td>${item.getCategory().getName().toString()}</td>
                                    </tr>
                                    <tr>
                                        <td>Cost</td>
                                        <td>${item.cost} HUF</td>
                                    </tr>
                                    <td>Maximum Participants</td>
                                    <td>${item.maxParticipants} person(s)</td>
                                    </tr>
                                    <td>Weekly Schedule</td>
                                    <td>${item.dayOfWeek}</td>
                                    </tr>
                                    <tr>
                                        <td>Description</td>
                                        <td>${item.description}</td>
                                    </tr>

                                </table>

                            </div>
                        </div>
                    </c:forEach>








                    <div id="datePicked" class="container" style="display:none; color:white;">
                        <div class="row">
                            <div class="col-md-4">
                                <form  id="form-id" action="./booking" method="post" >
                                    <input type="hidden"  name="eventId" value="${eventId}">    
                                    <h3 id="datePickedText" ></h3>
                                    <input type="hidden"  name="datePickedVal" value="">  
                                    <h3 id="numOfPersonsSelected" ></h3>
                                    <input type="hidden"  name="numOfPersonsSel" value="1">  
                                    <button type="button" onclick="goBack()" id="cancel" style="font-size: 2rem"  class="btn btn-warning">CANCEL<br>BOOKING</button>
                                    <button type="button" id="confirm" style="font-size: 2rem" class="btn btn-success">CONFIRM<br>BOOKING</button>
                                </form>
                            </div>
                        </div>
                    </div> 




                    <div id="customisedCalendar" class="container">
                        <div class="row">
                            <div class="col-md-4">



                                <div class="row">
                                    <div class="col-md-9">
                                        <p style="font-size: 18px; color: wheat;">Please Pick a Date&nbsp;&nbsp;AND&nbsp;&nbsp;Select size of group&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                    </div>
                                    <div class="col-md-3">
                                        <select style="font-size:2rem" id="ddlViewBy">
                                            <option value="1">one person</option>
                                            <c:forEach items="${participants}" var="item">
                                                <option value="${item}">${item} persons</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>




                                <button type="button" id="goAhead" class="btn btn-success" style="display:none;">- - ---> Go ahead with Booking</button>
                                <button type="button" id="notAvail" class="btn btn-danger" style="display:none;">Sorry, Selected date is not available</button>
                                <div id="v-cal" >
                                    <div class="vcal-header">
                                        <button class="vcal-btn" data-calendar-toggle="previous">
                                            <svg height="24" version="1.1" viewbox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
                                            <path d="M20,11V13H8L13.5,18.5L12.08,19.92L4.16,12L12.08,4.08L13.5,5.5L8,11H20Z"></path>
                                            </svg>
                                        </button>

                                        <div class="vcal-header__label" data-calendar-label="month">
                                            March 2017
                                        </div>


                                        <button class="vcal-btn" data-calendar-toggle="next">
                                            <svg height="24" version="1.1" viewbox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg">
                                            <path d="M4,11V13H16L10.5,18.5L11.92,19.92L19.84,12L11.92,4.08L10.5,5.5L16,11H4Z"></path>
                                            </svg>
                                        </button>
                                    </div>
                                    <div class="vcal-week">
                                        <span style="${requestScope.daymod[0]}">Mon</span>
                                        <span style="${requestScope.daymod[1]}">Tue</span>
                                        <span style="${requestScope.daymod[2]}">Wed</span>
                                        <span style="${requestScope.daymod[3]}">Thu</span>
                                        <span style="${requestScope.daymod[4]}">Fri</span>
                                        <span style="${requestScope.daymod[5]}">Sat</span>
                                        <span style="${requestScope.daymod[6]}">Sun</span>
                                    </div>
                                    <div class="vcal-body" data-calendar-area="month"></div>
                                </div>

                                <p class="demo-picked">
                                    Date picked:
                                    <span id="landing_field" data-calendar-label="picked" style="color:grey"></span>
                                </p>


                            </div>

                        </div>
                    </div><!--container calendar-->
                </div><!--col-md4-->
                <div class="col-md-7">
                    <iframe  class="framedmap" src="map?latitude=${requestScope.geolat}&longitude=${requestScope.geolon}&markertext=Tour_Starts_Here&markersubtext=${requestScope.eventList.get(0).guideUserName}" height="610" width="800"></iframe>                

                </div>
            </div><!--row-->


        </div><!--container-fuild -->

        <span id="reservedDates" style="display:none" >${requestScope.reservedDates}</span>
        <span id="daysOff"  style="display:none" >${requestScope.daysOff}</span>



        <script src="./assets/dist/vanillaCalendar.js" type="text/javascript"></script>
        <script src="./assets/js/calendarCustomized.js" type="text/javascript"></script>
        <script>
                                        window.addEventListener('load', function () {
                                            vanillaCalendar.init({
                                                disablePastDays: true
                                            });
                                        });
        </script>







        <br><br>


        <jsp:include page="./testimonials.jsp"/>

    </body>

</html>








