<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <script src="https://api.mqcdn.com/sdk/mapquest-js/v1.3.2/mapquest.js"></script>
        <link type="text/css" rel="stylesheet" href="https://api.mqcdn.com/sdk/mapquest-js/v1.3.2/mapquest.css"/>

        <script type="text/javascript">
            window.onload = function () {
                //LHEOJAhszSLSF49Dw21Ay9rU4NdnjDs6
                //lYrP4vF3Uk5zgTiGGuEzQGwGIVDGuy24
                L.mapquest.key = 'LHEOJAhszSLSF49Dw21Ay9rU4NdnjDs6';

                var map = L.mapquest.map('map', {
                    center: [${requestScope.latitude}, ${requestScope.longitude}],
                    layers: L.mapquest.tileLayer('map'),
                    zoom: 14
                });

                map.addControl(L.mapquest.control());

                var marky = L.mapquest.textMarker([${requestScope.latitude}, ${requestScope.longitude}], {
                    text: '${requestScope.markerText}',
                    subtext: '${requestScope.markerSubtext}',
                    position: 'right',
                    type: 'marker',
                    title: 'HelloTourist!',
                    icon: {
                        primaryColor: '#FF44FF',
                        secondaryColor: '#AA55FF',
                        size: 'sm'
                    }
                }).addTo(map);




                map.on('click', function (e) {
                    var popLocation = e.latlng;
                    console.log(popLocation);
                    try {

                        parent.document.getElementById("inputGeoloc").innerHTML = popLocation;
                        parent.document.getElementById("inputGeoloc").value = popLocation;
                    } catch (error) {
                        console.log("no such element, map.jsp");
                    }

                    var popup = L.popup()
                            .setLatLng(popLocation)
                            .setContent('<p>Location set<br>By User</p>')
                            .openOn(map);
                });




            };



        </script>
    </head>

    <body style="border: 0; margin: 0;">
        <p id="info">This is the meeting point on map </p>
        <div id="map" style="width: 100%; height: 530px;"></div>
    </body>
</html>
