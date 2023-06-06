package com.besteleben.irregularpenguin.entities.components;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * The Lifebar creating a certain amount of heartcontainer to display
 * the players life
 */
public class LifeBar extends Group {
    /**
     * heartTexture for the heartcontainer
     */
    private final TextureRegion heartTexture;
    /**
     * emptyHeartTexture when the heart is empty
     */
    private final TextureRegion emptyHeartTexture;
    /**
     * maximum number of hearts
     */
    private int maximumHearts;
    /**
     * actualHearts to fill
     */
    private int actualHearts;

    /**
     * Constructor for a lifebar with heartcontainers
     * @param heartTexture heartTexture for the filled hearts
     * @param emptyHeartTexture emptyhearttexture for the empty hearts
     */
    public LifeBar(TextureRegion heartTexture, TextureRegion emptyHeartTexture) {
        this.heartTexture = heartTexture;
        this.emptyHeartTexture = emptyHeartTexture;
        maximumHearts = -1;
    }

    /**
     * method to create the needed heartContainers and add them to the lifeBar
     */
    private void createdHeartContainers() {
        for (int i = 0; i < maximumHearts; i++) {
            HeartContainer heartContainer = new HeartContainer(heartTexture, emptyHeartTexture);
            heartContainer.setPosition(10+(i * heartContainer.getWidth() * heartContainer.getScaleX()), 560 - (heartContainer.getHeight()*getScaleY()) - 2);
            addActor(heartContainer);
        }
    }

    /**
     * fill the amount of hearts to the life of the player
     * all other hearts should be displayed with the empty texture
     * @param lifeCount players life count
     */
    public void setFilledHearts(int lifeCount){
        if(maximumHearts == -1){
            maximumHearts = lifeCount;
            createdHeartContainers();
        }
        actualHearts = lifeCount;
        int displayedLife = MathUtils.clamp(lifeCount,0, maximumHearts);
        for (int i = 0; i < getChildren().size; i++) {
            Actor heart = getChildren().get(i);
            if(heart instanceof HeartContainer) {
                HeartContainer heartContainer = (HeartContainer) heart;
                boolean alive = i<displayedLife;
                heartContainer.setAlive(alive);
            }
        }
    }

    /**
     * Gets actutalLife.
     *
     * @return value of actutalLife
     */
    public int getActualHearts() {
        return actualHearts;
    }
}
