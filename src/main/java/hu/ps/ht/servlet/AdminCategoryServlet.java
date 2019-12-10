/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ps.ht.servlet;

import hu.ps.ht.dto.CategoryDTO;
import hu.ps.ht.enumerated.OutcomeMessage;
import hu.ps.ht.service.CategoryService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
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
@WebServlet(name = "AdminCategoryServlet", urlPatterns = {"/admindeletecategory"})
public class AdminCategoryServlet extends HttpServlet {

    @Inject
    CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CategoryDTO> categoryList = categoryService.findAll()
                .stream()
                .collect(Collectors.toList());
        
        request.setAttribute("category", categoryList);
        

        request.getRequestDispatcher("WEB-INF/adminwelcome.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (request.getParameter("deleteCategory") != null) {
            Long categoryId = Long.valueOf(request.getParameter("deleteCategory"));
            OutcomeMessage message = categoryService.deleteCategoryById(categoryId);
            if (message == OutcomeMessage.success) {
                session.setAttribute("deleteOutcome", "Sikeres törlés");
            } else {
                session.setAttribute("deleteOutcome", "Sikertelen törlés");
            }
        }
        response.sendRedirect("admin");
    }

}
