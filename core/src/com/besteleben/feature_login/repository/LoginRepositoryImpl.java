package com.besteleben.feature_login.repository;

import com.besteleben.feature_login.exceptions.UserNotFoundException;
import com.besteleben.feature_login.objects.UserData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;

/**
 * This is an implementation of the LoginRepository Interface
 * using a maria db
 */
public class LoginRepositoryImpl implements LoginRepository {
    /**
     * URL to the database
     */
    private static final String DB_URL = "jdbc:mariadb://mj13.serverdomain.org:3306/wa3454_db3";
    /**
     * Username für den Login in die DB
     */
    private static final String USERNAME = "wa3454_3";
    /**
     * passwort für die datenbank verbindung
     */
    private static final String PASSWORD = "Alfatraining1!";
    /**
     * Logger for logging errors in a logfile
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginRepositoryImpl.class);;

    /**
     * gets the user by username
     *
     * @param username username to look up
     * @return the user data if the user exists.
     * @throws UserNotFoundException if the user does not exist this exception will be thrown
     */
    @Override
    public UserData getUserByUsername(String username) throws UserNotFoundException {
        String sql = "SELECT * FROM users WHERE `username` = ?";
        UserData user = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new UserData(
                            resultSet.getInt("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("salt"),
                            resultSet.getDate("created_at").toLocalDate());
                }
            }
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
        if (user == null) {
            throw new UserNotFoundException("User name not found.");
        }
        return user;
    }

    /**
     * checks if a username is already in use or still available to register
     *
     * @param username username to lookup
     * @return true if the user is not in use and still available and false if its already registered
     */
    @Override
    public boolean checkUserName(String username) {
        String sql = "SELECT * FROM users WHERE `username` = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next();
            }
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
        return false;
    }

    /**
     * is called when a new user wants to register his/herself.
     *
     * @param username user with username to insert into database
     * @param password password with hashedpassword to insert into database
     * @param salt     used salt to hash the password
     * @return if the registration to the database was successful return true
     */
    @Override
    public boolean registerUser(String username, String password, String salt) {
        String sql = "INSERT INTO users Values(null,?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, salt);
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.execute();
            return true;
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
        return true;
    }
}
