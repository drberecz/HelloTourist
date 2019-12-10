/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ps.ht.servlet;

import hu.ps.ht.dto.BookingDTO;
import hu.ps.ht.dto.GuideDTO;
import hu.ps.ht.enumerated.BookingStatus;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.BookingService;
import hu.ps.ht.service.GuideService;
import hu.ps.ht.util.EvaluationCacheUtil;
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

@WebServlet(name = "EvalServlet", urlPatterns = {"/eval"})
@DeclareRoles({Role.GUIDE, Role.TRAVELER})
@Slf4j
public class EvalServlet extends HttpServlet {

    @Inject
    GuideService guideService;

    @Inject
    BookingService bookingService;

    @Inject
    EvaluationCacheUtil evaluationCacheUtil;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.isUserInRole(Role.TRAVELER)) {
            HttpSession session = req.getSession();
            session.invalidate();
            log.info("Forward to Login.jsp NOT a TRAVELER");
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/eval.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        log.info("EvalServlet  called!");
        Long bookingId = null;
        try {
            bookingId = Long.parseLong(request.getParameter("bookingId"));
        } catch (NumberFormatException e) {
            log.debug("Booking ID NULL!");

        }
        HttpSession session = request.getSession();
        String travelerUsername = (String) session.getAttribute("userName");
        log.debug("CURRENT travler USERNAME: " + travelerUsername);

        String scoreString = request.getParameter("myField");

        Integer score = null;
        if (isScoreStringSentWithValidEvaluation(scoreString) && bookingIsNotEvaluated(bookingId)) {

            try {
                score = Integer.parseInt(scoreString);

            } catch (NumberFormatException e) {
                log.error("EvalServlet parse : " + e.getLocalizedMessage());
            }
            String guideName = request.getParameter("guide");

            GuideDTO guideDTO = null;
            guideDTO = guideService.getGuideByUsername(guideName);

            guideService.evaluateGuide(guideDTO, score);
            bookingService.changeBookingStatusToEvaluated(bookingId);

            log.info("Guide evaulated!");
            evaluationCacheUtil.addScoreToEvalLogByGuideName(request.getUserPrincipal().getName(), guideName, score);
            session.setAttribute("guidenameFromEval", guideName);
            response.sendRedirect("./guideprofile");
            return;
        }
        session.setAttribute("evalError", "true");
        response.sendRedirect("./listbooks");
    }

    boolean isScoreStringSentWithValidEvaluation(String scoreString) {
        if (scoreString != null && !scoreString.isEmpty()) {
            return true;
        }
        return false;
    }

    boolean bookingIsNotEvaluated(Long bookingId) {
        BookingDTO bookingDTO = null;
        bookingDTO = bookingService.findBookingById(bookingId);
        if (bookingDTO.getBookingStatus() == BookingStatus.EVALUATED) {
            return false;
        }
        return true;
    }

}
