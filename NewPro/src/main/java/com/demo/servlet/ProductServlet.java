package com.demo.servlet;

import com.demo.service.ProductService;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("products", productService.listProducts());
            req.getRequestDispatcher("products.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("error", "Unable to load products: " + e.getMessage());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
