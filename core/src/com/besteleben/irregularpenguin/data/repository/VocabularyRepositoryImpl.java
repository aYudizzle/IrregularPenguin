package com.besteleben.irregularpenguin.data.repository;

import com.besteleben.irregularpenguin.data.objects.Vocabulary;

import java.sql.*;
/**
 * Implementierung des Vocabulary Dao anhand einer Maria DB. Diese implementierung benutzt den Treiber der Maria db.
 */

public class VocabularyRepositoryImpl implements VocabularyRepository {
    /**
     * die Datenbank Adresse mit Auswahl des richtigen Treibers
     */
    private static final String DB_URL = "jdbc:mariadb://mj13.serverdomain.org:3306/wa3454_db3";
    /** Username für den Login in die DB */
    private static final String USERNAME = "wa3454_3";
    /** passwort für die datenbank verbindung */
    private static final String PASSWORD = "Alfatraining1!";

    /**
     * Auslesen eines random Datensatzen aus der Vokabel Datenbank.
     * @return gibt die zufaellig ausgewaehlte Vokabel zurueck.
     */
    @Override
    public Vocabulary getRandomVocabulary() {
        String sql = "SELECT * FROM vocabularies ORDER BY RAND() LIMIT 1";
        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            if(resultSet.next()) {
                Vocabulary vocabularyDTO = new Vocabulary(resultSet.getInt("id"),
                        resultSet.getString("german"),
                        resultSet.getString("infinitive"),
                        resultSet.getString("simple_past"),
                        resultSet.getString("past_participle"));
                return vocabularyDTO;
            }
        } catch (SQLException exception) {
            System.out.println("Keine Datenverbindung möglich");
        }
        return null;
    }
}
