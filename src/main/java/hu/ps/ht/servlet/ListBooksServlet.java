package hu.ps.ht.servlet;

import hu.ps.ht.dto.BookingDTO;
import hu.ps.ht.dto.GuideDTO;
import hu.ps.ht.enumerated.BookingStatus;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.BookingService;
import hu.ps.ht.service.GuideService;
import java.io.IOException;
import java.time.LocalDate;
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

@WebServlet(name = "ListBooksServletGuide", urlPatterns = {"/listbooks"})
@DeclareRoles({Role.GUIDE, Role.TRAVELER})
@Slf4j
public class ListBooksServlet extends HttpServlet {

    @Inject
    BookingService bookingService;

    @Inject
    GuideService guideService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.isUserInRole(Role.GUIDE) && !req.isUserInRole(Role.TRAVELER)) {
            HttpSession session = req.getSession();
            session.invalidate();
            log.info("Forward to Login.jsp NOT  a GUIDE or TRAVERER");
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
            log.info("this is a guide role" + Role.GUIDE);
            bookingDTOsInactive = bookingService.getInactiveBookingList(username, Role.GUIDE);
            bookingDTOsActive = bookingService.getActiveBookingList(username, Role.GUIDE);
        } else {
            log.info("this is a traveler role");

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

        String username = request.getUserPrincipal().getName();

        Long bookingIdfromListBooks = Long.valueOf(request.getParameter("bookingId"));
        BookingDTO currentBookingDTO = bookingService.findBookingById(bookingIdfromListBooks);
        BookingStatus bookingStatus = currentBookingDTO.getBookingStatus();

        GuideDTO selectedGuideDTO = guideService.getGuideByUsername(currentBookingDTO.getGuideName());
        request.setAttribute("selectedGuide", selectedGuideDTO);

        request.setAttribute("currentBookingDTO", currentBookingDTO);
        LocalDate tourDate = currentBookingDTO.getDateOfTour();
        if (guideService.guideCanConfirm(request.isUserInRole(Role.GUIDE), bookingStatus, tourDate)) {
            request.setAttribute("confirmButton", "O.K.");
        }
        if (request.isUserInRole(Role.GUIDE) || request.isUserInRole(Role.TRAVELER)) {
            if (bookingService.bookingCanBeCancelled(bookingStatus)) {
                request.setAttribute("cancelButton", "O.K.");
            }
        }
        if (guideService.guideCanComplete(request.isUserInRole(Role.GUIDE), bookingStatus, tourDate)) {

            request.setAttribute("completeButton", "O.K.");
        }

        if (request.isUserInRole(Role.TRAVELER)) {
            if (bookingService.bookingCanBeEvaluated(bookingStatus)) {
                request.setAttribute("evaluateButton", "O.K.");

            }
        }

        request.getRequestDispatcher("WEB-INF/managebooks.jsp").forward(request, response);
    }

}
