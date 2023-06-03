package com.besteleben.irregularpenguin.controller;

import com.badlogic.gdx.utils.Timer;
import com.besteleben.irregularpenguin.data.QuestionerData;
import com.besteleben.irregularpenguin.data.VocabularyDaoImplApi;
import com.besteleben.irregularpenguin.entities.Player;
import com.besteleben.irregularpenguin.screen.gamescreen.GameStage;
import com.besteleben.irregularpenguin.service.VocabularyService;

/**
 * Class for controlling User Input and business logic at all.
 * handles input coming from the input processor
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
     * Question Data which is needed to ask for the answer
     */
    public QuestionerData questionerData;


    /**
     * constructing the mediator for communication between middletier and frontend
     */
    public GameController(GameStage stage) {
        this.stage = stage;
        vocabularyService = new VocabularyService(new VocabularyDaoImplApi());
        player = new Player();
    }


    /**
     * get called when the user input needs to be validated by the middletier
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
                    System.out.println(questionerData);
                }
            }, 3f);
        }

    }

    /**
     * method for the interaction when the game is over
     */
    private void gameOver() {
        //todo
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
     * this method starts the game initially
     */
    public void startGame() {
        questionerData = vocabularyService.generateNextQuestion();
        stage.prepareRound(questionerData, player.getLife(),player.getHighscore());
    }

    /**
     * Function to update the Screen on certain events - get called by GameScreen regularly
     */
    public void update() {

    }

}
