package com.besteleben.feature_irregularpenguin.screen.gamescreen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.besteleben.core.ScreenManager;
import com.besteleben.feature_irregularpenguin.controller.GameController;
import com.besteleben.feature_irregularpenguin.data.repository.HighscoreRepositoryImpl;
import com.besteleben.feature_irregularpenguin.data.repository.VocabularyRepositoryImpl;
import com.besteleben.feature_irregularpenguin.input.KeyboardInputProcessor;
import com.besteleben.feature_irregularpenguin.screen.gamescreen.util.ResourceManager;
import com.besteleben.feature_irregularpenguin.service.GameVocabularyService;
import com.besteleben.feature_irregularpenguin.service.HighscoreService;

/**
 * Gamescreen which displays the game.
 */
public class GameScreen extends ScreenAdapter {
    /**
     * Verwaltet die Actors die im Screen dargestellt werden
     */
    private GameStage stage;
    /**
     * Keeps the viewport for the screen
     */
    private FitViewport viewport;
    /**
     * haelt den Mediator der die Kommunikation zwischen Service und Screen darstellt
     */
    private GameController controller;
    /**
     * Haelt die Instanz des ResourceManager um den Skin an die Stage zu uebergeben
     */
    private ResourceManager resourceManager;
    /**
     * register all inputs
     */
    private InputMultiplexer inputMultiplexer;
    /**
     * Reference to the Highscore Service
     */
    private HighscoreService highscoreService;
    /**
     * Reference to the VocabularyService
     */
    private GameVocabularyService vocabularyService;

    /**
     * Konstruktor der den GameScreen erstellt. Außerdem die Stage, sowie eine Instanz des GameControllers,
     * der die Kommunikation zum Service ermoeglicht
     *
     * @param screenManager reference to the screenmanager who handles the different screens
     */
    public GameScreen(ScreenManager screenManager) {
        viewport = new FitViewport(800, 600);
        stage = new GameStage(viewport, screenManager);
        stage.setViewport(viewport);
        viewport.apply();

        createServices();
        controller = new GameController(stage, highscoreService, vocabularyService);

        resourceManager = ResourceManager.getResourceManager();
        inputMultiplexer = new InputMultiplexer();
        createButtonClickListener();
        createInputListener();
    }

    /**
     * helper method for the constructor, so the constructor is not bloated.
     */
    private void createInputListener() {
        KeyboardInputProcessor answerInputProcessor = new KeyboardInputProcessor(stage.getAnswerTextField());
        inputMultiplexer.addProcessor(stage);
        inputMultiplexer.addProcessor(answerInputProcessor);
    }

    /**
     * for creating the services - just for keeping the constructor clean
     */
    private void createServices() {
        highscoreService = new HighscoreService(new HighscoreRepositoryImpl());
        vocabularyService = new GameVocabularyService(new VocabularyRepositoryImpl());
    }

    /**
     * helper method for the constructor to create a custom ClickListener for the Answer Button
     */
    private void createButtonClickListener() {
        stage.getAnswerButton().addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.handleUserInput(stage.getAnswerTextField().getText());
                stage.setKeyboardFocus(null); // removed den focus, wenn man diesen mit einem click ins textfeld gesetzt hat, sodass eine vereinfachte Eingabe wieder moeglich ist.
                return true;
            }
        });
        stage.getHighscoreButton().addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.showHighscore();
                return true;
            }
        });
        stage.getRestartButton().addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                controller.startNewGame();
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
     * handled das verhalten beim resizen vom window mit angabe der neuen height und width
     *
     * @param width  die breite des fensters
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
        //Gdx.input.setInputProcessor darf erst bei show gesetzt werden, da diese sich sonst mit anderen
        //input processoren schneiden und fehlerhaft reagieren.
        Gdx.input.setInputProcessor(inputMultiplexer);
        controller.startGame();
    }
}
