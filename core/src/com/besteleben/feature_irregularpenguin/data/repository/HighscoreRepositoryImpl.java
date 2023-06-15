package com.besteleben.feature_irregularpenguin.data.repository;

import com.besteleben.feature_irregularpenguin.data.objects.HighscoreEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * saves the highscore in a local file highscore.dat
 */
public class HighscoreRepositoryImpl implements HighscoreRepository {
    /**
     * die Datenbank Adresse mit Auswahl des richtigen Treibers
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
    private static final Logger LOGGER = LoggerFactory.getLogger(HighscoreRepositoryImpl.class);
    /**
     * load highscoreList from backend
     *
     * @return the highscore list with several entries
     */
    @Override
    public List<HighscoreEntry> loadHighscore() {
        String query = "SELECT * FROM highscore";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            List<HighscoreEntry> highscoreEntryList = new ArrayList<>();
            while (resultSet.next()) {
                HighscoreEntry entry = new HighscoreEntry(
                        resultSet.getString("playername"),
                        resultSet.getInt("playerscore")
                );
                int id = resultSet.getInt("id");
                entry.setId(id);
                highscoreEntryList.add(entry);
            }
            return highscoreEntryList;
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
        return new ArrayList<>();
    }

    /**
     * save the highscore list
     *
     * @param highscoreList represents the highscore List
     */
    @Override
    public void saveHighscore(List<HighscoreEntry> highscoreList) {
        String sql = "UPDATE highscore SET playername = ?, playerscore = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < highscoreList.size(); i++) {
                statement.setString(1, highscoreList.get(i).getPlayerName());
                statement.setInt(2, highscoreList.get(i).getPlayerScore());
                statement.setInt(3, i + 1);
                statement.addBatch();
            }
            statement.executeBatch();
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
    }
}


