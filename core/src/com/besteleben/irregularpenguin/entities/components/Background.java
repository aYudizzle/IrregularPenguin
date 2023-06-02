package com.besteleben.irregularpenguin.entities.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {
    private final TextureRegion backgroundRegion;
    private final float screenWidth;
    private final float baseLine;

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

    public void draw(Batch batch, float parentAlpha) {
        batch.draw(backgroundRegion, getX(), getY(), getOriginX(), getOriginY(), screenWidth, getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
