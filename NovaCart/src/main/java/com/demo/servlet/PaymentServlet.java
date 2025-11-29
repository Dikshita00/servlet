package com.demo.servlet;

import com.demo.service.OrderService;
import com.demo.service.PaymentService;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.math.BigDecimal;

//com.demo.servlet.PaymentServlet.java
@WebServlet("/pay")
public class PaymentServlet extends HttpServlet {
 @Override
 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     HttpSession session = req.getSession();
     Integer orderIdObj = (Integer) session.getAttribute("orderId");
     java.math.BigDecimal total = (java.math.BigDecimal) session.getAttribute("orderTotal");

     if (orderIdObj == null || total == null) {
         req.setAttribute("error", "No order in session. Please checkout again.");
         req.getRequestDispatcher("cart.jsp").forward(req, resp);
         return;
     }

     int orderId = orderIdObj.intValue();
     String method = req.getParameter("method"); // CARD/UPI/COD

     try {
         com.demo.service.PaymentService ps = new com.demo.service.PaymentService();

         //  initiate payment with computed total
         int paymentId = ps.initiate(orderId, total, method);

         //  mark order paid
         if ("COD".equals(method)) {
             new com.demo.service.OrderService().markPaid(orderId);
             req.setAttribute("msg", "Order placed with Cash on Delivery. Order ID: " + orderId);
         } else {
             ps.succeed(paymentId, "CONFIRM-" + paymentId);
             new com.demo.service.OrderService().markPaid(orderId);
             req.setAttribute("msg", "Payment successful. Order ID: " + orderId + ", Payment ID: " + paymentId);
         }

         // clear order session data
         session.removeAttribute("orderId");
         session.removeAttribute("orderTotal");

         req.getRequestDispatcher("index.jsp").forward(req, resp);
     } catch (Exception e) {
         req.setAttribute("error", "Payment failed: " + e.getMessage());
         req.getRequestDispatcher("payment.jsp").forward(req, resp);
     }
 }
}
