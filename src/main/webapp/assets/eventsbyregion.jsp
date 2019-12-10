<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>


<html>

    <head>

        <title>Events By Region</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/testimony.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
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

        <div class="card text-white bg-success mb-3" style="max-width: 35rem;">
            <div class="card-header"></div>
            <div class="card-body">
                <h4 class="card-title " style="font-size:2em;"><c:out value="${sessionScope.error}"/> </h4>
                <p class="card-text"><br><br></p>
            </div>
        </div>




        <div class="row">

            <c:forEach items="${eventList}" var="item">
                <div class="card text-white bg-info" style="max-width: 60rem;">
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
        </div>


        <br><br>


        <jsp:include page="./testimonials.jsp"/>

    </body>

</html>








