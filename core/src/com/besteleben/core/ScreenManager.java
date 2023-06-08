package com.besteleben.core;

import com.badlogic.gdx.Screen;

import java.util.HashMap;
import java.util.Map;

public class ScreenManager {
    private final IrregularPenguinGame game;
    private final Map<String, Screen> screens;

    public ScreenManager(IrregularPenguinGame game) {
        this.game = game;
        this.screens = new HashMap<>();
    }

    public void addScreen(String name, Screen screen) {
        screens.put(name, screen);
    }

    public void removeScreen(String name) {
        screens.remove(name);
    }

    public void setScreen(String name) {
        Screen screen = screens.get(name);
        if (screen != null) {
            game.setScreen(screen);
        } else {
            throw new IllegalArgumentException("Screen not found: " + name);
        }
    }

    public void dispose() {
        for (Screen screen : screens.values()) {
            screen.dispose();
        }
        screens.clear();
    }
}
