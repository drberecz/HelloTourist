/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ps.ht.servlet;

import hu.ps.ht.dto.EventDTO;
import hu.ps.ht.service.EventService;
import hu.ps.ht.util.EventManagerUtil;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EventsByRegion", urlPatterns = {"/eventsbyregion"})
public class EventsByRegionServlet extends HttpServlet {

    @Inject
    EventService eventService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String input = req.getParameter("q");

        List<EventDTO> eventList = eventService.getEventDtoListByRegion(input);
        EventManagerUtil.convertDTOsWeeklyPatternAttributesTo3CharNames(eventList);
        req.setAttribute("eventList", eventList);

        if (eventList == null || eventList.isEmpty()) {
            HttpSession session = req.getSession();

            session.setAttribute("error", "Sorry, no Events available in this area.");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("error", "Events available in area: " + input);

        }

        req.getRequestDispatcher("./assets/eventsbyregion.jsp").forward(req, resp);
    }

}
