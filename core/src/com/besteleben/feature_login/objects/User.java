package com.besteleben.feature_login.objects;

import java.time.LocalDate;

public class User {
    private static User user;
    private int id;
    private String username;

    private User() {
    }

    public static User getInstance(){
        if(user == null){
            user = new User();
        }
        return user;
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
     * Gets id.
     *
     * @return value of id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id value of id
     */
    public void setId(int id) {
        if(this.id == 0) {
            this.id = id;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
