package com.besteleben.feature_login.repository;

import com.besteleben.feature_login.exceptions.UserNotFoundException;
import com.besteleben.feature_login.objects.UserData;

/**
 * an interface for a repository implementation
 */
public interface LoginRepository {
    /**
     * gets the user by username
     *
     * @param username username to look up
     * @return the user data if the user exists.
     * @throws UserNotFoundException if the user does not exist this exception will be thrown
     */
    UserData getUserByUsername(String username) throws UserNotFoundException;

    /**
     * is called when a new user wants to register his/herself.
     *
     * @param username user with username to insert into repository
     * @param password password with hashedpassword to insert into repository
     * @param salt     used salt to hash the password
     * @return if the registration to the repository was successful return true
     */
    boolean registerUser(String username, String password, String salt);

    /**
     * checks if a username is already in use
     *
     * @param username username to lookup
     * @return true if the user is not in use and still available and false if its already registered
     */
    boolean checkUserName(String username);
}
