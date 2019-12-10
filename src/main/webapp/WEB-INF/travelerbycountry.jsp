<%-- 
    Document   : travelerbycountry
    Created on : 2019.11.05., 19:25:22
    Author     : Csaba
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>


        <title>List Country</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/testimony.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link href= "https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css"> 
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css">


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

        </style>



    </head>





    <body>
        <jsp:include page="./header.jsp"/>
        <h1 style="color:black">Country Statistic</h1>
        <!--        <h1 style="color:black"><a href="./admin">Back to admin</a></h1>-->
        <div class="btn-group" role="group" aria-label="Basic example">
            <form action="./admin" method="get">
                <button type="submit" class="btn btn-info">Admin Page</button>
            </form>

        </div>

        <div class="container">
            <h2><kbd>List of Country</kbd></h2>
            <table id="example" table class="table table-dark table-striped">
                <thead>
                    <tr>
                        <th>Username:</th>
                        <th>Fullname:</th>
                        <th>Country:</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${country}" var="item">
                        <tr>
                            <td>${item.userName}</td>
                            <td>${item.fullName}</td>
                            <td>${item.country}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="container">
            <h2><kbd>Sum Of Country</kbd></h2>
            <table class="table table-dark table-striped">
                <thead>
                <tbody>

                    <c:forEach items="${countrymap}" var="item">
                        <tr>
                            <td>${item.key} : ${item.value}</td>

                        </tr>
                    </c:forEach>

                    </thead>
                </tbody>
            </table>
        </div>

        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>       
    </body>
</html>