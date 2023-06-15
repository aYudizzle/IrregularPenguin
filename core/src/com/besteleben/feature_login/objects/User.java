package com.besteleben.feature_login.objects;


/**
 * User as singleton
 * this keeps the needed user data if the user successfully logged in.
 */
public class User {
    /**
     * reference of the user object - needed for the Singleton pattern
     */
    private static User user;
    /**
     * id of the user
     */
    private int id;
    /**
     * username of the user
     */
    private String username;

    /**
     * private constructor, so the user can not be instanced multiple times
     */
    private User() {
    }

    /**
     * method to get the single instance of the user Object, if it is not existence, then it will be created
     *
     * @return the instance of the user object
     */
    public static User getInstance() {
        if (user == null) {
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
        if (this.id == 0) {
            this.id = id;
        }
    }

    /**
     * mainly used for debugging purposes to print the object readable
     *
     * @return user object as string
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
