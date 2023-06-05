package com.besteleben.irregularpenguin.data.repository;

import com.besteleben.irregularpenguin.data.objects.HighscoreEntry;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * saves the highscore in a local file highscore.dat
 */
public class HighscoreRepositoryImpl implements HighscoreRepository {
    /**
     * Filename to load
     */
    private final String highscorePath = "highscore/highscore.dat";
    /**
     * load data from backend
     *
     * @return the highscore list with several entries
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HighscoreEntry> loadHighscore() {
        File highscoreFile = new File(highscorePath);
        if(!highscoreFile.exists()){
            return new ArrayList<>();
        }
        List<HighscoreEntry> loadedHighscore = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(highscorePath))) {
            Object test = inputStream.readObject();
            loadedHighscore = (List<HighscoreEntry>) test;

        } catch (IOException | ClassNotFoundException exception) {
//            System.err.println("Can't access file!");
            exception.printStackTrace();
        }
        return loadedHighscore;
    }

    /**
     * save the highscore list
     *
     * @param highscoreList represents the highscore List
     */
    @Override
    public void saveHighscore(List<HighscoreEntry> highscoreList) {
        try(ObjectOutputStream outputStream = new ObjectOutputStream( new FileOutputStream(highscorePath))){
            outputStream.writeObject(highscoreList);
        } catch(IOException exception){
            System.err.println("Fehler beim speichern der Liste");
//            exception.printStackTrace();
        }
    }
}
