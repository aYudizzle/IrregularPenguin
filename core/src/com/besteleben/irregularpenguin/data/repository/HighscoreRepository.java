package com.besteleben.irregularpenguin.data.repository;

import com.besteleben.irregularpenguin.data.objects.HighscoreEntry;

import java.util.List;

/**
 * Interface to implement access to write the highscore into the persistence layer
 */
public interface HighscoreRepository {
    /**
     * load data from backend
     * @return the highscore list with several entries
     */
    List<HighscoreEntry> loadHighscore();

    /**
     * save the highscore list
     * @param highscoreList the list of highsccore entries to save
     */
    void saveHighscore(List<HighscoreEntry> highscoreList);
}
