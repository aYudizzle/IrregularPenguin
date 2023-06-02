package com.besteleben.irregularpenguin.entities.utlility;

import com.besteleben.irregularpenguin.entities.character.penguin.Penguin;
import com.besteleben.irregularpenguin.entities.components.LifeBar;

/**
 * eine Klasse
 */
public class PenguinLifeManager {
    private final Penguin penguin;
    private final LifeBar lifeBar;

    public PenguinLifeManager(Penguin penguin, LifeBar lifeBar) {
        this.penguin = penguin;
        this.lifeBar = lifeBar;
        updateLifeBar();
    }

    public void decreaseLife(){
        penguin.decreaseLife();
        updateLifeBar();
    }

    public void increaseLife(){
        penguin.increaseLife();
        updateLifeBar();
    }

    public void updateLifeBar() {
        int currentLife = penguin.getLife();
        lifeBar.setFilledHearts(currentLife);
    }
}
