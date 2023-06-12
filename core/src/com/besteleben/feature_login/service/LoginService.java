package com.besteleben.feature_login.service;

import com.besteleben.feature_login.exceptions.LoginCredentialsWrongException;
import com.besteleben.feature_login.exceptions.UserNotFoundException;
import com.besteleben.feature_login.objects.User;
import com.besteleben.feature_login.objects.UserData;
import com.besteleben.feature_login.repository.LoginRepository;
import com.besteleben.feature_login.service.utils.PasswordUtils;

/**
 * Service to handle the business logic of the login screen
 */
public class LoginService {
    /**
     * the data source of the login service to verify user inputs
     */
    private final LoginRepository loginRepository;

    /**
     * constructor with the given login repository
     * @param loginRepository the repository to work with
     */
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    /**
     * method to authenticate the user
     * @param username username of the userinput
     * @param password password of the userinput
     * @throws UserNotFoundException if the user does not exist the exception gets thrown
     * @throws LoginCredentialsWrongException if the password is wrong this exception is getting thrown
     */
    public void authenticateUser(String username, String password) throws UserNotFoundException, LoginCredentialsWrongException {
        UserData userData = loginRepository.getUserByUsername(username);
        if (userData != null) {
            String salt = userData.getSalt();
            String hashedPassword = PasswordUtils.hashPassword(password, salt);
            boolean loginCorrect = hashedPassword.equals(userData.getPassword());
            if (loginCorrect) {
                User user = User.getInstance();
                user.setId(userData.getId());
                user.setUsername(userData.getUsername());
                System.out.println(user);
            } else {
                throw new LoginCredentialsWrongException();
            }
        }
    }

    /**
     * checks if a username is existent
     * @param username the username to check
     * @return true of false depending on the usernames existence
     */
    public boolean checkUsername(String username) {
        return loginRepository.checkUserName(username);
    }

    /**
     * method to register a new user
     * @param username username to register
     * @param password chosen password
     * @return true if the registration succeeded and false if its failed
     */
    public boolean registerUser(String username, String password) {
        if(password.length()<8){
            return false;
        }
        if(username.length()<3){
            return false;
        }
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(password, salt);

        return loginRepository.registerUser(username,hashedPassword, salt);
    }
}
