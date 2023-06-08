package com.besteleben.feature_login.service;

import com.besteleben.feature_login.exceptions.LoginCredentialsWrongException;
import com.besteleben.feature_login.exceptions.UserNotFoundException;
import com.besteleben.feature_login.objects.User;
import com.besteleben.feature_login.objects.UserData;
import com.besteleben.feature_login.repository.LoginRepository;
import com.besteleben.feature_login.service.utils.PasswordUtils;

public class LoginService {
    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

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
    public boolean checkUsername(String userName) {
        return loginRepository.checkUserName(userName);
    }

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
