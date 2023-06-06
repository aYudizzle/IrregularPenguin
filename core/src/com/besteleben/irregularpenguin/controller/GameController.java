package com.besteleben.irregularpenguin.controller;

import com.badlogic.gdx.utils.Timer;

import com.besteleben.irregularpenguin.data.objects.HighscoreEntry;
import com.besteleben.irregularpenguin.data.objects.QuestionerData;
import com.besteleben.irregularpenguin.entities.Player;
import com.besteleben.irregularpenguin.screen.gamescreen.GameStage;
import com.besteleben.irregularpenguin.service.HighscoreService;
import com.besteleben.irregularpenguin.service.SettingsService;
import com.besteleben.irregularpenguin.service.VocabularyService;

import java.util.List;

/**
 * Mediator between frontend and services
 * connects with the different service and communicates with the
 * stage.
 */
public class GameController {
    /**
     * reference to the GameStage, to communicate with it
     */
    private final GameStage stage;
    /**
     * player for the game
     */
    private final Player player;
    /**
     * Service and connection to the business logic
     */
    private final VocabularyService vocabularyService;
    /**
     * Service for the Highscore
     */
    private final HighscoreService highscoreService;
    /**
     * Service for the player configuration
     */
    private final SettingsService settingsService;
    /**
     * Question Data which is needed to ask for the answer
     */
    public QuestionerData questionerData;


    /**
     * constructing the mediator for communication between service and frontend
     * @param highscoreService highscore service for manage the highscore
     * @param settingsService for managing game settings
     * @param stage frontend reference
     * @param vocabularyService for managing the vocabulary/question
     */
    public GameController(GameStage stage, SettingsService settingsService, HighscoreService highscoreService, VocabularyService vocabularyService) {
        this.stage = stage;
        this.vocabularyService = vocabularyService;
        this.highscoreService = highscoreService;
        this.settingsService = settingsService;
        player = new Player(this.settingsService.getConfig().getPlayerName());
    }
    /**
     * get called when the user input needs to be validated by the service
     * if the answer is correct it calls the nextRound method after a short delay
     * @param userInput the answer given by the user
     */
    public void handleUserInput(String userInput) {
        boolean answerCorrect = vocabularyService.checkAnswer(userInput);
        if (!answerCorrect) {
            player.decreaseLife();
            if (player.getLife() == 0) {
                gameOver();
            }
        }
        stage.reactionToAnswer(answerCorrect, player.getLife());
        if (answerCorrect) {
            player.increaseScore();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    nextRound();
                }
            }, 3f);
        }
    }
    /**
     * method for the interaction when the game is over
     */
    private void gameOver() {
        HighscoreEntry playerEntry = new HighscoreEntry(player.getPlayerName(), player.getHighscore());
        highscoreService.addHighscoreEntry(playerEntry);
        showHighscore();
    }
    /**
     * method gets call when the answer is correct and the
     * next round shall start.
     */
    public void nextRound() {
        questionerData = vocabularyService.generateNextQuestion();
        stage.prepareRound(questionerData, player.getLife(),player.getHighscore());
        stage.reset();
    }
    /**
     * method get called when the player name in the settings is getting changed
     * @param playerName the playerName to save
     */
    public void changePlayerName(String playerName){
        player.setPlayerName(playerName);
        settingsService.getConfig().setPlayerName(playerName);
        settingsService.saveConfig();
    }
    /**
     * this method starts the game initially
     */
    public void startGame() {
        questionerData = vocabularyService.generateNextQuestion();
        stage.prepareRound(questionerData, player.getLife(),player.getHighscore());
    }

    /**
     * method to start a new game start a new game
     */
    public void startNewGame(){
        questionerData = vocabularyService.generateNextQuestion();
        stage.prepareNewGame(questionerData, player.getLife(),player.getHighscore());
    }

    /**
     * method to show the actual highscore
     */
    public void showHighscore(){
        List<HighscoreEntry> highscoreList = highscoreService.getHighscoreList();
        stage.showHighscore(highscoreList);
    }
    /**
     * method to show settings
     */
    public void showSettings() {
        String actualPlayername = player.getPlayerName();
        stage.showSettingsDialog(actualPlayername);
    }
}
