package com.demo.servlet;

import com.demo.bean.User;
import com.demo.service.AuthService;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

//com.demo.servlet.LoginServlet.java
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
 private final com.demo.service.AuthService authService = new com.demo.service.AuthService();

 @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     req.getRequestDispatcher("login.jsp").forward(req, resp);
 }

 @Override
 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String email = req.getParameter("email");
     String password = req.getParameter("password");
     try {
         com.demo.bean.User user = authService.login(email, password);
         if (user != null) {
             HttpSession session = req.getSession();
             session.setAttribute("user", user); //  required for checkout
             resp.sendRedirect("products");
         } else {
             req.setAttribute("error", "Invalid email or password");
             req.getRequestDispatcher("login.jsp").forward(req, resp);
         }
     } catch (Exception e) {
         req.setAttribute("error", "Login failed: " + e.getMessage());
         req.getRequestDispatcher("login.jsp").forward(req, resp);
     }
 }
}

