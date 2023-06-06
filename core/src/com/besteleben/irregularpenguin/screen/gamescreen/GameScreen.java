package com.besteleben.irregularpenguin.screen.gamescreen;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.besteleben.irregularpenguin.controller.GameController;
import com.besteleben.irregularpenguin.data.repository.HighscoreRepositoryImpl;
import com.besteleben.irregularpenguin.data.repository.SettingsRepositoryImpl;
import com.besteleben.irregularpenguin.data.repository.VocabularyRepositoryImplApi;
import com.besteleben.irregularpenguin.input.KeyboardInputProcessor;
import com.besteleben.irregularpenguin.input.dialogs.SettingsDialogBox;
import com.besteleben.irregularpenguin.screen.gamescreen.util.ResourceManager;
import com.besteleben.irregularpenguin.service.HighscoreService;
import com.besteleben.irregularpenguin.service.SettingsService;
import com.besteleben.irregularpenguin.service.VocabularyService;

/**
 * Der GameScreen, welcher fuer den Spielablauf da ist.
 */
public class GameScreen extends ScreenAdapter {
    /** Verwaltet die Actors die im Screen dargestellt werden */
    private final GameStage stage;
    /** Haelt die Werte des Viewports */
    private final FitViewport viewport;
    /** haelt den Mediator der die Kommunikation zwischen Service und Screen darstellt */
    private final GameController controller;
    /** Haelt die Instanz des ResourceManager um den Skin an die Stage zu uebergeben */
    private final ResourceManager resourceManager;
    /** register all inputs */
    private final InputMultiplexer inputMultiplexer;
    /** Reference to the Highscore Service */
    private HighscoreService highscoreService;
    /** Reference to the Settings Service */
    private SettingsService settingsService;
    /** Reference to the VocabularyService */
    private VocabularyService vocabularyService;

    /**
     * Konstruktor der den GameScreen erstellt. Außerdem die Stage, sowie eine Instanz des GameControllers,
     * der die Kommunikation zum Service ermoeglicht
     */
    public GameScreen() {
        viewport = new FitViewport(800,600);
        stage = new GameStage(viewport);
        stage.setViewport(viewport);
        viewport.apply();

        createServices();
        controller = new GameController(stage, settingsService,highscoreService,vocabularyService);

        resourceManager = ResourceManager.getInstance();
        inputMultiplexer = new InputMultiplexer();
        createButtonClickListener();
        createInputListener();

    }

    /**
     * helper method for the constructor, so the constructor is not bloated.
     */
    private void createInputListener() {
        KeyboardInputProcessor answerInputProcessor = new KeyboardInputProcessor(stage.getAnswerTextField());
        inputMultiplexer.addProcessor(stage.getHighscoreButton().getStage());
        inputMultiplexer.addProcessor(stage.getRestartButton().getStage());
        inputMultiplexer.addProcessor(stage.getAnswerButton().getStage());
        inputMultiplexer.addProcessor(answerInputProcessor);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    /**
     * for creating the services - just for keeping the constructor clean
     */
    private void createServices() {
        settingsService = new SettingsService(new SettingsRepositoryImpl());
        highscoreService = new HighscoreService(new HighscoreRepositoryImpl());
        vocabularyService = new VocabularyService(new VocabularyRepositoryImplApi());
    }

    /**
     * helper method for the constructor to create a custom ClickListener for the Answer Button
     */
    private void createButtonClickListener() {
        stage.getAnswerButton().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.handleUserInput(stage.getAnswerTextField().getText());
                return true;
            }
        });
        stage.getHighscoreButton().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.showHighscore();
                return true;
            }
        });
        stage.getRestartButton().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.startNewGame();
                return true;
            }
        });

        stage.getSettingsButton().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.showSettings();
                return true;
            }
        });

        SettingsDialogBox settingsDialogBox = stage.getSettingsDialogBox();
        settingsDialogBox.getSaveButton().addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                String wantedPlayername = settingsDialogBox.getPlayerNameField().getText();
                controller.changePlayerName(wantedPlayername);
                settingsDialogBox.hide();
                return true;
            }
        });
    }

    /**
     * zum Zeichnen der Frames
     *
     * @param delta The time in seconds since the last render.
     */
    @Override
    public void render(float delta) {
        //rgb value / 255, da libGdx lediglich Werte zwischen 0 und 1 verarbeiten kann.
        ScreenUtils.clear(new Color(186 / 255f, 235 / 255f, 255 / 255f, 1));
            stage.act(delta);
            stage.draw();
    }

    /**
     * handled das verhalten beim resizen vom window mit angabe der neuen hight und width
     * @param width die breite des fensters
     * @param height die hoehe des fensters
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    /**
     * zeigt das Verhalten an, wenn das Spiel minimiert wird.
     * dispose damit der Grafikspeicher wieder freigegeben wird und nicht belegt bleibt.
     */
    @Override
    public void hide() {
        this.dispose();
    }

    /**
     * Damit der Screen alle Grafikresourcen wieder freigibt.
     */
    @Override
    public void dispose() {
        resourceManager.dispose();
        stage.dispose();
    }

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     * calls the controller.startGame Method to start the first round of the game
     */
    @Override
    public void show() {
        controller.startGame();
    }
}
