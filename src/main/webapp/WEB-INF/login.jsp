
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    session.setAttribute("anonymousRole", "true");
    
%>












<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>


        <style>

            body{

                background-image:url('assets/img/sunset.jpg'),linear-gradient( rgba(0, 0, 0) 100%, rgba(0, 0, 0)100%);

                background-size: contain;
                background-repeat: no-repeat;
                background-position: center center;
                height: 100%;
                font-family: 'Numans', sans-serif;
            }


        </style>


        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="<c:url value="/assets/css/loginstyle.css" />" rel="stylesheet">
    </head>
    <body>
        <div class="container">
            <div class="d-flex justify-content-center h-100">
                <div class="card">
                    <div class="card-header">
                        <h3>Sign In</h3>
                        <!--				<div class="d-flex justify-content-end social_icon">
                                                                <span><i class="fab fa-facebook-square" ></i></span>
                                                                <span><i class="fab fa-google-plus-square"></i></span>
                                                                <span><i class="fab fa-twitter-square"></i></span>
                                                        </div>-->
                    </div>
                    <div class="card-body">
                        <form action="j_security_check" method="post">
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" class="form-control" placeholder="username" name="j_username" required="">

                            </div>
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" class="form-control" placeholder="password" name="j_password" required="">
                            </div>
                            <div class="row align-items-center remember">
                                <input type="checkbox">Remember Me
                            </div>
                            <div class="form-group">
                                <input type="submit"  class="btn float-right login_btn">
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-center links">
                            Don't have an account?<a href="./reg">Register As Traveler</a>
                        </div>
                        <div class="d-flex justify-content-center">
                            <a href="./test">Back to the Homepage</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>