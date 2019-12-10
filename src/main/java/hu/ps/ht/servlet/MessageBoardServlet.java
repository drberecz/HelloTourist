/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.ps.ht.servlet;

import hu.ps.ht.service.MessageBoardService;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MessageBoardServlet", urlPatterns = {"/messageboard"})
public class MessageBoardServlet extends HttpServlet {

    @Inject
    MessageBoardService messageBoardService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getUserPrincipal().getName();
        String bookingid = request.getParameter("bookingid");
        String comment = "User: " + username + ":>\n" + request.getParameter("comment");
        String ajax = request.getParameter("ajax");
        Long id = Long.valueOf(bookingid);

        if (ajax != null) {
            String str = messageBoardService.msgAjax(id, username);
            request.setAttribute("nana", str);

            request.getRequestDispatcher("WEB-INF/testajax.jsp").forward(request, response);
            return;
        }

        messageBoardService.addCommentToBoard(id, comment);

    }

}
