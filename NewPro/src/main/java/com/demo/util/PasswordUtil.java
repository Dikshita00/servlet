package com.demo.util;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtil {
    private static final SecureRandom RAND = new SecureRandom();

    // Generate a random salt (Base64 encoded)
    public static String generateSalt() {
        byte[] salt = new byte[16];
        RAND.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hash password with salt using SHA-256
    public static String hash(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes()); // add salt
            byte[] hashed = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashed);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    // Verify raw password against stored hash
    public static boolean verify(String raw, String salt, String storedHash) {
        String newHash = hash(raw, salt);
        return newHash.equals(storedHash);
    }
}
