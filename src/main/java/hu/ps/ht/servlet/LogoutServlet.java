package hu.ps.ht.servlet;

import hu.ps.ht.enumerated.Role;
import hu.ps.ht.util.CredentialsManager;
import java.io.IOException;
import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
@DeclareRoles({Role.GUIDE, Role.TRAVELER, Role.ADMIN})
public class LogoutServlet extends HttpServlet {

    @Inject
    private CredentialsManager credentialsManager;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        HttpSession sessionNew = request.getSession();
        sessionNew.setAttribute("logoutsuccess", "o.k.");
        credentialsManager.validateRoles(request);

        response.sendRedirect("./test");

    }

}
