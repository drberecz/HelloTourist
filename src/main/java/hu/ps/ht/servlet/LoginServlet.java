package hu.ps.ht.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
@Slf4j
public class LoginServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("LOGIN forward from loginservlet");
        req.getRequestDispatcher("WEB-INF/login.jsp").forward(req, resp);

    }

}
