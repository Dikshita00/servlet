package com.demo.servlet;

import com.demo.service.AuthService;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String gender = req.getParameter("gender");
        String[] hobbiesArr = req.getParameterValues("hobbies");
        String hobbies = hobbiesArr != null ? String.join(",", hobbiesArr) : "";
        String city = req.getParameter("city");
        String password = req.getParameter("password");

        try {
            authService.register(name, email, gender, hobbies, city, password);
            req.setAttribute("msg", "Registration successful. Please login.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", e.getMessage());
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}
