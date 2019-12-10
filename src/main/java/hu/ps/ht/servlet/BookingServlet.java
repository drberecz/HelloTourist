/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ps.ht.servlet;

import hu.ps.ht.dto.BookingDTO;
import hu.ps.ht.dto.EventDTO;
import hu.ps.ht.enumerated.BookingStatus;
import hu.ps.ht.enumerated.WeeklyPattern;
import hu.ps.ht.service.BookingService;
import hu.ps.ht.service.EventService;
import hu.ps.ht.util.EventManagerUtil;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BookingServlet", urlPatterns = {"/booking"})
@DeclareRoles({"guide", "traveler", "admin"})
public class BookingServlet extends HttpServlet {

    @Inject
    EventService eventService;

    @Inject
    BookingService bookingService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.isUserInRole("traveler")) {
            HttpSession session = req.getSession();
            session.invalidate();
            System.out.println("Forward to Login.jsp******not a traveler*******************");
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //refaktor
        Long eventId = Long.parseLong(request.getParameter("eventid"));
        EventDTO eventDTO = eventService.getEventDtoById(eventId);

        Double[] geoPointArray = eventService.getEventGeolocation(eventDTO);
        if (geoPointArray != null) {
            request.setAttribute("geolat", geoPointArray[0]);
            request.setAttribute("geolon", geoPointArray[1]);
        }

        List<EventDTO> eventDTOSingle = new ArrayList<>();
        eventDTOSingle.add(eventDTO);

        StringBuilder sb = new StringBuilder();
        String[] daymod = new String[7];
        String dayOfWeek = eventDTO.getDayOfWeek();

        for (int i = 0; i < dayOfWeek.length(); i++) {
            if (dayOfWeek.charAt(i) == 'X') {
                daymod[i] = "background-color:red;";
                sb.append(WeeklyPattern.SEVEN_DAYS_ARRAY[i]).append(",");
            }
        }

        EventManagerUtil.convertDTOsWeeklyPatternAttributesTo3CharNames(eventDTOSingle);

        request.setAttribute("eventList", eventDTOSingle);
        request.setAttribute("daymod", daymod);
        request.setAttribute("daysOff", sb.toString());

        StringBuilder reservedDates = new StringBuilder();
        List<BookingDTO> bdtos = bookingService.getAllBookingsQueryGuideName(eventDTO.getGuideUserName());
        for (BookingDTO bdto : bdtos) {
            if (bdto.getBookingStatus()!=BookingStatus.CANCELLED){
            reservedDates.append(bdto.getDateOfTour().toString()).append(",");
            }
        }
        request.setAttribute("reservedDates", reservedDates.toString());

        List<Integer> participantsValues = new ArrayList<>();
        Integer maxPersons = Math.toIntExact(eventDTO.getMaxParticipants());
        if (maxPersons > 1) {
            for (int i = 2; i <= eventDTO.getMaxParticipants(); i++) {
                participantsValues.add(i);
            }
        }
        request.setAttribute("participants", participantsValues);
        request.setAttribute("eventId", eventDTO.getId());

        request.getRequestDispatcher("WEB-INF/booking.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String travelerUsername = (String) session.getAttribute("username");

        String eventIdStr = req.getParameter("eventId");
        String datePickedStr = req.getParameter("datePickedVal");
        String numofPstr = req.getParameter("numOfPersonsSel");

        System.out.println(eventIdStr);
        System.out.println(datePickedStr);
        System.out.println(numofPstr);

        Long eventId = Long.parseLong(eventIdStr);
        LocalDate dateOfBooking = LocalDate.parse(datePickedStr);
        Long numOfPersons = Long.parseLong(numofPstr);

        EventDTO eventDTO = eventService.getEventDtoById(eventId);

        BookingDTO bookingDTO = BookingDTO
                .builder()
                .bookingStatus(BookingStatus.AWAITING_CONFIRMATION)
                .cost(eventDTO.getCost())
                .dateOfTour(dateOfBooking)
                .description(eventDTO.getDescription())
                .guideName(eventDTO.getGuideUserName())
                .messageBoard(new StringBuffer("Created at " + LocalDateTime.now().toString()))
                .travellerName(travelerUsername)
                .build();
        bookingService.createNewBooking(bookingDTO);

        session.setAttribute("bookingSuccess", "true");
        resp.sendRedirect("./test");
    }

}
