package hu.ps.ht.servlet;

import hu.ps.ht.dto.GuideDTO;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.GuideService;
import hu.ps.ht.util.EvaluationCacheUtil;
import java.io.IOException;
import java.security.Principal;
import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@WebServlet(name = "GuideProfile", urlPatterns = {"/guideprofile"})
@DeclareRoles({Role.GUIDE, Role.TRAVELER})
@Slf4j
public class GuideProfile extends HttpServlet {

    @Inject
    EvaluationCacheUtil evaluationCacheUtil;

    @Inject
    GuideService guideService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String inputGuideNameFromUI = request.getParameter("guidename");
        if (inputGuideNameFromUI == null) {

            HttpSession session = request.getSession();
            inputGuideNameFromUI = (String) session.getAttribute("guidenameFromEval");
            request.setAttribute("evalsuccess", "true");

        }
        log.debug("guidename arrived from .jsp: " + inputGuideNameFromUI);

        GuideDTO selectedGuideDTO = new GuideDTO();
        try {
            selectedGuideDTO = guideService.getGuideByUsername(inputGuideNameFromUI);
        } catch (Exception ex) {
            log.error("No such guideEntity " + ex.getMessage());
        }
        request.setAttribute("selectedGuide", selectedGuideDTO);

        Principal principal = request.getUserPrincipal();
        if (principal != null && request.isUserInRole(Role.GUIDE)) {
            String evalLog = evaluationCacheUtil.getEvalLogByGuideName(inputGuideNameFromUI);
            request.setAttribute("evalLog", evalLog);

        }

        request.getRequestDispatcher("./WEB-INF/prof.jsp").forward(request, response);
    }

}
