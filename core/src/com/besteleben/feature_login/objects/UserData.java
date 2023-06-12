package com.besteleben.feature_login.objects;

import java.time.LocalDate;

/**
 * An UserData Object for transferring the user data from one layer to the other one.
 */
public class UserData {
    /**
     * id of the user
     */
    private int id;
    /**
     * username of the user
     */
    private String username;
    /**
     * hashed password of the user
     */
    private String password;
    /**
     * salt used for hashing the password
     */
    private String salt;
    /**
     * registration date of the user
     */
    private LocalDate created_at;

    /**
     * Constructor of the user object
     * @param id the id of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param salt the used salt for hashing the password
     * @param created_at creation date of the user
     */
    public UserData(int id, String username, String password, String salt, LocalDate created_at) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.created_at = created_at;
    }

    /**
     * Gets username.
     *
     * @return value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets salt.
     *
     * @return value of salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Gets id.
     *
     * @return value of id
     */
    public int getId() {
        return id;
    }

}
