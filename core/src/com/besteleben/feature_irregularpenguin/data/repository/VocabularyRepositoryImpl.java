package com.besteleben.feature_irregularpenguin.data.repository;

import com.besteleben.feature_irregularpenguin.data.objects.Vocabulary;
import com.besteleben.feature_irregularpenguin.data.objects.WrongVocabulariesEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementierung des Vocabulary Dao anhand einer Maria DB. Diese implementierung benutzt den Treiber der Maria db.
 */

public class VocabularyRepositoryImpl implements VocabularyRepository {
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
    private static final Logger LOGGER = LoggerFactory.getLogger(VocabularyRepositoryImpl.class);

    /**
     * Auslesen eines random Datensatzen aus der Vokabel Datenbank.
     *
     * @return gibt die zufaellig ausgewaehlte Vokabel zurueck.
     */
    @Override
    public Vocabulary getRandomVocabulary() {
        String query = "SELECT * FROM vocabularies ORDER BY RAND() LIMIT 1";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                Vocabulary vocabulary = new Vocabulary(resultSet.getInt("id"),
                        resultSet.getString("german"),
                        resultSet.getString("infinitive"),
                        resultSet.getString("simple_past"),
                        resultSet.getString("past_participle"));
                return vocabulary;
            }
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
        return null;
    }

    /**
     * save a wrong given answer, so it can be reasked after a certain time
     *
     * @param userId       id from the user who answered the vocabulary wrong
     * @param vocabularyId id from vocabulary which got answered wrong
     */
    @Override
    public void saveWrongAnsweredVocabulary(int userId, int vocabularyId) {
        String selectQuery = "SELECT COUNT(*) FROM wrong_answers WHERE vocabularyId = ? AND userId = ?";
        String insertQuery = "INSERT INTO wrong_answers (vocabularyId, userId, lastIncorrectAnswerTime, correctAnswers) VALUES (?, ?, ?,0)";
        String updateQuery = "UPDATE wrong_answers SET lastIncorrectAnswerTime = ?, correctAnswers = 0 WHERE vocabularyId = ? AND userId = ?";

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
             PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {

            selectStatement.setInt(1, vocabularyId);
            selectStatement.setInt(2, userId);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        updateStatement.setDate(1, Date.valueOf(LocalDate.now()));
                        updateStatement.setInt(2, vocabularyId);
                        updateStatement.setInt(3, userId);
                        updateStatement.executeUpdate();
                    } else {
                        insertStatement.setInt(1, vocabularyId);
                        insertStatement.setInt(2, userId);
                        insertStatement.setDate(3, Date.valueOf(LocalDate.now()));
                        insertStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
    }

    /**
     * list of all wrong answered vocabularies by the user
     *
     * @param userId the id it should get searched for.
     * @return a list of Entries or an empty list when no entries exist
     */
    @Override
    public List<WrongVocabulariesEntry> getWrongAnsweredVocabularies(int userId) {
        String query = "SELECT * FROM wrong_answers WHERE userId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                List<WrongVocabulariesEntry> wrongEntries = new ArrayList<>();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int userIdColumn = resultSet.getInt("userId");
                    int vocabularyId = resultSet.getInt("vocabularyId");
                    LocalDate dateOfWrongAnswer = resultSet.getDate("lastIncorrectAnswerTime").toLocalDate();
                    int countRightAnswers = resultSet.getInt("correctAnswers");

                    WrongVocabulariesEntry entry = new WrongVocabulariesEntry(id, userIdColumn, vocabularyId, dateOfWrongAnswer, countRightAnswers);
                    wrongEntries.add(entry);
                }

                return wrongEntries;
            }
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
        return new ArrayList<>();
    }

    /**
     * look up for a vocabulary by id
     *
     * @param idToLookUp the id to lookup
     * @return the vocabulary
     */
    @Override
    public Vocabulary getVocabularyById(int idToLookUp) {
        String query = "SELECT * FROM vocabularies WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idToLookUp);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Vocabulary vocabulary = new Vocabulary(resultSet.getInt("id"),
                            resultSet.getString("german"),
                            resultSet.getString("infinitive"),
                            resultSet.getString("simple_past"),
                            resultSet.getString("past_participle"));
                    return vocabulary;
                }
            }
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
        return null;
    }

    /**
     * Update the wrong vocabulary
     *
     * @param userId       the user's id
     * @param vocabularyId the vocabularies id
     * @param rightAnswers counter of given correct answers
     */
    @Override
    public void updateWrongAnsweredVocabulary(int userId, int vocabularyId, int rightAnswers) {
        String updateQuery = "UPDATE wrong_answers SET lastIncorrectAnswerTime = ?, correctAnswers = ? WHERE vocabularyId = ? AND userId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
            updateStatement.setDate(1, Date.valueOf(LocalDate.now()));
            updateStatement.setInt(2, rightAnswers);
            updateStatement.setInt(3, vocabularyId);
            updateStatement.setInt(4, userId);
            updateStatement.executeUpdate();
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
    }

    /**
     * deletes the wrong answered vocabulary
     *
     * @param answerId id of the wrong answered vocabulary to delete
     */
    @Override
    public void deleteWrongAnsweredVocabulary(int answerId) {
        String sql = "DELETE FROM `wrong_answers` WHERE `id` = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, answerId);
            statement.execute();
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
    }

    /**
     * gets a wrong answered vocabulary by user id and vocabularyId;
     *
     * @param userId       the users id
     * @param vocabularyId the vocabulary id
     */
    @Override
    public WrongVocabulariesEntry getWrongAnsweredVocabularyByIds(int userId, int vocabularyId) {
        String query = "SELECT * FROM wrong_answers WHERE vocabularyId = ? AND userId = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, vocabularyId);
            statement.setInt(2, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    WrongVocabulariesEntry entry = new WrongVocabulariesEntry(
                            resultSet.getInt("id"),
                            resultSet.getInt("userId"),
                            resultSet.getInt("vocabularyId"),
                            resultSet.getDate("lastIncorrectAnswerTime").toLocalDate(),
                            resultSet.getInt("correctAnswers"));
                    return entry;
                }
            }
        } catch (SQLException exception) {
            LOGGER.error("ERROR:", exception);
        }
        return null;
    }
}