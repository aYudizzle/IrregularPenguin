package com.besteleben.feature_login.controller;

import com.besteleben.feature_login.exceptions.LoginCredentialsWrongException;
import com.besteleben.feature_login.exceptions.UserNotFoundException;
import com.besteleben.feature_login.repository.LoginRepository;
import com.besteleben.feature_login.service.LoginService;

/**
 * Controller for the Login
 * Mediator between frontend and service.
 */
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public void login(String username, String password) throws UserNotFoundException, LoginCredentialsWrongException {
        loginService.authenticateUser(username,password);
    }

    public boolean checkUserName(String userName) {
        return loginService.checkUsername(userName);
    }

    public boolean registerUser(String username, String password) {
        boolean registrationSuccess = loginService.registerUser(username,password);
        return registrationSuccess;
    }
}
