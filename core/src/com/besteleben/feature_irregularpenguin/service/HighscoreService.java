package com.besteleben.feature_irregularpenguin.service;

import com.besteleben.feature_irregularpenguin.data.repository.HighscoreRepository;
import com.besteleben.feature_irregularpenguin.data.objects.HighscoreEntry;

import java.util.Collections;
import java.util.List;

/**
 * Service to save, load and manage the highscore
 */
public class HighscoreService {
    /**
     * dataSource of the highscore
     */
    private final HighscoreRepository dataSource;
    /**
     * list of scores representing the highscore list
     */
    private List<HighscoreEntry> highscoreList;
    /**
     * max players wanted in list
     */
    private final int maxEntries = 10;
    /**
     * constructor to create the highscore Service
     * @param dataSource the given datasource for the service
     */
    public HighscoreService(HighscoreRepository dataSource) {
        this.dataSource = dataSource;
        highscoreList = dataSource.loadHighscore();
        highscoreList.sort(Collections.reverseOrder());
    }
    /**
     * method to add a Highscoreentry
     * @param entry the highscore entry to add to the list
     */
    public void addHighscoreEntry(HighscoreEntry entry) {
        highscoreList = dataSource.loadHighscore();
        highscoreList.add(entry);
        highscoreList.sort(Collections.reverseOrder());
        trimHighscore();
        dataSource.saveHighscore(highscoreList);
    }

    /**
     * if the highscore list would have 11 entries -> shorten it and let the lowest score drop out of the list
     * remove the last index
     */
    private void trimHighscore() {
        if (highscoreList.size() > maxEntries) {
            highscoreList.remove(maxEntries);
        }
    }

    /**
     * Gets highscore.
     *
     * @return value of highscore
     */
    public List<HighscoreEntry> getHighscoreList() {
        highscoreList = dataSource.loadHighscore();
        return highscoreList;
    }
}
