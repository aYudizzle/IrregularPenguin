package com.besteleben.irregularpenguin.entities.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * The Background for the stage
 */
public class Background extends Actor {
    /** TextureRegion for the Background */
    private final TextureRegion backgroundRegion;
    /**
     * width of the screen so the background can fill the whole width
     */
    private final float screenWidth;
    /**
     * base height where the characters stands.
     */
    private final float baseLine;

    /**
     * constructor for the background
     * @param backgroundTexture stage for the texture of the background
     * @param baseLine Y coordinates where the characters move along
     */
    public Background(Texture backgroundTexture, float baseLine) {
        this.screenWidth = Gdx.graphics.getWidth();
        this.baseLine = baseLine;
        backgroundTexture.setWrap(Texture.TextureWrap.MirroredRepeat, Texture.TextureWrap.Repeat);
        backgroundRegion = new TextureRegion(backgroundTexture);
        backgroundRegion.setRegionWidth((int) screenWidth);
        backgroundRegion.setRegionHeight(backgroundTexture.getHeight());
        setSize(screenWidth, backgroundTexture.getHeight());
        setY(this.baseLine);
        setScale(2);
    }

    /**
     * draws the background with the given texture
     * @param batch The Batch for the texture - a batch is a rectangle where the texture is getting applied
     * @param parentAlpha The parent alpha, to be multiplied with this actor's alpha, allowing the parent's alpha to affect all
     *           children.
     */
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(backgroundRegion, getX(), getY(), getOriginX(), getOriginY(), screenWidth, getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
