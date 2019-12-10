package hu.ps.ht.servlet;

import hu.ps.ht.dto.UserDTO;
import hu.ps.ht.enumerated.OutcomeMessage;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.UserService;
import hu.ps.ht.util.RegistrationHashPassword;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Csaba
 */
@WebServlet(name = "Registration", urlPatterns = {"/reg"})
public class RegistrationServlet extends HttpServlet {

    @Inject
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("./assets/registrationtraveler.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {

        HttpSession httpSession = request.getSession();

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullname");
        String country = request.getParameter("country");
        String avatarHyperlink = request.getParameter("avatar_hyperlink");

        UserDTO userDTO = UserDTO.builder()
                .userName(userName)
                .country(country)
                .email(email)
                .fullName(fullName)
                .password(password)
                .imageLink(avatarHyperlink)
                .build();

        OutcomeMessage result = null;
        try {
            result = userService.insertUserRoleBased(userDTO, Role.TRAVELER);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String str = "";
        if (result != null) {
            if (result == OutcomeMessage.error) {
                str = "Email or Username already exists"; // konstans
            } else {
                str = "Registration successful";
            }
        }
        httpSession.setAttribute("registrationMsg", str);
        response.sendRedirect("./test");
    }

}
