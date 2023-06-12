package com.besteleben.feature_irregularpenguin.controller;

import com.badlogic.gdx.utils.Timer;

import com.besteleben.feature_irregularpenguin.data.objects.HighscoreEntry;
import com.besteleben.feature_irregularpenguin.data.objects.QuestionerData;
import com.besteleben.feature_irregularpenguin.entities.Player;
import com.besteleben.feature_irregularpenguin.screen.gamescreen.GameStage;
import com.besteleben.feature_irregularpenguin.service.HighscoreService;
import com.besteleben.feature_irregularpenguin.service.VocabularyService;
import com.besteleben.feature_login.objects.User;

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
     * Question Data which is needed to ask for the answer
     */
    public QuestionerData questionerData;
    /**
     * constructing the mediator for communication between service and frontend
     * @param highscoreService highscore service for manage the highscore
     * @param stage frontend reference
     * @param vocabularyService for managing the vocabulary/question
     */
    public GameController(GameStage stage, HighscoreService highscoreService, VocabularyService vocabularyService) {
        this.stage = stage;
        this.vocabularyService = vocabularyService;
        this.highscoreService = highscoreService;

        player = new Player();
    }
    /**
     * get called when the user input needs to be validated by the service
     * if the answer is correct or wrong it calls the nextQuestion method and handles the right/wrong answer
     * @param userInput the answer given by the user
     */
    public void handleUserInput(String userInput) {
        boolean answerCorrect = vocabularyService.checkAnswer(userInput);
        // when the answer is wrong
        if (!answerCorrect) {
            player.decreaseLife();
            nextQuestion(answerCorrect);
            if (player.getLife() == 0) {
                gameOver();
            }
        }
        // give result to frontend
        stage.reactionToAnswer(answerCorrect, player.getLife());
        // if the was correct
        if (answerCorrect) {
            player.increaseScore();
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    nextQuestion(answerCorrect);
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
     * method gets call when the answer was correct or wrong and the next question should be generated
     * if the answer was wrong it should be saved as wrong vocabulary
     * @param answerCorrect if the answer is correct
     */
    public void nextQuestion(boolean answerCorrect) {
        // save vocabulary as wrong
        if(!answerCorrect){
            vocabularyService.saveWrongAnsweredVocabulary();
        }
        questionerData = vocabularyService.generateNextQuestion(answerCorrect);
        stage.prepareRound(questionerData, player.getLife(),player.getHighscore());
        //reset stage when the answer is correct
        if(answerCorrect) {
            stage.reset();
        }
    }
    /**
     * this method starts the game initially
     */
    public void startGame() {
        questionerData = vocabularyService.generateNextQuestion(false);
        stage.prepareRound(questionerData, player.getLife(),player.getHighscore());
        player.setPlayerName(User.getInstance().getUsername());
    }
    /**
     * method to start a new game start a new game
     */
    public void startNewGame(){
        questionerData = vocabularyService.generateNextQuestion(false);
        player.reset();
        stage.prepareNewGame(questionerData, player.getLife(),player.getHighscore());

    }

    /**
     * method to show the actual highscore
     */
    public void showHighscore(){
        List<HighscoreEntry> highscoreList = highscoreService.getHighscoreList();
        stage.showHighscore(highscoreList);
    }
}
