package com.besteleben.feature_login.service.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * PasswordUtils is a Utility class for password hashing.
 * The password gets hashed with the "SHA-256" algorithm and with the use
 * of a certain salt.
 */
public class PasswordUtils {
    /**
     * the wanted hashing algorithm
     */
    private static final String HASHING_ALGORITHM = "SHA-256";
    /**
     * length of the salt to get the password more secure
     */
    private static final int SALT_LENGTH = 16;

    /**
     * private constructor because the class is a pure utility class and should not be instanced
     */
    private PasswordUtils() {}

    /**
     * method to hash the password with the chosen algorithm
     * @param password password string to hash
     * @param salt salt to use for hashing
     * @return hashed password
     */
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

    /**
     * a method to generate a random salt.
     * @return the generated salt
     */
    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[SALT_LENGTH];
        random.nextBytes(saltBytes);
        //    Rückgabewerts beträgt 24 Zeichen, da Base64-Encoding verwendet wird, um den generierten Salt-Wert in einen String umzuwandeln
        return Base64.getEncoder().encodeToString(saltBytes);
    }
}
