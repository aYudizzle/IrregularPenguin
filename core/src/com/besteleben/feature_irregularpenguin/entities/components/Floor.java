package com.besteleben.feature_irregularpenguin.entities.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * class for the floor of the stage
 */
public class Floor extends Actor {
    /** Texture for the floor */
    private final TextureRegion floorRegion;
    /** screenWidth to fill the whole screen with the floor */
    private final float screenWidth;
    /** baseline to adjust the floor */
    private final float baseLine;

    /**
     * constructor to construct the floor of a stage
     * @param floorTexture texture for the floor
     * @param baseLine baseline of the characters
     */
    public Floor(Texture floorTexture,float baseLine) {
        this.screenWidth = 800;
        this.baseLine = baseLine;

        floorTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
        floorRegion = new TextureRegion(floorTexture);
        floorRegion.setRegionWidth((int) screenWidth);
        floorRegion.setRegionHeight(floorTexture.getHeight());
        setSize(screenWidth, floorTexture.getHeight());
        setY(this.baseLine-61);
    }

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(floorRegion, getX(), getY(), getOriginX(), getOriginY(), screenWidth, getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}

