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
    private boolean answered = false;
    private boolean defeated = false;

    public Penguin(float frameDuration, float defaultX, float defaultY){
        this.defaultX = defaultX;
        this.defaultY = defaultY;
        TextureAtlas textureAtlas = new TextureAtlas("penguin.atlas");
        String runRegionName = "walk"; // tag for run animation in penguin.atlas
        String idleRegionName = "Idle"; // Idle for idle animation in penguin.atlas
        Array<TextureAtlas.AtlasRegion> runFrames = textureAtlas.findRegions(runRegionName);
        Array<TextureAtlas.AtlasRegion> idleFrames = textureAtlas.findRegions(idleRegionName);
        Array<TextureAtlas.AtlasRegion> hurtFrames = textureAtlas.findRegions("hurt");

        this.runAnimation = new Animation<>(frameDuration,runFrames, Animation.PlayMode.LOOP);
        idleAnimation = new Animation<>(frameDuration,idleFrames, Animation.PlayMode.LOOP);
        this.hurtAnimation = new Animation<>(frameDuration,hurtFrames,Animation.PlayMode.LOOP);
        setSize(runFrames.first().getRegionWidth(),runFrames.first().getRegionHeight());

        setY(this.defaultY);
        setX(this.defaultX);

        currentRegion = new TextureRegion();
        setScale(2);
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

        if (!answered) {
            if (!hasReachedCenter()) {
                running();
            } else {
                if (defeated) {
                    hurt();
                } else {
                    idle();
                }
            }
        } else {
//            hurt();
            running();
            }
        }


    private void hurt() {
        TextureRegion currentFrame;
        currentFrame = hurtAnimation.getKeyFrame(elapsedTime);
        setSize(currentFrame.getRegionWidth(),currentFrame.getRegionHeight());
        currentRegion.setRegion(currentFrame);
    }

    public void idle(){
        TextureRegion currentFrame;
        currentFrame = idleAnimation.getKeyFrame(elapsedTime);
        setSize(currentFrame.getRegionWidth(),currentFrame.getRegionHeight());
        currentRegion.setRegion(currentFrame);
    }
    public void running(){
        TextureRegion currentFrame;
        currentFrame = runAnimation.getKeyFrame(elapsedTime);
        setSize(currentFrame.getRegionWidth(),currentFrame.getRegionHeight());
        currentRegion.setRegion(currentFrame);
        moveBy(4,0);
    }

    public boolean hasReachedCenter() {
        float centerX = getX() + getWidth() / 2;
        float stageCenterX = getStage().getWidth() / 2 - (4.9f*currentRegion.getRegionWidth()); // für eine gewisse verschiebung nach links
        return centerX == stageCenterX;
    }

//    public boolean hasReachedRight() {
//        float penguinRightX = getX() + getWidth(); // Rechte Grenze des Pinguins
//        float stageRightX = getStage().getWidth(); // Rechte Grenze der Stage
//        return penguinRightX >= stageRightX;
//    }


    public void reset() {
        setX(defaultX);
        answered = false;

    }

    /**
     * Gets answered.
     *
     * @return value of answered
     */
    public boolean isAnswered() {
        return answered;
    }

    /**
     * Sets answered.
     *
     * @param answered value of answered
     */
    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    /**
     * Gets defeated.
     *
     * @return value of defeated
     */
    public boolean isDefeated() {
        return defeated;
    }

    /**
     * Sets defeated.
     *
     * @param defeated value of defeated
     */
    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

}

