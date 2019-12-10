<%-- 
    Document   : advancedsearch
    Created on : Nov 7, 2019, 3:25:36 PM
    Author     : root
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Advanced Search</title>

        <meta charset="UTF-8">
        <link rel="stylesheet" href="./assets/css/bootstrap.css">

        <meta name="viewport" content="width=device-width, initial-scale=1"> 

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>



        <style>

            body {

                background:url("vintagemap.jpg") no-repeat center center fixed; 
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

            .framedmap {
                border: 12px solid rgba(255, 215, 0, .5);
                -webkit-background-clip: padding-box; /* for Safari */
                background-clip: padding-box; /* for IE9+, Firefox 4+, Opera, Chrome */
            }


        </style>







    </head>
    <body>

        <jsp:include page="./assets/header.jsp"/>

        <div class="container-fluid">


            <div class="row">
                <div class="col-md-4">



                    <button class="btn btn-dark center-block"
                            style="padding: 8px; min-width: 30rem;">Please Choose a Category</button>
                    <br>
                    <c:forEach var="entry" items="${requestScope.categoryList}">
                        <form method="get" action="./advancedsearch">

                            <button   style="padding: 8px; min-width: 30rem;"
                                      name="categoryAdvanced" type="submit" class="btn btn-primary center-block" value="${entry.name}" >${entry.name}</button>
                            <br>
                        </form>                            

                    </c:forEach>    



                </div>




                <div class="col-md-8">
                    <c:choose>
                        <c:when test="${empty eventListMessage}">
                            <iframe class="framedmap" src="map?advanced=true" height="610" width="800"></iframe>          
                            </c:when>
                            <c:otherwise>


                            <c:if test="${eventList.size()==0}">

                                <div class="col-sm">
                                    <div class="card text-white bg-info mb-3" style="max-width: 30rem;  margin: 15px;">
                                        <div class="card-header">info</div>
                                        <div class="card-body">
                                            <h4 class="card-title">There are no events uploaded for category: ${eventListMessage}</h4>
                                            <p class="card-text"><br>Please choose another category</p>
                                        </div>
                                    </div>
                                </div>            

                            </c:if>



                            <c:forEach var="item" items="${requestScope.eventList}" >

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
                                                <td>${item.category.toString()}</td>
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



                        </c:otherwise>


                    </c:choose>
                </div>
            </div>
        </div>



    </body>
</html>
