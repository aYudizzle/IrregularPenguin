package com.besteleben.irregularpenguin.screen.gamescreen;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.besteleben.irregularpenguin.controller.GameController;
import com.besteleben.irregularpenguin.input.KeyboardInputProcessor;
import com.besteleben.irregularpenguin.screen.gamescreen.util.ResourceManager;

/**
 * Der GameScreen, welcher fuer den Spielablauf da ist.
 */
public class GameScreen extends ScreenAdapter {
    /** Verwaltet die Actors die im Screen dargestellt werden */
    private final GameStage stage;
    /** Haelt die Werte des Viewports */
    private FitViewport viewport;
    /** haelt den Mediator der die Kommunikation zwischen Service und Screen darstellt */
    private GameController controller;
    /** Haelt die Instanz des ResourceManager um den Skin an die Stage zu uebergeben */
    private ResourceManager resourceManager;
    /** register all inputs */
    private InputMultiplexer inputMultiplexer;

    /**
     * Konstruktor der den GameScreen erstellt. Außerdem die Stage, sowie eine Instanz des GameControllers,
     * der die Kommunikation zum Service ermoeglicht
     */
    public GameScreen() {
        viewport = new FitViewport(800,600);

        stage = new GameStage(viewport);
        controller = new GameController(stage);
        stage.setViewport(viewport);
        resourceManager = ResourceManager.getInstance();
        inputMultiplexer = new InputMultiplexer();
        createButtonClickListener();
        KeyboardInputProcessor answerInputProcessor = new KeyboardInputProcessor(stage.getAnswerTextField());

        inputMultiplexer.addProcessor(stage.getAnswerButton().getStage());
        inputMultiplexer.addProcessor(answerInputProcessor);
        Gdx.input.setInputProcessor(inputMultiplexer);
        viewport.apply();
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
        controller.update();
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
     */
    @Override
    public void show() {
    }


    /**
     * @see ApplicationListener#pause()
     */
    @Override
    public void pause() {

    }

    /**
     * @see ApplicationListener#resume()
     */
    @Override
    public void resume() {

    }
}
