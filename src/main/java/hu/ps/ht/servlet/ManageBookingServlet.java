package hu.ps.ht.servlet;

import hu.ps.ht.dto.BookingDTO;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.BookingService;
import hu.ps.ht.service.GuideService;
import java.io.IOException;
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
 * @author Barnabás
 */
@WebServlet(name = "ManageBookingServlet", urlPatterns = {"/managebooks"})
@DeclareRoles({Role.GUIDE, Role.TRAVELER})
@Slf4j
public class ManageBookingServlet extends HttpServlet {

    @Inject
    BookingService bookingService;
    @Inject
    GuideService guideService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.isUserInRole(Role.GUIDE) && !req.isUserInRole(Role.TRAVELER)) {
            HttpSession session = req.getSession();
            session.invalidate();
            log.info("Forward to Login.jsp NOT a GUIDE or TRAVELER");
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getUserPrincipal().getName();

        List<BookingDTO> bookingDTOsInactive = null;
        List<BookingDTO> bookingDTOsActive = null;

        if (request.isUserInRole(Role.GUIDE)) {
            bookingDTOsInactive = bookingService.getInactiveBookingList(username, Role.GUIDE);
            bookingDTOsActive = bookingService.getActiveBookingList(username, Role.GUIDE);
        } else {
            bookingDTOsInactive = bookingService.getInactiveBookingList(username, Role.TRAVELER);
            bookingDTOsActive = bookingService.getActiveBookingList(username, Role.TRAVELER);
        }

        request.setAttribute("inactive", bookingDTOsInactive);
        request.setAttribute("active", bookingDTOsActive);

        request.getRequestDispatcher("WEB-INF/listbooks.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        request.getRequestDispatcher("WEB-INF/managebooks.jsp").forward(request, response);
    }

}
