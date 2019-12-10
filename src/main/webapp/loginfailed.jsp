
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


            #centeredCard {
                height: 100%;
                display: flex;
                align-items: center;
                justify-content: center;
            }

        </style>


        <!--Bootsrap 4 CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

        <!--Fontawesome CDN-->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

        <!--Custom styles-->
        <link href="<c:url value='/assets/css/loginstyle.css' />" rel="stylesheet">
    </head>
    <body>


        <div id='centeredCard' class="col-sm">
            <div class="card text-white bg-info mb-3" style="max-width: 30rem;  margin: 15px;">
                <div class="card-header">error</div>
                <div class="card-body">
                    <h4 class="card-title">Login Failed<br><br>
                        <a href="./test">Back to the Homepage<br><br>
                            <a href="./login">Try again</a></h4>
                    <p class="card-text"><br> <br> </p>
                </div>
            </div>
            <!--</a>-->
        </div>      


    </body>
</html>








