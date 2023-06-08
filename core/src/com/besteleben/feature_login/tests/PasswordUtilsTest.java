package com.besteleben.feature_login.tests;

import com.besteleben.feature_login.repository.LoginRepositoryImpl;
import com.besteleben.feature_login.service.LoginService;
import com.besteleben.feature_login.service.utils.PasswordUtils;

public class PasswordUtilsTest {
    public static void main(String[] args) {
//        String salt = PasswordUtils.generateSalt();
        //Simulierte user inputs
        String password = "Alfatraining1";
        String username = "Emily";
//        String hashedPassword = PasswordUtils.hashPassword(password, salt);
//        System.out.println("Salt: " + salt);
//        System.out.println("Hashed Password: " + hashedPassword);

        String salt2 = PasswordUtils.generateSalt();
        System.out.println(salt2);

        LoginService loginService = new LoginService(new LoginRepositoryImpl());
//        System.out.println(loginService.authenticateUser(username,password));


    }
}
