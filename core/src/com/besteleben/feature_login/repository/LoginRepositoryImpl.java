package com.besteleben.feature_login.repository;

import com.besteleben.feature_login.exceptions.UserNotFoundException;
import com.besteleben.feature_login.objects.UserData;

import java.sql.*;
import java.time.LocalDate;

public class LoginRepositoryImpl implements LoginRepository {

    private static final String DB_URL = "jdbc:mariadb://mj13.serverdomain.org:3306/wa3454_db3";
    /**
     * Username für den Login in die DB
     */
    private static final String USERNAME = "wa3454_3";
    /**
     * passwort für die datenbank verbindung
     */
    private static final String PASSWORD = "Alfatraining1!";

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
            System.err.println("Datenbank nicht erreichbar");
        }
        if (user == null) {
            throw new UserNotFoundException("User name not found.");
        }
        return user;
    }

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
            System.out.println("Datenbank nicht erreichbar");
        }
        return false;
    }

    /**
     * is called when a new user wants to register his/herself.
     *
     * @param username user with username to insert into database
     * @param password password with hashedpassword to insert into database
     * @param salt used salt to hash the password
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
            System.out.println("Adding new user not successful");
        }
        return true;
    }
}