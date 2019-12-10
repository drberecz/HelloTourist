package hu.ps.ht.servlet;

import hu.ps.ht.enumerated.Region;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.RegionService;
import hu.ps.ht.util.CredentialsManager;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TestServlet", urlPatterns = {"/test"})
@DeclareRoles({Role.GUIDE, Role.TRAVELER, Role.ADMIN})
public class TestServlet extends HttpServlet {

    @Inject
    private RegionService regionService;

    @Inject
    private CredentialsManager credentialsManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = credentialsManager.validateRoles(request);

        request.setAttribute("regionList", Arrays.asList(Region.values()));
        request.setAttribute("regionImage", Region.getRegionImageLinks());;

        if (request.isUserInRole(Role.GUIDE)) {

            request.setAttribute("guideName", request.getUserPrincipal().getName());
            request.getRequestDispatcher("./assets/welcomepageguide.jsp").forward(request, response);
        } else if (request.isUserInRole(Role.ADMIN)) {
            request.getRequestDispatcher("WEB-INF/adminwelcome.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("./assets/welcomepage.jsp").forward(request, response);
        }
    }

}
