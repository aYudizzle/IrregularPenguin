package com.besteleben.feature_login.objects;

import java.time.LocalDate;

public class UserData {
    private int id;
    private String username;
    private String password;
    private String salt;
    private LocalDate created_at;

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

    /**
     * Gets created_at.
     *
     * @return value of created_at
     */
    public LocalDate getCreated_at() {
        return created_at;
    }
}
