package com.besteleben.feature_irregularpenguin.screen.gamescreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.besteleben.core.ScreenManager;
import com.besteleben.feature_irregularpenguin.data.objects.HighscoreEntry;
import com.besteleben.feature_irregularpenguin.data.objects.QuestionerData;
import com.besteleben.feature_irregularpenguin.entities.character.penguin.Penguin;
import com.besteleben.feature_irregularpenguin.entities.character.questioner.QuestionerGhost;
import com.besteleben.feature_irregularpenguin.entities.components.AnswerTextField;
import com.besteleben.feature_irregularpenguin.entities.components.Background;
import com.besteleben.feature_irregularpenguin.entities.components.Floor;
import com.besteleben.feature_irregularpenguin.entities.components.LifeBar;
import com.besteleben.feature_irregularpenguin.dialogs.SettingsDialogBox;
import com.besteleben.feature_irregularpenguin.screen.gamescreen.util.ResourceManager;

import java.util.ArrayList;
import java.util.List;

/**
 * List of all Actors, so every Actor get synchronized and refreshed with the same delta time.
 */
public class GameStage extends Stage {
    /**
     * Screenmanager to switch to another screen when the game ends
     */
    private ScreenManager screenManager;
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
     * displays the player character as penguin
     */
    private Penguin penguin;
    /**
     * QuestionerGhost displays the NPC
     */
    private QuestionerGhost questionerGhost;
    /**
     * Button to send the answer and check whether its right or wrong
     */
    private ImageButton answerButton;
    /**
     * Button to send restart the game
     */
    private ImageButton restartButton;
    /**
     * Button to send show the highscore
     */
    private ImageButton highscoreButton;
    /**
     * Answer Textfield to receive user input
     */
    private final AnswerTextField answerTextField;
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
    private final Label highscoreDisplay;
    /**
     * table to adjust the image button for restarting the game, open up settings dialog and the highscore to the top right corner
     */
    private Table tableUpperRightCorner;
    /**
     * Button to open the Settings Dialog
     */
    private ImageButton settingsButton;
    /**
     * Dialogbox for player settings
     */
    private SettingsDialogBox settingsDialogBox;
    /**
     * constructor gets called when the stage get created and applies the viewport
     * @param viewport the given viewport by the Screen
     */
    public GameStage(FitViewport viewport, ScreenManager screenManager) {
        super(viewport);
        this.screenManager = screenManager;
        gameActors = new ArrayList<>();
        resourceManager = ResourceManager.getInstance();
        answerTextField = new AnswerTextField("Have fun and good luck", resourceManager.getSkin());
        highscoreDisplay = new Label("", resourceManager.getSkin(),"highscore-label-style");

        createActors();
        addActorToStage();
        userInterfaceSettings();
    }

    /**
     * visual settings for the User Interface
     */
    private void userInterfaceSettings() {
        answerButton.setWidth(150);
        answerButton.setX(Gdx.graphics.getWidth() / 2f - answerButton.getWidth() / 2f);
        answerButton.setY(answerButton.getY() + 30f);
        answerButton.setVisible(false);

        highscoreDisplay.setSize(200, 200);
        highscoreDisplay.setX(Gdx.graphics.getWidth()/2f);
        highscoreDisplay.setY(Gdx.graphics.getHeight()-140);

        tableUpperRightCorner.setFillParent(true);
        tableUpperRightCorner.top().right();
        tableUpperRightCorner.defaults().spaceTop(5f).spaceBottom(5f);
        float buttonImageScaling = 0.6f;
        tableUpperRightCorner.add(settingsButton)
                .padRight(5)
                .padTop(10)
                .size(restartButton.getHeight()*buttonImageScaling,restartButton.getHeight()*buttonImageScaling);
        tableUpperRightCorner.add(restartButton)
                .padRight(5)
                .padTop(10)
                .size(restartButton.getWidth() * buttonImageScaling, restartButton.getHeight() * buttonImageScaling);
        tableUpperRightCorner.row();
        tableUpperRightCorner.add();
        tableUpperRightCorner.add(highscoreButton)
                .padRight(5)
                .size(highscoreButton.getWidth() * buttonImageScaling, highscoreButton.getHeight() * buttonImageScaling);

        settingsDialogBox = new SettingsDialogBox("",resourceManager.getSkin());
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
        addActor(tableUpperRightCorner);
    }

    /**
     * Helper method for the Constructor where all the magic (new Actors) happens
     */
    private void createActors() {
        background = new Background(new Texture("stage/background.png"), V_BASE_LINE);
        floor = new Floor(new Texture("stage/floor.png"), V_BASE_LINE);
        penguin = new Penguin(0.5f, 0, V_BASE_LINE);
        questionerGhost = new QuestionerGhost(0.25f, Gdx.graphics.getWidth() / 2f, V_BASE_LINE, resourceManager.getSkin());
        answerButton = new ImageButton(new TextureRegionDrawable(new Texture("button/answer_buttonup.png")), new TextureRegionDrawable(new Texture("button/answer_buttondown.png")));
        restartButton = new ImageButton(new TextureRegionDrawable(new Texture("button/restart_buttonup.png")), new TextureRegionDrawable(new Texture("button/restart_buttondown.png")));
        highscoreButton = new ImageButton(new TextureRegionDrawable(new Texture("button/highscore_buttonup.png")), new TextureRegionDrawable(new Texture("button/highscore_buttondown.png")));
        settingsButton = new ImageButton(new TextureRegionDrawable(new Texture("button/settings_buttonup.png")), new TextureRegionDrawable(new Texture("button/settings_buttondown.png")));
        lifeBar = new LifeBar(new TextureRegion(new Texture("lifebar/heart_full.png")), new TextureRegion(new Texture("lifebar/heart_lost.png")));
        tableUpperRightCorner = new Table();
    }

    /**
     * to add actors to the actor list and the stage
     */
    @Override
    public void addActor(Actor actor) {
        gameActors.add(actor);
        super.addActor(actor);
    }

    /**
     * draws the stage and all actors with the delta time in seconds
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        // Aktualisiere alle Actors in der Stage
        for (Actor actor : gameActors) {
            actor.act(delta);
        }
        //front end reaction at certain things.
        if (penguin.hasReachedCenter() && !penguin.isPlayerDefeated()) {
            showInteractionComponents();
        }
    }

    /**
     * show and display all components for interactions
     */
    private void showInteractionComponents() {
        answerTextField.setDisabled(false);
        answerTextField.setFocusTraversal(false);
        answerButton.setVisible(true);
        questionerGhost.getQuestionLabel().setVisible(true);
    }

    /**
     * preparing the first round
     * @param questionerData data for the question
     * @param life life to be displayed
     * @param highscore score value
     */
    public void prepareRound(QuestionerData questionerData, int life, int highscore) {
        questionerGhost.setActualTexture(questionerData.getColor());
        questionerGhost.settingUpQuestion(questionerData.getVerb());
        questionerGhost.initializeQuestionerTexture();
        lifeBar.setFilledHearts(life);
        highscoreDisplay.setText(highscore);
    }

    /**
     * gets called by the controller when the answer got validated by the middletier
     * @param life lifes from player
     * @param result if the answer was right or wrong (true or false)
     */
    public void reactionToAnswer(boolean result, int life) {
        if (result) {
            penguin.setQuestionAnswered(true);
            questionerGhost.processRightAnswer();
            answerButton.setVisible(false);
        } else {
            lifeBar.setFilledHearts(life);
            if (lifeBar.getActualHearts() == 0) {
                gameOver();
            }
        }
    }

    /**
     * when the player is defeated this method triggers the gameover sequence and shows a dialog for
     * entering the players name
     */
    private void gameOver() {
        penguin.setPlayerDefeated(true);
        questionerGhost.gameEnd();
        answerTextField.setDisabled(true);
        answerTextField.setFocusTraversal(false);
        answerButton.setVisible(false);
        answerTextField.setText("Better luck next time.");
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
            screenManager.setScreen("mainmenu");
            }
        }, 0.5f);
    }

    /**
     * get called to reset the stage actors to default
     * the reset get delayed by few milliseconds
     * with 15fps -> 1.2 seconds, 30fps down to 0.3 seconds
     */
    public void reset() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                penguin.reset();
                questionerGhost.reset();
                answerTextField.reset();

            }
        }, 0.3f);
    }

    /**
     * shows a dialog with the current Highscore of the game
     * @param highscoreList current highscore list
     */
    public void showHighscore(List<HighscoreEntry> highscoreList) {
        Dialog highscoreDialog = new Dialog("",resourceManager.getSkin());
        highscoreDialog.setModal(true);
        highscoreDialog.setBackground(new TextureRegionDrawable(new Texture("dialog/dialogbg.png")));
        Table highscoreTable = new Table();
        Label nameLabel = new Label("Name",resourceManager.getSkin(),"dialog-label-style");
        Label scoreLabel = new Label("Score", resourceManager.getSkin(),"dialog-label-style");
        highscoreTable.add(nameLabel).padTop(30f).padRight(15f);
        highscoreTable.add(scoreLabel).padTop(30f);
        highscoreTable.row();
        for(HighscoreEntry highscoreEntry : highscoreList) {
            Label name = new Label(highscoreEntry.getPlayerName(), resourceManager.getSkin(),"dialoghs-label-style");
            Label score = new Label(String.valueOf(highscoreEntry.getPlayerScore()),resourceManager.getSkin(),"dialoghs-label-style");
            highscoreTable.add(name).pad(3f);
            highscoreTable.add(score).pad(3f);
            highscoreTable.row();
        }
        highscoreDialog.getContentTable().add(highscoreTable);
        highscoreDialog.button("Ok").padBottom(30f);
        highscoreDialog.show(this);
    }

    /**
     * method gets called when the player want to start a new game
     * @param highscore the highscore value
     * @param life players life to be displayed
     * @param questionerData data which is needed to display the question
     */
    public void prepareNewGame(QuestionerData questionerData, int life, int highscore) {
        penguin.reset();
        penguin.setPlayerDefeated(false);
        answerTextField.resetForNewGame();
        lifeBar.setFilledHearts(life);
        questionerGhost.setActualTexture(questionerData.getColor());
        questionerGhost.settingUpQuestion(questionerData.getVerb());
        questionerGhost.initializeQuestionerTexture();
        questionerGhost.reset();
        lifeBar.setFilledHearts(life);
        highscoreDisplay.setText(highscore);
    }

    /**
     * shows the settings dialog
     * @param actualPlayername the actual playerName.
     */
    public void showSettingsDialog(String actualPlayername) {
        settingsDialogBox.setPlayerName(actualPlayername);
        settingsDialogBox.show(this);
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
     * Gets answerButton.
     *
     * @return value of answerButton
     */
    public ImageButton getAnswerButton() {
        return answerButton;
    }
    /**
     * Gets restartButton.
     *
     * @return value of restartButton
     */
    public ImageButton getRestartButton() {
        return restartButton;
    }

    /**
     * Gets highscoreButton.
     *
     * @return value of highscoreButton
     */
    public ImageButton getHighscoreButton() {
        return highscoreButton;
    }

    /**
     * Gets settingsButton.
     *
     * @return value of settingsButton
     */
    public ImageButton getSettingsButton() {
        return settingsButton;
    }

    /**
     * Gets settingsDialogBox.
     *
     * @return value of settingsDialogBox
     */
    public SettingsDialogBox getSettingsDialogBox() {
        return settingsDialogBox;
    }


}
