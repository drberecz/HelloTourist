<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <script src="https://api.mqcdn.com/sdk/mapquest-js/v1.3.2/mapquest.js"></script>
        <link type="text/css" rel="stylesheet" href="https://api.mqcdn.com/sdk/mapquest-js/v1.3.2/mapquest.css"/>

        <script type="text/javascript">
            window.onload = function () {
                console.log("holaz event");
                //LHEOJAhszSLSF49Dw21Ay9rU4NdnjDs6
                //lYrP4vF3Uk5zgTiGGuEzQGwGIVDGuy24
                L.mapquest.key = 'LHEOJAhszSLSF49Dw21Ay9rU4NdnjDs6';

                var map = L.mapquest.map('map', {
                    center: [47.500445, 19.046774],
                    layers: L.mapquest.tileLayer('map'),
                    zoom: 8
                });

                map.addControl(L.mapquest.control());


            <c:set var="count" value="0" scope="page" />
            <c:forEach items="${requestScope.eventList}" var="item">
                <c:set var="count" value="${count+1}" scope="page" />
                console.log('${item.guideUserName}');
                console.log('${item.getRegion()}');
                var marky${count} = L.mapquest.textMarker([${item.getXGeoLatitude()}, ${item.getYGeoLongitude()}], {
                    text: '${item.getCategory().getName()}',
                    subtext: '${item.guideUserName}',
                    position: 'right',
                    type: 'marker',
                    title: ' ${item.guideUserName}\n ${item.getCategory().getName()}\n ${item.description}\n cost:${item.cost} HUF',
                    icon: {
                        primaryColor: '#FF44FF',
                        secondaryColor: '#AA55FF',
                        size: 'sm'
                    }
                }).addTo(map);


                marky${count}.on('click', function (e) {
                    var popLocation = e.latlng;
                    console.log(popLocation);
                    document.getElementById("info").innerHTML = popLocation;
                    var popup = L.popup()
                            .setLatLng(popLocation)
                            .setContent('<h1><a target="_parent" href="./booking?eventid=${item.id}">BOOK THIS</a></h1>')
                            .openOn(map);
                });


            </c:forEach>







            };



        </script>
    </head>

    <body style="border: 0; margin: 0;">


        <p id="info">Events on MAP</p>
        <div id="map" style="width: 100%; height: 530px;"></div>
    </body>
</html>

