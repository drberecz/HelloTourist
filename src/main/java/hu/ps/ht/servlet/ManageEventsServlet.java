package hu.ps.ht.servlet;

import hu.ps.ht.dao.CategoryDAO;
import hu.ps.ht.dto.CategoryDTO;
import hu.ps.ht.dto.EventDTO;
import hu.ps.ht.dto.GuideDTO;
import hu.ps.ht.enumerated.Region;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.CategoryService;
import hu.ps.ht.service.EventService;
import hu.ps.ht.service.GuideService;
import hu.ps.ht.service.RegionService;
import hu.ps.ht.util.EventManagerUtil;
import hu.ps.ht.util.GeolocationCache;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Barnabas
 */
@WebServlet(name = "ManageEventsServlet", urlPatterns = {"/eventmanager"})
@DeclareRoles({"guide", "traveler", "admin"})
@Slf4j
public class ManageEventsServlet extends HttpServlet {

    @Inject
    GuideService guideService;

    @Inject
    CategoryService categoryService;

    @Inject
    RegionService regionService;

    @Inject
    EventService eventService;

    @Inject
    CategoryDAO categoryDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.isUserInRole(Role.GUIDE)) {
            HttpSession session = req.getSession();
            session.invalidate();
            log.info("Forward to Login.jsp your not a guide");
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Principal principal = request.getUserPrincipal();
        String usernameGuide = (principal != null) ? principal.getName() : null;

        GuideDTO selectedGuideDTO = guideService.getGuideByUsername(usernameGuide);
        request.setAttribute("selectedGuide", selectedGuideDTO);

        Double[] geoPointArray = GeolocationCache.getGeoPointArrayByRegion(selectedGuideDTO.getOperatesInRegion());
        if (geoPointArray != null) {
            request.setAttribute("geolat", geoPointArray[0]);
            request.setAttribute("geolon", geoPointArray[1]);
        }

        List<EventDTO> currentEventDTOs = eventService.getEventDtoListByGuide(selectedGuideDTO.getUserName());
        request.setAttribute("allEventByGuide", currentEventDTOs);

        List<CategoryDTO> allAvailableCategoryList = categoryService.findAll();
        request.setAttribute("allCategory", allAvailableCategoryList);

        List<Region> regionList = Arrays.asList(Region.values());;
        request.setAttribute("region", regionList);

        request.getRequestDispatcher("WEB-INF/eventmanager.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        String region = request.getParameter("regions");
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        Long numberOfMaxParticipants = Long.valueOf(request.getParameter("max"));
        Long cost = Long.valueOf(request.getParameter("cost"));
        String pattern = request.getParameter("pattern");

        String patternValue = EventManagerUtil.switchWeeklyPatternContants(pattern);

        Principal principal = request.getUserPrincipal();
        String usernameGuide = (principal != null) ? principal.getName() : null;

        EventDTO eventDTO = EventDTO.builder()
                .category(categoryDAO.findByName(category))
                .cost(cost)
                .dayOfWeek(patternValue)
                .description(description)
                .guideUserName(usernameGuide)
                .maxParticipants(numberOfMaxParticipants)
                .region(Region.valueOf(region))
                .build();

        Double[] geolocArray
                = EventManagerUtil.convertStringInputGeolocationToDouble(request.getParameter("geolocString"));
        if (geolocArray != null) {
            eventDTO.setXGeoLatitude(geolocArray[0]);
            eventDTO.setYGeoLongitude(geolocArray[1]);
        }

        eventService.createNewEventFromDTO(eventDTO);

        HttpSession session = request.getSession();
        session.setAttribute("newEventSuccess", "o.k.");
        response.sendRedirect(
                "./test");
    }

}
