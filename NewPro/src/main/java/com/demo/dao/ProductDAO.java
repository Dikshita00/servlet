package com.demo.dao;

import com.demo.bean.Product;
import com.demo.util.DBUtil;
import java.sql.*;
import java.util.*;
import java.math.BigDecimal;

public class ProductDAO {

    // List all products
    public List<Product> listAll() throws SQLException {
        String sql = "SELECT * FROM products";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            List<Product> list = new ArrayList<>();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("ProductID"));                //  matches table
                p.setName(rs.getString("ProductName"));         //  matches table
                p.setDescription(rs.getString("QuantityPerUnit")); //  use as description
                p.setPrice(rs.getBigDecimal("UnitPrice"));      //  matches table
                p.setStock(rs.getInt("UnitsInStock"));          //  matches table
                list.add(p);
            }
            return list;
        }
    }

    // Find product by ID
    public Product findById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE ProductID = ?"; // use ProductID
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.setId(rs.getInt("ProductID"));                
                    p.setName(rs.getString("ProductName"));         
                    p.setDescription(rs.getString("QuantityPerUnit")); 
                    p.setPrice(rs.getBigDecimal("UnitPrice"));      
                    p.setStock(rs.getInt("UnitsInStock"));          
                    return p;
                }
                return null;
            }
        }
    }

    // Decrement stock
    public void decrementStock(int id, int qty) throws SQLException {
        String sql = "UPDATE products SET UnitsInStock = UnitsInStock - ? " +
                     "WHERE ProductID = ? AND UnitsInStock >= ?";   //  use UnitsInStock + ProductID
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qty);
            ps.setInt(2, id);
            ps.setInt(3, qty);
            ps.executeUpdate();
        }
    }
}
