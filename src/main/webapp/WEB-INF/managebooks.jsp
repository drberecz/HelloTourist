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

        </style>

    </head>

    <body>


        <jsp:include page="./header.jsp"/>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-1">
                </div>
                <div class="col-md-11">

                    <c:if test="${not empty selectedGuide}">
                        <div class="container">
                            <h3><kbd>Selected booking</kbd></h3>
                            <table class="table table-dark table-striped">
                                <thead>
                                    <tr>
                                        <th>Guide Name</th>
                                        <th>Region</th>
                                        <th>Reviews</th>
                                        <th>Total score</th>
                                        <th>Avarage score</th>
                                        <th>Phone number</th>
                                        <th>Email address</th>
                                    </tr>
                                </thead>

                                <tbody>
                                    <tr>
                                        <td>${selectedGuide.userName}</td>
                                        <td>${selectedGuide.operatesInRegion}</td>
                                        <td>${selectedGuide.numOfReviews}</td>
                                        <td>${selectedGuide.scoreSum}</td>
                                        <td>${selectedGuide.averageScore}</td>
                                        <td>${selectedGuide.phone}</td>
                                        <td>${selectedGuide.email}</td>
                                    </tr>
                                </tbody>
                            </table> 
                        </div>
                    </c:if>

                    <div class="container">
                        <textarea id="msgBoard" rows="12" cols="50"  readonly>
                            ${currentBookingDTO.messageBoard.toString()}
                        </textarea>
                    </div>
                    <br>






                    <div class="container">
                        <h3><kbd>Details of current booking</kbd></h3>

                        <c:if test="${not empty currentBookingDTO}">
                            <table class="table table-dark table-striped">
                                <thead>
                                    <tr>
                                        <th>Traveler Name</th>
                                        <th>Guide Name</th>
                                        <th>Date</th>
                                        <th>Status</th>
                                        <th> </th>
                                        <th> </th>
                                    </tr>
                                </thead>


                                <tbody>
                                    <tr>
                                        <td>${currentBookingDTO.travellerName}</td>
                                        <td>${currentBookingDTO.guideName}</td>
                                        <td>${currentBookingDTO.dateOfTour}</td>
                                        <td>${currentBookingDTO.bookingStatus}</td>
                                        <td> 
                                            <c:if test="${not empty confirmButton}">
                                                <form action="./cancel" method="post"> 
                                                    <input type="hidden"  name="operation" value="${currentBookingDTO.id}${(empty currentBookingDTO.id)? 'B' : 'B'}">
                                                    <button type="submit" class="btn btn-success">  Confirm  <span class="glyphicon glyphicon-thumbs-up"></span></button>
                                                </form>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${not empty cancelButton}" >
                                                <form id="ccancel" action="./cancel" method="post">
                                                    <input type="hidden"  name="operation" value="${currentBookingDTO.id}${(empty currentBookingDTO.id)? 'A' : 'A'}">
                                                    <button type="submit" class="btn btn-danger">  Cancel  <span class="glyphicon glyphicon-thumbs-down"></span></button>
                                                </form>
                                            </c:if>
                                        <td>
                                            <c:if test="${not empty completeButton}">
                                                <form action="./cancel" method="post">
                                                    <input type="hidden"  name="operation" value="${currentBookingDTO.id}${(empty currentBookingDTO.id)? 'C' : 'C'}">
                                                    <button type="submit" class="btn btn-primary">  Complete  <span class="glyphicon glyphicon-ok-circle"></span></button>
                                                </form>
                                            </c:if>
                                        </td>
                                        <td>
                                            <c:if test="${not empty evaluateButton}">
                                                <form action="./cancel" method="post">
                                                    <input type="hidden"  name="operation" value="${currentBookingDTO.id}${(empty currentBookingDTO.id)? 'D' : 'D'}">
                                                    <button type="submit" class="btn btn-info">  Evaluate  <span class="glyphicon glyphicon-star-empty"></span></button>
                                                </form>
                                            </c:if>
                                        </td>

                                    </tr>
                                </tbody>

                            </table> 
                        </div>
                    </c:if>

                </div>

            </div>
            <br> <br> <br> <br> <br> <br> <br> <br>
        </div>






        <button class="open-button" onclick="openForm()">Chat</button>


        <div class="chat-popup" id="myForm">
            <div class="form-container">
                <h1>Chat</h1>

                <label for="msg"><b>Message</b></label>
                <textarea placeholder="Type message.." id="commentx" required></textarea>
                <button  class="btn" onclick="sendMsg()">Send</button>
                <button  class="btn cancel" onclick="closeForm()">Close</button>
            </div>
        </div>

        <script>


            var messageFlag = false;

            function sendMsg(param) {


                var cx = "\n" + document.getElementById("commentx").value;
                if (param) {
                    cx = param;
                }

                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        console.log("msg sent");
                        messageFlag = true;
                        closeForm();
                    }
                };
                xmlhttp.open("GET", "./messageboard?bookingid=${currentBookingDTO.id}&comment=" + cx, true);
                xmlhttp.send();


            }


            document.querySelector('#ccancel').addEventListener('click', function (e) {
                console.log("CANCEL PRESSED");
                sendMsg("This event was Cancelled....");
            });




        </script>



        <script>
            function openForm() {
                document.getElementById("myForm").style.display = "block";
            }

            function closeForm() {
                document.getElementById("commentx").value = "";
                document.getElementById("myForm").style.display = "none";
            }
        </script>


        <a href="#" class="playSound" style="display: none;">Play</a>

        <script>
            var playsoundElem = document.getElementsByClassName("playSound")[0];
            var docSizePrev = 0;

            function showMB() {


                var xmlhttp = new XMLHttpRequest();
                xmlhttp.onreadystatechange = function () {
                    if (this.readyState === 4 && this.status === 200) {
                        var response = this.responseText;
                        if (!messageFlag && response.length !== docSizePrev) {
                            docSizePrev = response.length;
                            playsoundElem.click();
                        }
                        if (messageFlag) {
                            messageFlag = false;
                            docSizePrev = response.length;
                        }

                        document.getElementById("msgBoard").innerHTML = response;
                    }
                };
                xmlhttp.open("GET", "./messageboard?bookingid=${currentBookingDTO.id}&ajax=true", true);
                xmlhttp.send();

            }
            setInterval(showMB, 2000);

        </script>


        <script>

            $(document).ready(function () {
                var obj = document.createElement("audio");
                obj.src = "./assets/Message-notification.mp3";
                obj.volume = 0.50;
                obj.autoPlay = false;
                obj.preLoad = true;

                $(".playSound").click(function () {
                    obj.play();
                });

            });


            window.setInterval(function () {
                var elem = document.getElementById('msgBoard');
                elem.scrollTop = elem.scrollHeight;
            }, 1000);
            ;

            document.querySelector('#commentx').addEventListener('keypress', function (e) {
                if (e.key === 'Enter') {
                    sendMsg();
                }
            });

        </script>






    </body>

</html>








