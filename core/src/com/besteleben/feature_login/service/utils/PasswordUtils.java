package com.besteleben.feature_login.service.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {
    private static final String HASHING_ALGORITHM = "SHA-256";
    private static final int SALT_LENGTH = 16;

    public static String hashPassword(String password, String salt) {
        String hashedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
            messageDigest.update(salt.getBytes());
            byte[] hashedBytes = messageDigest.digest(password.getBytes());
            hashedPassword = Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
        }
        return hashedPassword;
    }


    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[SALT_LENGTH];
        random.nextBytes(saltBytes);
        //    Rückgabewerts beträgt 24 Zeichen, da Base64-Encoding verwendet wird, um den generierten Salt-Wert in einen String umzuwandeln
        return Base64.getEncoder().encodeToString(saltBytes);
    }
}
