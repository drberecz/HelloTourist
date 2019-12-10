package hu.ps.ht.servlet;

import hu.ps.ht.dto.BookingDTO;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.BookingService;
import hu.ps.ht.service.GuideService;
import hu.ps.ht.util.BookingUtil;
import java.io.IOException;
import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/*
 * @author Barnabás
 */
@WebServlet(name = "CancelServlet", urlPatterns = {"/cancel"})
@DeclareRoles({"guide", "traveler"})
@Slf4j
public class CancelServlet extends HttpServlet {

    @Inject
    BookingService bookingService;

    @Inject
    GuideService guideService;

    @Inject
    BookingUtil bookingUtil;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.isUserInRole(Role.GUIDE) && !req.isUserInRole(Role.TRAVELER)) {
            HttpSession session = req.getSession();
            session.invalidate();
            log.info("not a guide or traveler");
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("./listbooks");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String strContainsBookingIdAndOperationSelectorLetterAtEnd = request.getParameter("operation");
        log.debug("control string from jsp" + strContainsBookingIdAndOperationSelectorLetterAtEnd);
        Long bookingId = Long.valueOf(strContainsBookingIdAndOperationSelectorLetterAtEnd.substring(0, strContainsBookingIdAndOperationSelectorLetterAtEnd.length() - 1));

        log.debug("generated booking id:" + bookingId);
        BookingDTO bookingDTO = bookingService.findBookingById(bookingId);
        log.debug("BookingDTO arrived to CANCEL servlet" + bookingDTO);
        char select = strContainsBookingIdAndOperationSelectorLetterAtEnd.charAt(strContainsBookingIdAndOperationSelectorLetterAtEnd.length() - 1);

        HttpSession session = request.getSession();

        if (select != 'D') {
            session.setAttribute("result", bookingUtil.operationHelper(select, bookingDTO));
            response.sendRedirect("./listbooks");
        } else {
            request.setAttribute("bookingId", bookingDTO.getId());
            request.setAttribute("selectedGuide", guideService.getGuideByUsername(bookingDTO.getGuideName()));
            request.getRequestDispatcher("WEB-INF/eval.jsp").forward(request, response);
        }
    }

}
