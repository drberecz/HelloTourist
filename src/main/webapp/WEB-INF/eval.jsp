<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

    <head>

        <title>Manage booking</title>
        <link rel="stylesheet" href="./assets/css/bootstrap.css">
        <link rel="stylesheet" href="./assets/css/testimony.css">
        <link rel="stylesheet" href="./assets/css/popupchat.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>



        <style>

            body {

                background:url("background3.jpg") no-repeat center center fixed; 
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
            .rating-header {
                margin-top: -10px;
                margin-bottom: 10px;
            }


        </style>

    </head>

    <body>


        <jsp:include page="./header.jsp"/>
        <div class="card text-white bg-info mb-3" style="max-width: 35rem;">
            <div class="card-header">

                <strong class="mr-auto"><h5><span class=" glyphicon glyphicon-folder-open"> <kbd>   ${selectedGuide.userName}</kbd>
                            <a href="${selectedGuide.imageLink}" data-lightbox="image-1" data-title=${selectedGuide.userName}> </span></h5>  </strong>
                <img src="${selectedGuide.imageLink}" height="50" width="50"/></a>  
            </div>
            <div class="card-body">









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
                            <td><h5>Average:</h5></th><th><h5>${selectedGuide.averageScore}</h5></td>
                        </tr>
                        <tr>
                            <td><h5><span class="glyphicon glyphicon-earphone"></h5></th><th><h5>${selectedGuide.phone}</h5></td>
                        </tr>
                        <tr>
                            <td><h5><span class="glyphicon glyphicon-envelope"></h5></th><th><h5>${selectedGuide.email}</h5></td>
                        </tr
                        <tr>

                    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">


                    <div class="form-group" id="rating-ability-wrapper">
                        <label class="control-label" for="rating">
                            <span class="field-label-header">How would you rate your guide?</span><br>
                            <span class="field-label-info"></span>
                            <input type="hidden" id="selected_rating" name="selected_rating" value="" required="required">
                        </label>
                        <h2 class="bold rating-header" style="">
                            <span class="selected-rating">0</span><small> / 5</small>
                        </h2>
                        <button type="button" class="btnrating btn btn-default btn-lg" data-attr="1" id="rating-star-1">
                            <i class="fa fa-star" aria-hidden="true"></i>
                        </button>
                        <button type="button" class="btnrating btn btn-default btn-lg" data-attr="2" id="rating-star-2">
                            <i class="fa fa-star" aria-hidden="true"></i>
                        </button>
                        <button type="button" class="btnrating btn btn-default btn-lg" data-attr="3" id="rating-star-3">
                            <i class="fa fa-star" aria-hidden="true"></i>
                        </button>
                        <button type="button" class="btnrating btn btn-default btn-lg" data-attr="4" id="rating-star-4">
                            <i class="fa fa-star" aria-hidden="true"></i>
                        </button>
                        <button type="button" class="btnrating btn btn-default btn-lg" data-attr="5" id="rating-star-5">
                            <i class="fa fa-star" aria-hidden="true"></i>
                        </button>


                    </div>

                    <c:if test="empty bookingId">
                        <h1>BOOKING ID NULL!!!</h1>
                    </c:if>



                    <form action ="./eval" method="POST">

                        <input type="hidden" name="myField" id="myField"  />
                        <input type="hidden" name="guide" value="${selectedGuide.userName}"/>
                        <input type="hidden" name="bookingId" value="${requestScope.bookingId}"/>

                        <button type="submit"   class="btn btn-primary" value="Submit">Submit</button>
                    </form>


                    </tr

                    </tbody>
                </table>
                <p class="card-text"><br></p>
            </div>
        </div>













        <script>		jQuery(document).ready(function ($) {

                $(".btnrating").on('click', (function (e) {

                    var previous_value = $("#selected_rating").val();

                    var selected_value = $(this).attr("data-attr");
                    $("#selected_rating").val(selected_value);

                    $(".selected-rating").empty();
                    $(".selected-rating").html(selected_value);

                    for (i = 1; i <= selected_value; ++i) {
                        $("#rating-star-" + i).toggleClass('btn-warning');
                        $("#rating-star-" + i).toggleClass('btn-default');
                    }

                    for (ix = 1; ix <= previous_value; ++ix) {
                        $("#rating-star-" + ix).toggleClass('btn-warning');
                        $("#rating-star-" + ix).toggleClass('btn-default');
                    }
                    document.getElementById("myField").value = selected_value;
                }));


            });

        </script>

        <script src="./assets/js/lightbox.js"></script>
    </body>

</html>








