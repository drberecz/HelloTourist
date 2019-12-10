<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

    <head>
        <title>Hello Tourist!</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/testimony.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link href="<c:url value='/assets/css/loginstyle.css' /> rel='stylesheet'">


        <style>

            a {color: #fff;}
            a:hover {color: #cfc;}
            body{

                background-image:url('assets/img/tokajwp.jpeg');
                background-size: cover;
                background-repeat: no-repeat;
                background-position: center center;
                height: 100%;
                font-family: 'Numans', sans-serif;
            }


        </style>


    </head>
    <body>

        <jsp:include page="./header.jsp"/>    

        <div class="container">


            <div class="d-flex justify-content-center h-100">
                <div class="card">
                    <div class="card-header">
                        <h3>Register as Guide</h3>

                    </div>
                    <div class="card-body">
                        <form action="./regasguide" method="post">
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" class="form-control" placeholder="Full Name" name="fullname" required>

                            </div>
                            <div class="input-group form-group">
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-user"></i></span>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Username" name="username" required>

                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-star"></i></span>
                                    </div>
                                    <input type="text" class="form-control" placeholder="email address" name="email" required>
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-key"></i></span>
                                    </div>
                                    <input type="password" class="form-control" placeholder="password" name="password" required="">
                                </div>
                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-star"></i></span>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Telephone number" name="phone" required>
                                </div>

                                <div class="input-group form-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text"><i class="fas fa-star"></i></span>
                                    </div>
                                    <input type="text" class="form-control" placeholder="Region" name="region" required>
                                </div>

                                <input id="avalink" type="hidden" value="./assets/avatar.png" name="imageLink">
                                <div class="form-group">
                                    <button type="submit">REGISTER</button>
                                </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex justify-content-center links">
                            Already have an account?<a href="./login">Log in As Guide<br> </a>
                        </div>

                    </div>
                </div>
            </div>
        </div>


        <div class="d-flex justify-content-center h-100"> 
            <div>
                <h4>Change your Avatar</h4>

                <img id="avatar" src='./assets/avatar.png' alt="RSS" width="200" height="200">
                <button  id="eve"  pe="button" class="btn btn-outline-secondary btn-lg">Click Me</button>
            </div>
        </div>
        <script>
            document.getElementById("eve").addEventListener('click', function (event) {
                var element = $('#avatar');
                element.removeAttr('src');
                let random = Math.floor(Math.random() * 100000 + 1);
                let url = 'https://avatar.lisk.ws/' + random + '.png';
                document.getElementById("avalink").value = url;
                $(element).attr('src', url);
            });


        </script>







    </body>
</html>
