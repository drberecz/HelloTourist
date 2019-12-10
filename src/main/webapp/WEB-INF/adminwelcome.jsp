<%-- 
    Document   : adminwelcome
    Created on : Oct 31, 2019, 1:04:37 PM
    Author     : root
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/testimony.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

        <style>

            body {

                background:url("./background3.jpg") no-repeat center center fixed; 
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
            .btn-group  {
                background-color: lightgray; /* Green background */
                border: 1px solid green; /* Green border */
                color: white; /* White text */
                padding: 10px 24px; /* Some padding */
                cursor: pointer; /* Pointer/hand icon */
                float: top; /* Float the buttons side by side */
            }
            /* Clear floats (clearfix hack) */
            .btn-group:after {
                content: "";
                clear: both;
                display: table;
            }
            .btn-group button:not(:last-child) {
                border-right: none; /* Prevent double borders */
            }
            /* Add a background color on hover */
            .btn-group button:hover {
                background-color: #3e8e41;
            }


        </style>

    </head>


    <body>

        <jsp:include page="./header.jsp"/>

        <c:if test="${not empty sessionScope.deleteOutcome}">

            <div class="col-sm">
                <div class="card text-white bg-success mb-3" style="max-width: 30rem;  margin: 15px;">
                    <div class="card-header">info</div>
                    <div class="card-body">
                        <h4 class="card-title">${sessionScope.deleteOutcome}</h4>
                        <p class="card-text"><br><br></p>
                    </div>
                </div>
                <!--</a>-->
            </div>            

            <c:remove var="deleteOutcome"></c:remove>
        </c:if>


        <h1 style="color:black">Welcome Admin!</h1>


        <div class="btn-group" style="width:100%; max-width: 50rem;">
            <form action="./test" method="get">
                <button type="submit" class="btn btn-info" style="width:100%">Homepage</button>
            </form>
            <form action="./countrystatistic?q=1" method="get">
                <button  type="submit" class="btn btn-info" style="width:100%">Country Statistic</button>
            </form>
        </div>



        <div class="container">
            <form action="./admin" method="post">
                <label for="name">CategoryName
                    <input id="name" name="category" type="text">
                </label>
                <button type="submit">Create category</button>

            </form>
        </div>

        <c:if test="${not empty requestScope.category}">
            <div class="container">
                <h2><kbd>List of category</kbd></h2>
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th>id</th>
                            <th>name</th>
                            <th>option</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.category}" var="item">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.name}</td>
                                <td>
                                    <form action="./admindeletecategory" method="post">
                                        <input type="hidden"  name="deleteCategory" value="${item.id}">
                                        <button type="submit" class="btn btn-info">  Delete  <span class="glyphicon glyphicon-log-in"></span></button>
                                    </form>
                                </td>
                            </tr>

                        </c:forEach>
                    </tbody>
                </table> 
            </div>

            <br><br>             
        </c:if>



    </body>
</html>
