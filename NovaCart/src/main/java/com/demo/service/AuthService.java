package com.demo.service;

import com.demo.bean.User;
import com.demo.dao.UserDAO;
import com.demo.util.PasswordUtil;

public class AuthService {
    private final UserDAO userDAO = new UserDAO();

    // Registration: generate salt + hash, store user
    public User register(String name, String email, String gender, String hobbies, String city, String rawPassword) throws Exception {
        if (userDAO.emailExists(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hash(rawPassword, salt);

        User u = new User();
        u.setName(name);
        u.setEmail(email);
        u.setGender(gender);
        u.setHobbies(hobbies);
        u.setCity(city);
        u.setPasswordSalt(salt);
        u.setPasswordHash(hash);

        int id = userDAO.create(u);   // must insert both hash + salt
        u.setId(id);
        return u;
    }

    // Login: verify salted hash
    public User login(String email, String rawPassword) throws Exception {
        User u = userDAO.findByEmail(email);
        if (u == null) return null;

        boolean ok = PasswordUtil.verify(rawPassword, u.getPasswordSalt(), u.getPasswordHash());
        return ok ? u : null;
    }

    // Reset password: generate new salt + hash
    public boolean resetPassword(String email, String newRaw) throws Exception {
        User u = userDAO.findByEmail(email);
        if (u == null) return false;

        String salt = PasswordUtil.generateSalt();
        String hash = PasswordUtil.hash(newRaw, salt);

        userDAO.updatePassword(u.getId(), hash, salt);
        return true;
    }
}
