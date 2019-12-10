package hu.ps.ht.servlet;

import hu.ps.ht.dto.UserDTO;
import hu.ps.ht.enumerated.Region;
import hu.ps.ht.enumerated.OutcomeMessage;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.RegionService;
import hu.ps.ht.service.UserService;
import hu.ps.ht.util.StringEnumMatcherUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RegistrationAsGuideServlet", urlPatterns = {"/regasguide"})

public class RegistrationAsGuideServlet extends HttpServlet {

    @Inject
    UserService userService;

    @Inject
    RegionService regionService;

    @Inject
    StringEnumMatcherUtil enumMatcherUtil;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Region> regionList = Arrays.asList(Region.values());
        request.setAttribute("regionList", regionList);

        request.getRequestDispatcher("./assets/registrationguide.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        HttpSession httpSession = request.getSession();

        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String operatesInRegion = request.getParameter("region");
        String phone = request.getParameter("phone");
        String imagelink = request.getParameter("imageLink");

        Region region = enumMatcherUtil.matchInputWithRegionEnums(operatesInRegion);
        if (region == null) {
            System.out.println("Could not find Region for INPUT: " + operatesInRegion);
            region = Region.BUDAPEST;
        }

        UserDTO userDTO = UserDTO.builder()
                .email(email)
                .imageLink(imagelink)
                .operatesInRegion(region)
                .password(password)
                .phone(phone)
                .userName(userName)
                .build();

        OutcomeMessage result = null;
        try {
            result = userService.insertUserRoleBased(userDTO, Role.GUIDE);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(RegistrationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String str = "";
        if (result != null) {
            if (result == OutcomeMessage.error) {
                str = "Email or Username already exists";
            } else {
                str = "Registration successful";
            }
        }
        httpSession.setAttribute("registrationMsg", str);
        response.sendRedirect("./test");
    }

}
