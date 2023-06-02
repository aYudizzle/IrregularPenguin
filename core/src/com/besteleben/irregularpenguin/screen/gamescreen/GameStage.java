package com.besteleben.irregularpenguin.screen.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
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
    /** HORIZONTAL adjustment Line for Actors */
    private final static float V_BASE_LINE = 300;
    /** for skin ressources */
    private final ResourceManager resourceManager;
    /** List of all Actors */
    private final List<Actor> gameActors;
    /** displays the player character */
    private Penguin penguin;
    /** QuestionerGhost displays the NPC */
    private QuestionerGhost questionerGhost;
    /** Button to send the answer */
    private ImageButton answerButton;
    /** Answer Textfield to receive user input */
    private AnswerTextField answerTextField;
    /** displays the background */
    private Background background;
    /** displays the Floor */
    private Floor floor;
    /** Displays the life of the player */
    private LifeBar lifeBar;



    public GameStage(FitViewport viewport) {
        super(viewport);
        gameActors = new ArrayList<>();
        resourceManager = ResourceManager.getInstance();
        answerTextField = new AnswerTextField("Have fun and good luck",resourceManager.getSkin());
        createActors();
        addActorToStage();
        buttonSettings();
    }

    private void buttonSettings() {
        answerButton.setWidth(150);
        answerButton.setX(Gdx.graphics.getWidth() / 2f - answerButton.getWidth() / 2f);
        answerButton.setY(answerButton.getY()+15f);
        answerButton.setVisible(false);
    }

    private void addActorToStage() {
        addActor(background);
        addActor(floor);
        addActor(answerTextField);
        addActor(answerButton);
        addActor(lifeBar);
        addActor(penguin);
        addActor(questionerGhost);
    }

    /** Helper method for the Constructor where all the magic (new Actors) happens */
    private void createActors() {
        background = new Background(new Texture("stage/background.png"), V_BASE_LINE);
        floor = new Floor(new Texture("stage/floor.png"),V_BASE_LINE);
        penguin = new Penguin(0.5f, 0, V_BASE_LINE);
        questionerGhost = new QuestionerGhost(0.25f, Gdx.graphics.getWidth()/2f,V_BASE_LINE, resourceManager.getSkin());
        answerButton = new ImageButton(new TextureRegionDrawable(new Texture("button/buttonup.png")),new TextureRegionDrawable(new Texture("button/buttondown.png")));
        lifeBar = new LifeBar(new TextureRegion(new Texture("lifebar/heart_full.png")), new TextureRegion(new Texture("lifebar/heart_lost.png")));
    }


    @Override
    public void addActor(Actor actor) {
        gameActors.add(actor);
        super.addActor(actor);
    }
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
        if(penguin.isReachedCenter()){
            startInteraction();
        }
    }

    public void startInteraction(){
        answerTextField.setDisabled(false);
        questionerGhost.getQuestionLabel().setVisible(true);
        questionerGhost.showQuestion();
        answerTextField.setFocusTraversal(true);
        answerButton.setVisible(true);
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
