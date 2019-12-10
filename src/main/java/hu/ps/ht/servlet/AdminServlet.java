/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ps.ht.servlet;

import hu.ps.ht.dto.CategoryDTO;
import hu.ps.ht.enumerated.OutcomeMessage;
import hu.ps.ht.enumerated.Role;
import hu.ps.ht.service.CategoryService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
@DeclareRoles({"guide", "traveler", "admin"})
public class AdminServlet extends HttpServlet {
    
    
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Inject
    CategoryService categoryService;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CategoryDTO> categoryList = categoryService.findAll()
                .stream()
                .collect(Collectors.toList());
        
        req.setAttribute("category", categoryList);

        

        req.getRequestDispatcher("WEB-INF/adminwelcome.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CategoryDTO categoryDTO = new CategoryDTO();

        String category = req.getParameter("category");
        categoryDTO.setName(category);

        categoryService.createNewCategory(categoryDTO);
        resp.sendRedirect("admin");

    }

}
