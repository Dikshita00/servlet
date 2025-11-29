package com.demo.service;

import com.demo.dao.PaymentDAO;

import java.math.BigDecimal;
import java.util.UUID;

public class PaymentService {
    private final PaymentDAO paymentDAO = new PaymentDAO();

    public int initiate(int orderId, BigDecimal amount, String method) throws Exception {
        String ref = "TXN-" + UUID.randomUUID();
        return paymentDAO.create(orderId, amount, method, "INITIATED", ref);
    }

    public void succeed(int paymentId, String ref) throws Exception {
        paymentDAO.updateStatus(paymentId, "SUCCESS", ref);
    }

    public void fail(int paymentId, String ref) throws Exception {
        paymentDAO.updateStatus(paymentId, "FAILED", ref);
    }
}
