<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<head>
    <style>
        .card { background-color: rgba(245, 245, 245, 0.4); }
        .card-header, .card-footer { opacity: 1}

        .navbar-brand {
            color: white;
            text-shadow: 1px 1px 2px black, 0 0 25px violet, 0 0 5px darkviolet;
        }
    </style>
</head>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" style="font-size:64px;"  href="./test">Hello Tourist!</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>


    <c:choose>
        <c:when test="${not empty sessionScope.anonymousRole}">
            <a class="navbar-brand" style="font-size:2rem; background-color:#448;"  href="#">      
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </a>
        </c:when>
        <c:otherwise>
            <img src="${sessionScope.imageLink}" alt="AVATAR" height="50" width="50">
            <a class="navbar-brand" style="font-size:1.5rem; background-color:#448;"  href="#">      
                <span style="font-size:1rem;">User</span>&nbsp;${sessionScope.username}&nbsp;&nbsp;<br>
                <span style="font-size:1rem;">Role</span>&nbsp;${sessionScope.roleOfUser}&nbsp;&nbsp;<br>
                &nbsp;
            </a>          
        </c:otherwise>


    </c:choose>



    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">


            <c:choose>

                <c:when test = "${not empty sessionScope.anonymousRole}">      
                    <li class="nav-item" style="color:white; background-color:#448;">
                        <a class="nav-link" href="./login">&nbsp;Login&nbsp;<br>&nbsp;Register&nbsp;</a>
                    </li>
                </c:when>

                <c:otherwise>
                    <li class="nav-item" style="color:white; background-color:#448;">
                        <a class="nav-link" href="./logout">&nbsp;Logout&nbsp;<br>&nbsp;______&nbsp;</a>
                    </li>                
                </c:otherwise>


            </c:choose>

            <li class="nav-item active">
                <a class="nav-link" href="#">&nbsp;&nbsp;Home&nbsp;&nbsp;<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item" style="color:white; background-color:#844;">
                <a class="nav-link" href="./regasguide">Tourguide<br>Area</a>
            </li>
        </ul>

    </div>
</nav>




