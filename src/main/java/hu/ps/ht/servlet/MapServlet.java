package hu.ps.ht.servlet;

import hu.ps.ht.dto.EventDTO;
import hu.ps.ht.service.EventService;
import hu.ps.ht.util.GeolocationCache;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MapServlet", urlPatterns = {"/map"})
public class MapServlet extends HttpServlet {

    @Inject
    EventService eventService;

    //http://localhost:8080/HelloTourist2/map?latitude=47.594009&longitude=19.116468&markertext=Tour_Starts_Here&markersubtext=
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("advanced") != null) {
            List<EventDTO> eventDTOs = eventService.findAllEvents();

            eventDTOs.stream().filter((eventDTO) -> (eventDTO.getXGeoLatitude() == null || eventDTO.getYGeoLongitude() == null)).forEachOrdered((eventDTO) -> {
                Double[] geoPointArray = GeolocationCache.getGeoPointArrayByRegion(eventDTO.getRegion());
                eventDTO.setXGeoLatitude(geoPointArray[0]);
                eventDTO.setYGeoLongitude(geoPointArray[1]);
            });
            request.setAttribute("eventList", eventDTOs);
            request.getRequestDispatcher("./mapadvanced.jsp").forward(request, response);
            return;
        }

        String latitude = request.getParameter("latitude");
        String longitude = request.getParameter("longitude");
        // String markerText= request.getParameter("markertext");
        String markerText = "Tour Starts Here";
        String markerSubtext = request.getParameter("markersubtext");

        request.setAttribute("latitude", latitude);
        request.setAttribute("longitude", longitude);
        request.setAttribute("markerText", markerText);
        request.setAttribute("markerSubtext", markerSubtext);

        request.getRequestDispatcher("./map.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
