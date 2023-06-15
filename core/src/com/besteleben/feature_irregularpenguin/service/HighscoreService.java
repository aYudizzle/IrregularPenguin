package com.besteleben.feature_irregularpenguin.service;

import com.besteleben.feature_irregularpenguin.data.objects.HighscoreEntry;
import com.besteleben.feature_irregularpenguin.data.repository.HighscoreRepository;

import java.util.Collections;
import java.util.List;

/**
 * Service to save, load and manage the highscore
 */
public class HighscoreService {
    /**
     * dataSource of the highscore
     */
    private HighscoreRepository dataSource;
    /**
     * list of scores representing the highscore list
     */
    private List<HighscoreEntry> highscoreList;
    /**
     * max players wanted in list
     */
    private static final int MAX_ENTRIES = 10;

    /**
     * constructor to create the highscore Service
     *
     * @param dataSource the given datasource for the service
     */
    public HighscoreService(HighscoreRepository dataSource) {
        this.dataSource = dataSource;
        highscoreList = dataSource.loadHighscore();
        highscoreList.sort(Collections.reverseOrder());
    }

    /**
     * method to add a Highscoreentry
     *
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
        if (highscoreList.size() > MAX_ENTRIES) {
            highscoreList.remove(MAX_ENTRIES);
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
