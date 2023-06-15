package com.besteleben.feature_irregularpenguin.data.repository;

import com.besteleben.feature_irregularpenguin.data.objects.HighscoreEntry;

import java.util.List;

/**
 * Interface to implement access to write the highscore into the persistence layer
 */
public interface HighscoreRepository {
    /**
     * load highscoreList from backend
     *
     * @return the highscore list with several entries
     */
    List<HighscoreEntry> loadHighscore();

    /**
     * save the highscore list
     *
     * @param highscoreList the list of highsccore entries to save
     */
    void saveHighscore(List<HighscoreEntry> highscoreList);
}
