package com.besteleben.core;

import com.badlogic.gdx.Game;
import com.besteleben.feature_irregularpenguin.screen.gamescreen.GameScreen;
import com.besteleben.feature_login.screen.LoginScreen;

/**
 * Klasse, die den Einstieg in das Spiel darstellt,
 * hier wird der Screenmanager erstellt und es koennen ggf. weitere Einstellungen gemacht werden.
 */

public class IrregularPenguinGame extends Game {
    /**
     * reference for the screen manager who switchs between different screens
     */
    private ScreenManager screenManager;

    /**
     * create method where the screenManager gets created.
     */
    @Override
    public void create() {
        screenManager = new ScreenManager(this);

        screenManager.addScreen("login", new LoginScreen(screenManager));
        screenManager.addScreen("game", new GameScreen(screenManager));
        screenManager.setScreen("login");
    }

    /**
     * to clean up used ressources
     */
    @Override
    public void dispose() {
        screenManager.dispose();
    }
}
