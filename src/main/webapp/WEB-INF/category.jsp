<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- Popper JS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> 
<!DOCTYPE html>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<html>
    <head>
        <style>
            body {  
                /*background-image: url("car2.gif");*/
                background-repeat: no-repeat;
                background-position: top right;
                background-attachment: scroll;
            }

            #guidesticker {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            #guidesticker td, #guidesticker th {
                border: 2px solid #ddd;
                padding: 8px;
            }

            #guidesticker tr:nth-child(even){background-color: #f2f2f2;}

            #guidesticker tr:hover {background-color: #ddd;}

            #guidesticker th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #4CAF50;
                color: white;
            }
        </style>-->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category jsp</title>
    </head>
    <body>
        <!--<a href="GuideServlet?query=sites">View site list</a>-->
        <form action="./category" method="post">
            <label for="upperLimit"><b>Max. value</b></label>
            <input type="text" placeholder="$" name="upperLimit" required>
            <button type="submit">Search</button>
        </form>

        <table id="guidesticker">
            <th>Name</th>
            <th>Id</th>
                <c:forEach items="${categories}" var="item">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
