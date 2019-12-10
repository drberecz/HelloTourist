<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>


<html>

    <head>

        <title>List bookings</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/testimony.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <style>

            body {

                background:url("./background1.jpg") no-repeat center center fixed; 
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
            }

        </style>


    </head>

    <body>

        <jsp:include page="./header.jsp"/>

        <c:if  test="${requestScope.inactive.size()<1 and 
                       requestScope.active.size()<1}">
               <div class="col-sm">
                   <div class="card text-white bg-info mb-3" style="max-width: 30rem;  margin: 15px;">
                       <div class="card-header">Information</div>
                       <div class="card-body">
                           <h4 class="card-title">Your booking list is empty</h4>
                       </div>
                   </div>
               </div>  


        </c:if>

        <c:if  test="${not empty sessionScope.evalError}">
            <div class="col-sm">
                <div class="card text-white bg-danger mb-3" style="max-width: 30rem;  margin: 15px;">
                    <div class="card-header">Error</div>
                    <div class="card-body">
                        <h4 class="card-title">Sorry, could not add evaluation score</h4>
                    </div>
                </div>
            </div>  
            <c:remove var="evalError"></c:remove>    
        </c:if>

        <c:if  test="${not empty sessionScope.result}">
            <div class="col-sm">
                <div class="card text-white bg-info mb-3" style="max-width: 30rem;  margin: 15px;">
                    <div class="card-header">Information</div>
                    <div class="card-body">
                        <h4 class="card-title">${result}</h4>
                    </div>
                </div>
            </div>  

            <c:remove var="result"></c:remove>
        </c:if>


        <c:if test="${not empty requestScope.active}">
            <div class="container">
                <h2><kbd>List of Active Bookings</kbd></h2>
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th>Traveler Name</th>
                            <th>Guide Name</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>Your choice...</th>
                        </tr>
                    </thead>

                    <c:forEach items="${requestScope.active}" var="item">
                        <tbody>
                            <tr>
                                <td>${item.travellerName}</td>
                                <td>${item.guideName}</td>
                                <td>${item.dateOfTour}</td>
                                <td>${item.bookingStatus}</td>
                                <td> <form action="./listbooks" method="post">
                                        <input type="hidden"  name="bookingId" value="${item.id}">
                                        <button type="submit" class="btn btn-info">  Manage  <span class="glyphicon glyphicon-log-in"></span></button>
                                    </form></td>
                            </tr>
                        </tbody>
                    </c:forEach>
                </table> 
            </div>
        </c:if>
        <c:if test="${not empty requestScope.inactive}">
            <div class="container">
                <h2><kbd>List of Past Bookings</kbd></h2>
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th>Traveler Name</th>
                            <th>Guide Name</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>Your choice...</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach items="${requestScope.inactive}" var="item">
                            <tr>
                                <td>${item.travellerName}</td>
                                <td>${item.guideName}</td>
                                <td>${item.dateOfTour}</td>
                                <td>${item.bookingStatus}</td>
                                <td> <form action="./listbooks" method="post">
                                        <input type="hidden"  name="bookingId" value="${item.id}">
                                        <button type="submit" class="btn btn-info">  Manage  <span class="glyphicon glyphicon-log-in"></span></button>
                                    </form></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
            </div>
        </c:if>



        <div class="row">

            <c:forEach items="${eventList}" var="item">
                <div class="card text-white bg-secondary mb-3" style="max-width: 80rem;">
                    <div class="card-header"> </div>
                    <div class="card-body">
                        <h4 class="card-title">Guide name: ${item.guideUserName}  <a href="./guideprofile?guidename=${item.guideUserName}"> SEE PROFILE</a> </h4>
                        <table class="card-text">

                            <tr>
                                <th>__________________________</th>
                                <th>__________________________</th>
                                <th  scope="col" style="font-size: 24px; background-color: whitesmoke;"><a  href="./booking?eventid=${item.id}" style="color: black">BOOK</a></th>
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


        <br><br>


    </body>

</html>



