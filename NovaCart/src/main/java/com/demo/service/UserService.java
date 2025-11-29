package com.demo.service;

import com.demo.bean.User;
import com.demo.dao.UserDAO;

import java.sql.SQLException;

public class UserService {
    private final UserDAO userDAO = new UserDAO();

    // Find user by email
    public User getUserByEmail(String email) throws SQLException {
        return userDAO.findByEmail(email);
    }

    // Register a new user (already handled in AuthService, but can be reused here)
    public int addUser(User user) throws SQLException {
        return userDAO.create(user);
    }

    // Check if email exists
    public boolean emailExists(String email) throws SQLException {
        return userDAO.emailExists(email);
    }

    // Update password (used by ForgotPasswordServlet)
    public void updatePassword(int userId, String hash, String salt) throws SQLException {
        userDAO.updatePassword(userId, hash, salt);
    }

    // Example: update profile (name, gender, hobbies, city)
    public void updateProfile(User user) throws SQLException {
        String sql = "UPDATE users SET name=?, gender=?, hobbies=?, city=? WHERE id=?";
        try (var con = com.demo.util.DBUtil.getConnection();
             var ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getGender());
            ps.setString(3, user.getHobbies());
            ps.setString(4, user.getCity());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
        }
    }
}
