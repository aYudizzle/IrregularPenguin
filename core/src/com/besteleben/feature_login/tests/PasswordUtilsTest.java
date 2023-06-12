package com.besteleben.feature_login.tests;

import com.besteleben.feature_login.service.utils.PasswordUtils;

/**
 * Test Class for PasswordUtils
 */
public class PasswordUtilsTest {
    /**
     * to start the tests for simple testing purposes
     * @param args not used in this case
     */
    public static void main(String[] args) {
        String salt = PasswordUtils.generateSalt();
        //Simulierte user inputs
        String password = "alfa";
        String username = "alfa1";
        String hashedPassword = PasswordUtils.hashPassword(password, salt);
        System.out.println("Salt: " + salt);
        System.out.println("Hashed Password: " + hashedPassword);

//        String salt2 = PasswordUtils.generateSalt();
//        System.out.println(salt2);

//        LoginService loginService = new LoginService(new LoginRepositoryImpl());
//        System.out.println(loginService.authenticateUser(username,password));
    }
}
