package com.demo.servlet;

import com.demo.bean.User;
import com.demo.service.AuthService;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("logout".equalsIgnoreCase(action)) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            resp.sendRedirect("index.jsp");
        } else {
            // Default: forward to login page
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("login".equalsIgnoreCase(action)) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            try {
                User u = authService.login(email, password);
                if (u != null) {
                    HttpSession session = req.getSession();
                    session.setAttribute("user", u);
                    resp.sendRedirect("products");
                } else {
                    req.setAttribute("error", "Invalid credentials");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            } catch (Exception e) {
                req.setAttribute("error", "Login error: " + e.getMessage());
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } else if ("register".equalsIgnoreCase(action)) {
            // You can delegate to RegisterServlet or handle inline
            resp.sendRedirect("register");
        } else {
            resp.sendRedirect("index.jsp");
        }
    }
}
