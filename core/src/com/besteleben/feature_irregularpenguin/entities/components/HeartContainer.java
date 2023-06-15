package com.besteleben.feature_irregularpenguin.entities.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * representing the lives of the player as heart container
 * if the heart is alive its filled red otherwise show a alternativeTexture
 * to show that its already depleted.
 */
public class HeartContainer extends Actor {
    /**
     * heartTexture is the texture when the heart is 'filled'/alive
     */
    private TextureRegion heartTexture;
    /**
     * lostHeartTexture represents the texture which should be shown when the
     * heart is not available/alive anymore
     */
    private TextureRegion lostHeartTexture;
    /**
     * if the heart is still alive/depleted
     */
    private boolean alive;

    /**
     * Constructor to set up a single heart container with a texture when its depleted or still alive
     *
     * @param heartTexture     filled heartTexture
     * @param lostHeartTexture depleted heartTexture
     */
    public HeartContainer(TextureRegion heartTexture, TextureRegion lostHeartTexture) {
        this.heartTexture = heartTexture;
        this.lostHeartTexture = lostHeartTexture;
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
     * @param batch       batch to draw the textures
     * @param parentAlpha The parent alpha, to be multiplied with this actor's alpha, allowing the parent's alpha to affect all
     *                    children.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (alive) {
            batch.draw(heartTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        } else {
            batch.draw(lostHeartTexture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }
}
