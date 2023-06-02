package com.besteleben.irregularpenguin.entities.character.penguin;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.besteleben.irregularpenguin.entities.character.IngameCharacter;

public class Penguin extends IngameCharacter {
    private Animation<TextureRegion> runAnimation;
    private Animation<TextureRegion> hurtAnimation;
    private int life;
    private boolean running;
    private boolean reachedCenter;
    private boolean movingRight;

    public Penguin(float frameDuration, float defaultX, float defaultY){
        this.defaultX = defaultX;
        this.defaultY = defaultY;
        TextureAtlas textureAtlas = new TextureAtlas("penguin.atlas");
        String runRegionName = "walk"; // tag for run animation in penguin.atlas
        String idleRegionName = "Idle"; // Idle for idle animation in penguin.atlas
        this.life = 3;
        Array<TextureAtlas.AtlasRegion> runFrames = textureAtlas.findRegions(runRegionName);
        Array<TextureAtlas.AtlasRegion> idleFrames = textureAtlas.findRegions(idleRegionName);
        Array<TextureAtlas.AtlasRegion> hurtFrames = textureAtlas.findRegions("hurt");

        this.runAnimation = new Animation<TextureRegion>(frameDuration,runFrames, Animation.PlayMode.LOOP);
        idleAnimation = new Animation<TextureRegion>(frameDuration,idleFrames, Animation.PlayMode.LOOP);
        this.hurtAnimation = new Animation<TextureRegion>(frameDuration,hurtFrames,Animation.PlayMode.LOOP);
        setSize(runFrames.first().getRegionWidth(),runFrames.first().getRegionHeight());

        setY(this.defaultY);
        setX(this.defaultX);

        currentRegion = new TextureRegion();
        setScale(2);
        running = true;
        movingRight = false;
        reachedCenter = false;
    }

    /**
     * Updates the actor based on time. Typically this is called each frame by {@link Stage#act(float)}.
     * <p>
     * The default implementation calls {@link Action#act(float)} on each action and removes actions that are complete.
     *
     * @param delta Time in seconds since the last frame.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime += delta;
        updateAppearance();
    }

    public void draw(Batch batch, float parentAlpha) {
        if (currentRegion != null) {
            batch.draw(currentRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

    public void updateAppearance() {
        //todo UpdateAppearance im gamecontroller aufgerufen.
        // maybe noch implementieren
        if(running){
            TextureRegion currentFrame = runAnimation.getKeyFrame(elapsedTime);
            setSize(currentFrame.getRegionWidth(),currentFrame.getRegionHeight());
            currentRegion.setRegion(currentFrame);
            ;
            // Überprüfe, ob der Actor die Mitte erreicht hat
            if(!reachedCenter){
                moveBy(4,0);
            }
            if (getX() + getWidth() / 2 >= getStage().getWidth() / 2 - (4.9f*currentFrame.getRegionWidth()) && !reachedCenter) {
                reachedCenter = true;
                running = false; // Wechsle zum Idle-Zustand
//                movingRight = true; // Bewege nach rechts
            }
        } else {
            // Führe die Idle-Animation aus
            TextureRegion currentFrame = idleAnimation.getKeyFrame(elapsedTime);
            setSize(currentFrame.getRegionWidth(),currentFrame.getRegionHeight());
            currentRegion.setRegion(currentFrame);

            // Bewege nach rechts, wenn isMovingRight true ist
            if (movingRight) {
//                moveBy(1, 0); // Ändere die Werte entsprechend deiner Anforderungen
            }
        }
    }
    public void decreaseLife() {
        if(life>0){
            life--;
        }
    }

    public void increaseLife() {
        if(life<3){
            life++;
        }
    }


    /**
     * Gets running.
     *
     * @return value of running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets running.
     *
     * @param running value of running
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * Gets reachedCenter.
     *
     * @return value of reachedCenter
     */
    public boolean isReachedCenter() {
        return reachedCenter;
    }

    /**
     * Sets reachedCenter.
     *
     * @param reachedCenter value of reachedCenter
     */
    public void setReachedCenter(boolean reachedCenter) {
        this.reachedCenter = reachedCenter;
    }

    /**
     * Gets movingRight.
     *
     * @return value of movingRight
     */
    public boolean isMovingRight() {
        return movingRight;
    }

    /**
     * Sets movingRight.
     *
     * @param movingRight value of movingRight
     */
    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }



    /**
     * Gets life.
     *
     * @return value of life
     */
    public int getLife() {
        return life;
    }


}

