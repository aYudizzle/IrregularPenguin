package com.besteleben.feature_login.repository;

import com.besteleben.feature_login.exceptions.UserNotFoundException;
import com.besteleben.feature_login.objects.UserData;

public interface LoginRepository {
    UserData getUserByUsername(String username) throws UserNotFoundException;
    /**
     * is called when a new user wants to register his/herself.
     *
     * @param username user with username to insert into repository
     * @param password password with hashedpassword to insert into repository
     * @param salt used salt to hash the password
     * @return if the registration to the repository was successful return true
     */
    boolean registerUser(String username, String password, String salt);

    boolean checkUserName(String userName);
}
