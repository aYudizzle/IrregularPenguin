package com.besteleben.core;

import com.badlogic.gdx.Screen;

import java.util.HashMap;
import java.util.Map;

/**
 * Manager who helps with the screen management. Keeps an instance of the game and maps every possible screen to a key
 * so its easier to handle those screens and you are able to use shortnames to set the screen
 */
public class ScreenManager {
    /** reference of the game */
    private final IrregularPenguinGame game;
    /**
     * maps with all possible screens
     */
    private final Map<String, Screen> screens;

    /**
     * Constructor with a reference to the game instance
     * @param game reference to the game instance
     */
    public ScreenManager(IrregularPenguinGame game) {
        this.game = game;
        this.screens = new HashMap<>();
    }

    /**
     * add a screen to the manager so it can be set when needed
     * @param name screenname the shortname for the screen
     * @param screen instance of the screen
     */
    public void addScreen(String name, Screen screen) {
        screens.put(name, screen);
    }

    /**
     * method to remove screens from the manager
     * @param name the name of the screen which should get removed
     */
    public void removeScreen(String name) {
        screens.remove(name);
    }

    /**
     * method to set the screen as an active screen
     * @param name which screen should be shown
     */
    public void setScreen(String name) {
        Screen screen = screens.get(name);
        if (screen != null) {
            game.setScreen(screen);
        } else {
            System.out.println("Screen not found: " + name);
        }
    }

    /**
     * cleaning up resources when they are not needed anymore
     */
    public void dispose() {
        for (Screen screen : screens.values()) {
            screen.dispose();
        }
        screens.clear();
    }
}
