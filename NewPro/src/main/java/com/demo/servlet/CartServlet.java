package com.demo.servlet;

import com.demo.bean.User;
import com.demo.service.OrderService;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
//com.demo.servlet.CartServlet.java
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
 @SuppressWarnings("unchecked")
 private Map<Integer,Integer> getCart(HttpSession session) {
     Object o = session.getAttribute("cart");
     if (o == null) {
         Map<Integer,Integer> cart = new HashMap<>();
         session.setAttribute("cart", cart);
         return cart;
     }
     return (Map<Integer,Integer>) o;
 }

 @Override
 protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String action = req.getParameter("action"); // add/remove/checkout
     HttpSession session = req.getSession();
     Map<Integer,Integer> cart = getCart(session);

     if ("add".equals(action)) {
         int productId = Integer.parseInt(req.getParameter("productId"));
         int qty = Integer.parseInt(req.getParameter("qty"));
         cart.put(productId, cart.getOrDefault(productId, 0) + qty);
         resp.sendRedirect("products");
     } else if ("remove".equals(action)) {
         int productId = Integer.parseInt(req.getParameter("productId"));
         cart.remove(productId);
         resp.sendRedirect("cart");
     } else if ("checkout".equals(action)) {
         com.demo.bean.User user = (com.demo.bean.User) session.getAttribute("user");
         if (user == null) {
             resp.sendRedirect("login");
             return;
         }
         try {
             com.demo.service.OrderService os = new com.demo.service.OrderService();
             //  place order and get orderId
             int orderId = os.placeOrder(user.getId(), cart);
             session.setAttribute("orderId", orderId);

             //  compute total and store for payment page
             java.math.BigDecimal total = java.math.BigDecimal.ZERO;
             com.demo.service.ProductService ps = new com.demo.service.ProductService();
             for (Map.Entry<Integer,Integer> e : cart.entrySet()) {
                 com.demo.bean.Product p = ps.get(e.getKey());
                 java.math.BigDecimal line = p.getPrice().multiply(java.math.BigDecimal.valueOf(e.getValue()));
                 total = total.add(line);
             }
             session.setAttribute("orderTotal", total);

             //  clear cart after order is created
             session.removeAttribute("cart");

             // go to payment page
             resp.sendRedirect("payment.jsp");
         } catch (Exception e) {
             req.setAttribute("error", "Checkout failed: " + e.getMessage());
             req.getRequestDispatcher("cart.jsp").forward(req, resp);
         }
     }
 }

 @Override
 protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     req.getRequestDispatcher("cart.jsp").forward(req, resp);
 }
}

