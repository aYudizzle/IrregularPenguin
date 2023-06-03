package com.besteleben.irregularpenguin.entities.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class LifeBar extends Group {
    private final TextureRegion heartTexture;
    private final TextureRegion alternativeTexture;
    private int maxLife;

    private int actutalLife;

    public LifeBar(TextureRegion heartTexture, TextureRegion alternativeTexture) {
        this.heartTexture = heartTexture;
        this.alternativeTexture = alternativeTexture;
        maxLife = -1;
    }

    private void createdHeartContainers() {
        for (int i = 0; i < maxLife; i++) {
            HeartContainer heartContainer = new HeartContainer(heartTexture,alternativeTexture);
            heartContainer.setPosition(10+(i * heartContainer.getWidth() * heartContainer.getScaleX()), 560 - (heartContainer.getHeight()*getScaleY()) - 2);
            addActor(heartContainer);
        }
    }

    public void setFilledHearts(int lifeCount){
        if(maxLife == -1){
            maxLife = lifeCount;
            createdHeartContainers();
        }
        actutalLife = lifeCount;
        int displayedLife = MathUtils.clamp(lifeCount,0,maxLife);
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
    public int getActutalLife() {
        return actutalLife;
    }
}
