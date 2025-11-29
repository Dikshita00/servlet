package com.demo.servlet;

import com.demo.service.AuthService;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/forgot")
public class ForgotPasswordServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("forgot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String newPass = req.getParameter("newPassword");
        try {
            boolean ok = authService.resetPassword(email, newPass);
            req.setAttribute(ok ? "msg" : "error", ok ? "Password updated." : "Email not found.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Reset failed: " + e.getMessage());
            req.getRequestDispatcher("forgot.jsp").forward(req, resp);
        }
    }
}
