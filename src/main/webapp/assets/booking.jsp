<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>


<html>

    <head>

        <title>Events By Region2</title>
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




        <div class="row">

            <c:forEach items="${eventList}" var="item">
                <div class="card text-white bg-secondary mb-3" style="max-width: 80rem;">
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
                                <td>${item.category}</td>
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
        </div>












        <div class="container">
            <div class="row">
                <div class="col-md-4">


                    <h1>Please Pick a Date</h1>
                    <button type="button" class="btn btn-warning">- - ---> Go ahead with Booking</button>
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
                        <span data-calendar-label="picked"></span>
                    </p>


                </div>

            </div>
        </div><!--container-->


        <script src="./assets/dist/vanillaCalendar.js" type="text/javascript"></script>
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








