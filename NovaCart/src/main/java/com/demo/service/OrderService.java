package com.demo.service;

import com.demo.bean.Product;
import com.demo.dao.OrderDAO;
import com.demo.dao.ProductDAO;

import java.math.BigDecimal;
import java.util.Map;

public class OrderService {
    private final OrderDAO orderDAO = new OrderDAO();
    private final ProductDAO productDAO = new ProductDAO();

    // cart: productId -> qty
    public int placeOrder(int userId, Map<Integer, Integer> cart) throws Exception {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
            Product p = productDAO.findById(e.getKey());
            if (p == null) throw new IllegalArgumentException("Product not found: " + e.getKey());
            BigDecimal line = p.getPrice().multiply(BigDecimal.valueOf(e.getValue()));
            total = total.add(line);
        }
        int orderId = orderDAO.createOrder(userId, total);

        for (Map.Entry<Integer, Integer> e : cart.entrySet()) {
            Product p = productDAO.findById(e.getKey());
            orderDAO.addItem(orderId, p.getId(), e.getValue(), p.getPrice());
            productDAO.decrementStock(p.getId(), e.getValue());
        }
        return orderId;
    }

    public void markPaid(int orderId) throws Exception {
        orderDAO.updateStatus(orderId, "PAID");
    }
}
