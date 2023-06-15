package com.besteleben.feature_login.controller;

import com.besteleben.feature_login.exceptions.LoginCredentialsWrongException;
import com.besteleben.feature_login.exceptions.UserNotFoundException;
import com.besteleben.feature_login.service.LoginService;

/**
 * Controller for the Login
 * Mediator between frontend and service.
 */
public class LoginController {
    /**
     * the login service for the business logic
     */
    private LoginService loginService;

    /**
     * Constructor for the controller with the reference to the loginservice
     *
     * @param loginService reference to the service
     */
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * method should get called to fire off the login event and authenticate user data's
     *
     * @param username given username
     * @param password given password
     * @throws UserNotFoundException          exception when the username does not exist
     * @throws LoginCredentialsWrongException exception when the user enters wrong login credentials
     */
    public void login(String username, String password) throws UserNotFoundException, LoginCredentialsWrongException {
        loginService.authenticateUser(username, password);
    }

    /**
     * checks if the username is existent or non-existent
     *
     * @param userName username to lookup
     * @return true if the username is not existent and still available
     */
    public boolean checkUserName(String userName) {
        return loginService.checkUsername(userName);
    }

    /**
     * method to fire off the registration process
     *
     * @param username username chosen by the user
     * @param password password chosen by the user
     * @return true if the registration is successful
     */
    public boolean registerUser(String username, String password) {
        boolean registrationSuccess = loginService.registerUser(username, password);
        return registrationSuccess;
    }
}
