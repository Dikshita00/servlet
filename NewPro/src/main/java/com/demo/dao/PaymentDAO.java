package com.demo.dao;

import com.demo.util.DBUtil;

import java.sql.*;
import java.math.BigDecimal;

public class PaymentDAO {
    public int create(int orderId, BigDecimal amount, String method, String status, String txnRef) throws SQLException {
        String sql = "INSERT INTO payments (order_id, amount, method, status, txn_ref) VALUES (?,?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, orderId);
            ps.setBigDecimal(2, amount);
            ps.setString(3, method);
            ps.setString(4, status);
            ps.setString(5, txnRef);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    public void updateStatus(int paymentId, String status, String txnRef) throws SQLException {
        String sql = "UPDATE payments SET status = ?, txn_ref = ? WHERE id = ?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setString(2, txnRef);
            ps.setInt(3, paymentId);
            ps.executeUpdate();
        }
    }
}
