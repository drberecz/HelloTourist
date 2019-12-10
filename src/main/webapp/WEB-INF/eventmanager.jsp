<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>


<html>

    <head>

        <title>Manage Event</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/testimony.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <style>

            body {



                /*background-image: linear-gradient(to bottom right, #886644, #446688);*/

                background:url("background3.jpg") no-repeat center center fixed; 
                -webkit-background-size: cover;
                -moz-background-size: cover;
                -o-background-size: cover;
                background-size: cover;
            }
            a {color: black;}
            a:hover {color: mediumspringgreen;}


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

        <button class="btn btn-secondary btn-lg" onclick="window.scrollTo(0, document.body.scrollHeight);"
                style="position: relative; left: 30px;">
            SCROLL TO BOTTOM</button>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">


                    <div class="card text-dark bg-info mb-4" style="max-width: 45rem;">
                        <div class="card-header"></div>
                        <div class="card-body">
                            <h4 class="card-title " style="font-size:2em;"><c:out value="${sessionScope.error}"/> </h4>
                            <p class="card-text"><br>


                            <form action="./eventmanager" method="post">

                                <div class="form-group row">

                                    <label for="description" class="col-sm-4 col-form-label"><b>GeoLocation </b></label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="NO value set" name="geolocString" required class="form-control" id="inputGeoloc" value="" readonly="true">
                                    </div>
                                </div>
                                <div class="form-group row">

                                    <label for="description" class="col-sm-4 col-form-label"><b>Description: </b></label>
                                    <div class="col-sm-6">
                                        <input type="text" placeholder="Description" name="description" required class="form-control" id="description" value="Hello Tourist, on the right side...">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="max" class="col-sm-4 col-form-label"><b>Max. participants: </b></label>
                                    <div class="col-sm-6">
                                        <input type="number" placeholder="Max. participants" value="1" name="max" required required class="form-control" id="max"></div>
                                </div>
                                <div class="form-group row">
                                    <label for="cost" class="col-sm-4 col-form-label"><b>Cost: </b></label>
                                    <div class="col-sm-6">
                                        <input type="number" placeholder="$" name="cost" value="1000" required class="form-control" id="cost"></div>
                                </div>
                                <div class="form-group row">
                                    <label for="pattern" class="col-sm-4 col-form-label"><b>Shift: </b></label>
                                    <div class="col-sm-6">
                                        <select name="pattern" class="form-control" id="pattern">
                                            <option value="3">WEEKDAYS_ONLY</option>
                                            <option value="4">WEEKENDS_ONLY</option>
                                            <option value="2">VACANT_EXPECT_MONDAYS</option>
                                            <option value="1">ALWAYS_VACANT</option>
                                        </select>  </div></div>
                                <div class="form-group row">
                                    <label for="category" class="col-sm-4 col-form-label"><b>Category: </b></label>
                                    <div class="col-sm-6">
                                        <select name="category" class="form-control" id="category">
                                            <c:forEach var="item" items="${allCategory}">
                                                <option value="${item.name}" >${item.name}</option>

                                            </c:forEach>
                                        </select></div></div>
                                <div class="form-group row">
                                    <label for="region" class="col-sm-4 col-form-label"><b>Region: </b></label>
                                    <div class="col-sm-6">
                                        <select name="regions" class="form-control" id="regions">
                                            <c:forEach var="entry" items="${region}">
                                                <option value="${entry}">${entry}</option>

                                            </c:forEach>
                                        </select></div></div>
                                <div class="form-group row">    
                                    <div class="col-sm-6 center-block" >
                                        <button type="submit" class="btn btn-primary center-block" >Create event</button>
                                    </div>
                                </div>
                        </div>
                        </form>



                        <br></p>


                    </div>
                </div>



                <div class="col-md-8">
                    <iframe class="framedmap" src="map?latitude=${requestScope.geolat}&longitude=${requestScope.geolon}&markertext=Proposed_Starting_Point&markersubtext=Click on map to Modify" height="530" width="800"></iframe>                

                </div>
            </div>
        </div>        



        <br><br>



        <c:forEach var="item" items="${allEventByGuide}">


            <div class="card text-white bg-info mb-3 " style="max-width: 60rem;">
                <div class="card-header"> </div>
                <div class="card-body">
                    <h4 class="card-title">Guide name: ${item.guideUserName}  </h4>
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





    </body>

</html>









