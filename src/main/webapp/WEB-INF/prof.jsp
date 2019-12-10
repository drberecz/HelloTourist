<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">

</head>
<body>

<head>

    <title>Guide Profile</title>
    <link rel="stylesheet" href="./assets/css/bootstrap.css">
    <link rel="stylesheet" href="./assets/css/testimony.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

    <link href="./assets/css/lightbox.css" rel="stylesheet">



    <style>

        body {

            background-image: linear-gradient(to bottom right, #886644, #446688);
        }
        a {color: #fff;}
        a:hover {color: #cfc;}

        .toast {
            opacity: 0.8 !important;
            margin: 25px;

            color:  #000099;


            box-shadow: 8px 10px 5px #3A1800;
        }
        .card{
            margin: 25px;
            box-shadow: 8px 10px 5px #3A1800;
        }
        .checked {
            color: black;
        }
        .glyphicon{color: black}
        h3 {
            color: white;
            text-shadow: 2px 2px 4px #000000;
        }

    </style>

</head>

<body>
    <jsp:include page="./header.jsp"/>

    <div class="container-fluid">
        <div class="row">
            <div class="col-md-4">

                <div class="toast" data-autohide="false">
                    <div class="toast-header">

                        <strong class="mr-auto"><h2><span class=" glyphicon glyphicon-folder-open fa-5x"> <kbd>   ${selectedGuide.userName}</kbd></span></h2></strong>
                        <a href="${selectedGuide.imageLink}" data-lightbox="image-1" data-title=${selectedGuide.userName}>
                            <img src="${selectedGuide.imageLink}" height="50" width="50"/></a>    
                        <small class="text-muted"></small>

                        <button type="button" class="ml-2 mb-1 close" data-dismiss="toast"></button>

                    </div>
                    <div class="toast-body">


                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th><h5>Name:</h5></th><th><h5>${selectedGuide.userName}</h5></th>  
                                </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <td><h5>Region:</h5></th><th><h5>${selectedGuide.operatesInRegion}</h5></td>
                                </tr>
                                <tr>
                                    <td><h5>Reviews:</h5></th><th><h5>${selectedGuide.numOfReviews}</h5></td>
                                </tr>
                                <tr>
                                    <td><h5>Score:</h5></th><th><h5>${selectedGuide.scoreSum}</h5></td>
                                </tr>
                                <tr>
                                    <td><h5>Average score:</h5></th><th><h5>${selectedGuide.averageScore}</h5></td>
                                </tr>
                                <tr>
                                    <td><h5><span class="glyphicon glyphicon-earphone"></h5></th><th><h5>${selectedGuide.phone}</h5></td>
                                </tr>
                                <tr>
                                    <td><h5><span class="glyphicon glyphicon-envelope"></h5></th><th><h5>${selectedGuide.email}</h5></td>
                                </tr

                            </tbody>
                        </table>

                    </div>
                </div>

            </div>

            <br><br>

            <div class="col-md-8">

                <c:if  test="${not empty requestScope.evalsuccess}">

                    <div class="col-sm">
                        <div class="card text-white bg-success mb-3" style="max-width: 30rem;  margin: 15px;">
                            <div class="card-header">info</div>
                            <div class="card-body">
                                <h4 class="card-title">Evaluation successfully updated!</h4>
                                <p class="card-text"><br>Guide name:<br>${guidenameFromEval}</p>
                            </div>
                        </div>

                    </div>            
                    <c:remove var="guidenameFromEval"></c:remove>
                </c:if>

                <c:if test="${not empty requestScope.evalLog}">
                    <h4  class="text-white" style="max-width: 40rem;">Recent reviews</h4>      
                    <textarea rows="16" cols="50" style="font-size: 16px;">
                        ${evalLog}
                    </textarea>


                </c:if>


            </div>


        </div>
    </div>




    <jsp:include page="./testimonials.jsp"/>

    <script>
        $(document).ready(function () {
            $('.toast').toast('show');

        });
    </script>
    <script src="./assets/js/lightbox.js"></script>
</body>
</html>








