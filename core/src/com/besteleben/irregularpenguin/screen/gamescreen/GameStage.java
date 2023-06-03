package com.besteleben.irregularpenguin.screen.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.besteleben.irregularpenguin.data.QuestionerData;
import com.besteleben.irregularpenguin.entities.character.penguin.Penguin;
import com.besteleben.irregularpenguin.entities.character.questioner.QuestionerGhost;
import com.besteleben.irregularpenguin.entities.components.AnswerTextField;
import com.besteleben.irregularpenguin.entities.components.Background;
import com.besteleben.irregularpenguin.entities.components.Floor;
import com.besteleben.irregularpenguin.entities.components.LifeBar;
import com.besteleben.irregularpenguin.screen.gamescreen.util.ResourceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * List of all Actors, so every Actor get synchronized and refreshed with the same delta time.
 */
public class GameStage extends Stage {
    /**
     * HORIZONTAL adjustment Line for Actors
     */
    private final static float V_BASE_LINE = 300;
    /**
     * for skin ressources
     */
    private final ResourceManager resourceManager;
    /**
     * List of all Actors
     */
    private final List<Actor> gameActors;
    /**
     * displays the player character
     */
    private Penguin penguin;
    /**
     * QuestionerGhost displays the NPC
     */
    private QuestionerGhost questionerGhost;
    /**
     * Button to send the answer
     */
    private ImageButton answerButton;
    /**
     * Answer Textfield to receive user input
     */
    private AnswerTextField answerTextField;
    /**
     * displays the background
     */
    private Background background;
    /**
     * displays the Floor
     */
    private Floor floor;
    /**
     * Displays the life of the player
     */
    private LifeBar lifeBar;
    /**
     * Label to display the highscore in the top center of the screen
     */
    private Label highscoreDisplay;

    /**
     * constructor gets called when the stage get created
     */
    public GameStage(FitViewport viewport) {
        super(viewport);
        gameActors = new ArrayList<>();
        resourceManager = ResourceManager.getInstance();
        answerTextField = new AnswerTextField("Have fun and good luck", resourceManager.getSkin());
        highscoreDisplay = new Label("", resourceManager.getSkin(),"highscore-label-style");

        createActors();
        addActorToStage();
        buttonHighscoreSettings();
    }

    /**
     * visual settings for the button
     */
    private void buttonHighscoreSettings() {
        answerButton.setWidth(150);
        answerButton.setX(Gdx.graphics.getWidth() / 2f - answerButton.getWidth() / 2f);
        answerButton.setY(answerButton.getY() + 15f);
        answerButton.setVisible(false);
        highscoreDisplay.setSize(200, 200);
        highscoreDisplay.setX(Gdx.graphics.getWidth()/2f);
        System.out.println(highscoreDisplay.getX());
        highscoreDisplay.setY(Gdx.graphics.getHeight()-150);
    }

    /**
     * constructor method to add all actors to the stage
     */
    private void addActorToStage() {
        addActor(background);
        addActor(floor);
        addActor(answerTextField);
        addActor(answerButton);
        addActor(lifeBar);
        addActor(penguin);
        addActor(questionerGhost);
        addActor(highscoreDisplay);


    }

    /**
     * Helper method for the Constructor where all the magic (new Actors) happens
     */
    private void createActors() {
        background = new Background(new Texture("stage/background.png"), V_BASE_LINE);
        floor = new Floor(new Texture("stage/floor.png"), V_BASE_LINE);
        penguin = new Penguin(0.5f, 0, V_BASE_LINE);
        questionerGhost = new QuestionerGhost(0.25f, Gdx.graphics.getWidth() / 2f, V_BASE_LINE, resourceManager.getSkin());
        answerButton = new ImageButton(new TextureRegionDrawable(new Texture("button/buttonup.png")), new TextureRegionDrawable(new Texture("button/buttondown.png")));
        lifeBar = new LifeBar(new TextureRegion(new Texture("lifebar/heart_full.png")), new TextureRegion(new Texture("lifebar/heart_lost.png")));
    }

    /**
     * to add actors to the actor list
     */
    @Override
    public void addActor(Actor actor) {
        gameActors.add(actor);
        super.addActor(actor);
    }

    /**
     * to remove actors from the stage
     */
    public void removeActor(Actor actor) {
        gameActors.remove(actor);
        actor.remove();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        // Aktualisiere alle Actors in der Stage
        for (Actor actor : gameActors) {
            actor.act(delta);
        }
        //front end reaction at certain things.
        if (penguin.hasReachedCenter() && !penguin.isDefeated()) {
            showInteractionComponents();
        }

    }

    /**
     * show and display all components for interactions
     */
    private void showInteractionComponents() {
        answerTextField.setDisabled(false);
        answerTextField.setFocusTraversal(true);
        answerButton.setVisible(true);
        questionerGhost.getQuestionLabel().setVisible(true);
    }

    public void prepareRound(QuestionerData questionerData, int life, int highscore) {
        questionerGhost.setActualTexture(questionerData.getColor());
        questionerGhost.settingUpQuestion(questionerData.getVerb());
        questionerGhost.initializeQuestionerTexture();
        lifeBar.setFilledHearts(life);
        highscoreDisplay.setText(highscore);
    }

    /**
     * gets called by the controller when the answer got validated by the middletier
     */
    public void reactionToAnswer(boolean result, int life) {
        if (result) {
            penguin.setAnswered(true);
            questionerGhost.processRightAnswer();
        } else {
            lifeBar.setFilledHearts(life);
            if (lifeBar.getActutalLife() == 0) {
                gameOver();
            }
        }
    }

    private void gameOver() {
        penguin.setDefeated(true);
        questionerGhost.gameEnd();
        answerTextField.setDisabled(true);
        answerTextField.setFocusTraversal(false);
        answerButton.setVisible(false);
        answerTextField.setText("Better luck next time.");

    }


    public void reset() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                penguin.reset();
                questionerGhost.reset();
                answerTextField.reset();
            }
        }, 1.2f);
    }


    /**
     * Gets answerTextField.
     *
     * @return value of answerTextField
     */
    public AnswerTextField getAnswerTextField() {
        return answerTextField;
    }

    /**
     * Gets actors.
     *
     * @return value of actors
     */
    public List<Actor> getGameActors() {
        return gameActors;
    }

    /**
     * Gets answerButton.
     *
     * @return value of answerButton
     */
    public ImageButton getAnswerButton() {
        return answerButton;
    }


}
