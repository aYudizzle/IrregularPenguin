package com.besteleben.irregularpenguin.entities.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HeartContainer extends Actor {
    private TextureRegion heartTexture;
    private TextureRegion alternativeTexture;
    private boolean alive;

    public HeartContainer(TextureRegion heartTexture, TextureRegion alternativeTexture) {
        this.heartTexture = heartTexture;
        this.alternativeTexture = alternativeTexture;
        setSize(heartTexture.getRegionWidth(), heartTexture.getRegionHeight());
        setScale(2);
    }
    /**
     * Sets alive.
     *
     * @param alive value of alive
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Draws the actor. The batch is configured to draw in the parent's coordinate system.
     * {@link Batch#draw(TextureRegion, float, float, float, float, float, float, float, float, float)
     * This draw method} is convenient to draw a rotated and scaled TextureRegion. {@link Batch#begin()} has already been called on
     * the batch. If {@link Batch#end()} is called to draw without the batch then {@link Batch#begin()} must be called before the
     * method returns.
     * <p>
     * The default implementation does nothing.
     *
     * @param batch
     * @param parentAlpha The parent alpha, to be multiplied with this actor's alpha, allowing the parent's alpha to affect all
     *                    children.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(alive){
            batch.draw(heartTexture,getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        } else {
            batch.draw(alternativeTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}
