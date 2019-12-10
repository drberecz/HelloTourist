/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ps.ht.servlet;

import hu.ps.ht.dto.TravellerDTO;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.TravellerService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.security.DeclareRoles;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Csaba
 */
@WebServlet(name = "CountryStatisticServlet", urlPatterns = {"/countrystatistic"})
@DeclareRoles({"guide", "traveler", "admin"})
public class CountryStatisticServlet extends HttpServlet {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    
    @Inject
    TravellerService travellerService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.isUserInRole(Role.ADMIN)) {
            HttpSession session = req.getSession();
            session.invalidate();
            LOGGER.info("Forward to Login.jsp your a not a admin");
            req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        super.service(req, resp);
    }

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<TravellerDTO> travellersList = travellerService.findAll()
                .stream()
                .collect(Collectors.toList());
        request.setAttribute("country", travellersList);
       

        Map<String, Integer> countryMap = travellerService.sumCountry();
                countryMap.entrySet()
                        .stream()
                        .forEach(x-> System.out.println(x.getKey()+"=>"+x.getValue()));

        
        
        
        request.setAttribute("countrymap", countryMap);

        request.getRequestDispatcher("WEB-INF/travelerbycountry.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
